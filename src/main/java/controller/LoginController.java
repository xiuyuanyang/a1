package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.ResultBean;
import beans.User;
import param.CountParam;
import param.LoginParam;
import service.LoginService;
import utils.Global;
import utils.IOUtil;
import utils.IdGen;
import utils.MD5;
import utils.MessageConstants;
import utils.RedisUtil;

@Controller
@RequestMapping(value = "mobile")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String tokenExpiresIn = Global.getConfig("token_expires_in");

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/x-www-form-urlencoded",
			"application/json" })
	@ResponseBody
	public Object login(@RequestBody LoginParam lp, HttpServletRequest req) {

		String contenttype = req.getHeader("content-type");
		String token, mobile, password;
		ResultBean rb = new ResultBean();

		if (contenttype.equalsIgnoreCase("application/json")) {
			mobile = lp.getMobile();
			password = lp.getPassword();
		} else {
			mobile = req.getParameter("mobile");
			password = req.getParameter("password");
		}

		Map<String, String> map = new HashMap<String, String>();
		password = MD5.getMD5Code(password);

		if (StringUtils.isEmpty(password) | StringUtils.isEmpty(mobile)) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("login fail , mobile or password can not be empty");
			return rb;
		}
		mobile = mobile.trim();
		password = password.trim();
		User lu = new User();
		lu.setMobile(mobile);
		lu.setPassword(password);
		User u = loginService.getLogin(lu);
		if (u == null) {
			logger.info("loginService.getLogin(lu) and user is null ");
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("login fail , wrong password or mobile");
		} else {
			// try to get current time token by mobile no. then delete this .
			token = RedisUtil.get(mobile);
			if (!StringUtils.isEmpty(token)) {
				RedisUtil.del(token);
			}

			// generate new token and bind to mobile user.
			token = IdGen.uuid();
			rb.setCode(MessageConstants.success.getValue());
			rb.setMessage("login success");
			map.put("userid", u.getId() + "");
			map.put("token", token);
			rb.setData(map);
			RedisUtil.setex(mobile, Integer.valueOf(tokenExpiresIn), token);
			RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), u.getId() + "");
		}
		return rb;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(HttpServletRequest req) {

		String username = req.getParameter("username").trim();
		String mobile = req.getParameter("mobile").trim();
		String password = req.getParameter("password").trim();

		ResultBean rb = new ResultBean();

		if (StringUtils.isEmpty(username) | StringUtils.isEmpty(mobile) | StringUtils.isEmpty(password)) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("register fail , mobile or username or password can not be empty");
			return rb;
		} else {
			username = username.trim();
			mobile = mobile.trim();
			password = password.trim();
			User u = new User();
			u.setMobile(mobile);
			logger.info("register user and password = " + password);
			password = MD5.getMD5Code(password);
			u.setPassword(password);
			u.setUsername(username);

			// JSONObject jo = new JSONObject(u);

			if (loginService.addUser(u)) {
				rb.setCode(MessageConstants.success.getValue());
				rb.setMessage("register success");
			} else {
				rb.setCode(MessageConstants.error.getValue());
				rb.setMessage("register fail , mobile number has been used");
			}
		}
		return rb;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout(@RequestBody CountParam cp ,HttpServletRequest req) {
		ResultBean rb = new ResultBean();
		try {
//			String str = IOUtil.getBodyString(req.getReader());
//			JSONObject object = new JSONObject(str);
//			String token = (String) object.get("token");
			
			String str = cp.getToken();

			if (StringUtils.isEmpty(str)) {
				rb.setCode(0);
				rb.setMessage("token can not be empty");
				return rb;
			}

			String token = str.trim();
			RedisUtil.del(token);
			rb.setCode(1);
			rb.setMessage("logout success");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}

	@ResponseBody
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	public void resetPwd() {
		//
		// String mobile = param.getMobile();
		// String password = param.getPassword();
		// String verifyCode = param.getVerifyCode();
		//
		// try {
		// if (StringUtils.isEmpty(mobile)) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_MOBILE_IS_NULL);
		// }
		// if (StringUtils.isEmpty(password)) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_PWD_IS_NULL);
		// }
		//
		// //éªŒè¯�ç �åŒ¹é…�
		// if (StringUtils.isEmpty(verifyCode)) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_VERIFY_CODE_IS_NULL);
		// }
		//
		// String code = RedisUtil.get(mobile);
		//
		// if (StringUtils.isEmpty(code)) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_VERIFY_CODE_PAST);
		// }
		// if (!verifyCode.equals(code)) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_VERIFY_INCORRECT);
		// }
		//
		// User user = userService.getUserBymobile(mobile);
		// if (null == user) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_USER_DONT_EXIST);
		// }
		//
		// //å¯†ç �ç”¨md5åŠ å¯†
		// String pwd = MD5.getMD5Code(password);
		// user.setPassword(pwd);
		//
		// boolean b = userService.updatePassword(user);
		// if (!b) {
		// return Head
		// .failedMessage(MchtMessages.ERROR_MODIFY_PWD);
		// }
		//
		// return Head.successMessage();
		//
		// } catch (Exception e) {
		//
		// log.info(MchtMessages.ERROR_MODIFY_PWD, e);
		// return Head
		// .failedMessage(MchtMessages.ERROR_MODIFY_PWD);
		// }

	}

}
