package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.NameCard;
import beans.ResultBean;
import param.AddCardParam;
import param.CountParam;
import param.GetCardParam;
import param.SetPhotoParam;
import param.SetPhotosParam;
import param.SetPhotosParams;
import service.NameCardService;
import utils.Global;
import utils.IOUtil;
import utils.IdGen;
import utils.MessageConstants;
import utils.RedisUtil;

@Controller
@RequestMapping("/card")
public class CardController {

	private Logger logger = LoggerFactory.getLogger(CardController.class);
	private static final String tokenExpiresIn = Global.getConfig("token_expires_in");
	private static final String photoFolder = Global.getConfig("photo_folder");

	@Autowired
	ServletContext servletContext;

	@Autowired
	private NameCardService nameCardService;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public Object getCard(@RequestBody GetCardParam cp) {

		String token = cp.getToken();
		String userid;
		ResultBean rb = new ResultBean();
		if (token != null) {
			token = token.trim();
			userid = RedisUtil.get(token);
			logger.info("token != null and get userid = " + userid);
			if (userid != null) {
				RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), userid);
			} else {
				rb.setCode(0);
				rb.setMessage("token expired");
				return rb;
			}
		} else {
			rb.setCode(0);
			rb.setMessage("download without token");
			return rb;
		}

		String id = cp.getId();
		int uid = Integer.parseInt(userid);
		NameCard nc = nameCardService.getOneCard(id, uid);

		if (nc == null) {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("fail , no result ");
			return rb;
		}

		rb.setData(nc);
		rb.setCode(MessageConstants.success.getValue());
		rb.setMessage("success");
		return rb;
	}

	@RequestMapping(value = "/put", method = RequestMethod.POST)
	@ResponseBody
	public Object addCard(@RequestBody AddCardParam acp) {

		String token;
		token = acp.getToken();

		String userid;
		String uuid;
		ResultBean rb = new ResultBean();
		if (token != null) {
			token = token.trim();
			userid = RedisUtil.get(token);
			logger.info("token != null and get userid = " + userid);
			if (userid != null) {
				RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), userid);
			} else {
				rb.setCode(0);
				rb.setMessage("token expired");
				return rb;
			}
		} else {
			rb.setCode(0);
			rb.setMessage("upload without token");
			return rb;
		}

		String id = acp.getId();
		String name = acp.getName();
		String photolink = acp.getPhotolink();
		String company = acp.getCompany();
		String title = acp.getTitle();
		String email = acp.getEmail();
		String phone = acp.getPhone();
		String fax = acp.getFax();
		String mobile = acp.getMobile();
		String address = acp.getAddress();
		String website = acp.getWebsite();
		String seriesNumber = acp.getSeriesNumber();
		String themeType = acp.getThemeType();
		String language = acp.getLanguage();

		NameCard nc = new NameCard();
//		Map<String, String> map = new HashMap<String, String>();
		nc.setUid(Integer.parseInt(userid.trim()));
		boolean updateFlag = false;
		
		if (!StringUtils.isEmpty(id)) {
			System.out.println("id = "+id);
			System.out.println("userid = "+userid);
			nc.setId(id.trim());
			uuid = id.trim();
//			map.put("isNotUpdate", "0");
			updateFlag = true;
		} else {
			uuid = IdGen.uuid();
			nc.setId(uuid);
//			map.put("isNotUpdate", "1");
		}
		
		if (!StringUtils.isEmpty(name)) {
			nc.setName(name.trim());
		}
		if (!StringUtils.isEmpty(photolink)) {
			nc.setPhotolink(photolink.trim());
		}
		if (!StringUtils.isEmpty(company)) {
			nc.setCompany(company.trim());
		}
		if (!StringUtils.isEmpty(title)) {
			nc.setTitle(title.trim());
		}
		if (!StringUtils.isEmpty(email)) {
			nc.setEmail(email.trim());
		}
		if (!StringUtils.isEmpty(phone)) {
			nc.setPhone(phone.trim());
		}
		if (!StringUtils.isEmpty(fax)) {
			nc.setFax(fax.trim());
		}
		if (!StringUtils.isEmpty(mobile)) {
			nc.setMobile(mobile.trim());
		}
		if (!StringUtils.isEmpty(address)) {
			nc.setAddress(address.trim());
		}
		if (!StringUtils.isEmpty(website)) {
			nc.setWebsite(website.trim());
		}
		if (!StringUtils.isEmpty(seriesNumber)) {
			nc.setSeriesNumber(Integer.parseInt(seriesNumber.trim()));
		}
		if (!StringUtils.isEmpty(themeType)) {
			nc.setThemeType(Integer.parseInt(themeType.trim()));
		}
		if (!StringUtils.isEmpty(language)) {
			nc.setLanguage(Integer.parseInt(language.trim()));
		}

//		if (updateFlag) {
//			if (nameCardService.modifyOneCard(nc)) {
//				rb.setMessage("upload update success");
//				map.put("id", uuid);
//				rb.setData(map);
//				rb.setCode(MessageConstants.success.getValue());
//			} else {				
//				rb.setMessage("upload update fail");
//				rb.setCode(MessageConstants.error.getValue());
//			}
//			
//		} else {
//			if (nameCardService.addOneCard(nc)) {
//				rb.setMessage("upload insert success");
//				map.put("id", uuid);
//				rb.setData(map);
//				rb.setCode(MessageConstants.success.getValue());
//			} else {
//				rb.setMessage("upload insert fail");
//				rb.setCode(MessageConstants.error.getValue());
//			}
//		}
		uploadCard(nc,rb,updateFlag);
		return rb;

	}

	private void uploadCard(NameCard nc , ResultBean rb , boolean isUpdate){
		Map<String, String> map = new HashMap<String, String>();
		if (isUpdate) {
			if (nameCardService.modifyOneCard(nc)) {
				rb.setMessage("upload update success");
				map.put("id", nc.getId());
				map.put("isNotUpdate", "0");
				rb.setData(map);
				rb.setCode(MessageConstants.success.getValue());
			} else {
				if(nameCardService.addOneCard(nc)){
					rb.setMessage("meant to update but in fact insert success");
					map.put("id", nc.getId());
					map.put("isNotUpdate", "1");
					rb.setData(map);
					rb.setCode(MessageConstants.success.getValue());
				} else {
					rb.setMessage("upload update fail");
					rb.setCode(MessageConstants.error.getValue());
				}	
			}
			
		} else {
			if (nameCardService.addOneCard(nc)) {
				rb.setMessage("upload insert success");
				map.put("id", nc.getId());
				map.put("isNotUpdate", "1");
				rb.setData(map);
				rb.setCode(MessageConstants.success.getValue());
			} else {
				rb.setMessage("upload insert fail");
				rb.setCode(MessageConstants.error.getValue());
			}
		}
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removeCard(@RequestBody GetCardParam cp) {
		
		String token = cp.getToken();
		ResultBean rb = new ResultBean();
		String userid;
		if (token != null) {
			token = token.trim();
			userid = RedisUtil.get(token);
			logger.info("token != null and get userid = " + userid);
			if (userid != null) {
				RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), userid);
			} else {
				rb.setCode(0);
				rb.setMessage("token expired");
				return rb;
			}
		} else {
			rb.setCode(0);
			rb.setMessage("upload without token");
			return rb;
		}
System.out.println(userid);
		boolean r = nameCardService.deleteAllCard(Integer.parseInt(userid));
	
		if (r) {
			rb.setCode(1);
			rb.setMessage("remove success");
		}

		return rb;
	}

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public Object countCards(HttpServletRequest req, @RequestBody CountParam cp) {

		String token = req.getParameter("token");
		String userid;
		if (token == null) {
			token = cp.getToken();
		}

		ResultBean rb = new ResultBean();
		if (token != null) {
			token = token.trim();
			userid = RedisUtil.get(token);
			logger.info("token != null and get userid = " + userid);
			if (userid != null) {
				RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), userid);
			} else {
				rb.setCode(0);
				rb.setMessage("token expired");
				return rb;
			}
		} else {
			rb.setCode(0);
			rb.setMessage("upload without token");
			return rb;
		}

		List<Integer> r = nameCardService.countCards(Integer.parseInt(userid));
		try {
			rb.setCode(MessageConstants.success.getValue());
			rb.setMessage("count cards success");
			rb.setData(r);
			return rb;
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("count cards fail");
		}

		System.out.println(r + "");
		return rb;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllCards(HttpServletRequest req, HttpServletResponse res) {

		String token = req.getParameter("token");
		String userid;
		ResultBean rb = new ResultBean();
		if (token != null) {
			token = token.trim();
			userid = RedisUtil.get(token);
			logger.info("token != null and get userid = " + userid);
			if (userid != null) {
				RedisUtil.setex(token, Integer.parseInt(tokenExpiresIn), userid);
			} else {
				rb.setCode(0);
				rb.setMessage("token expired");
				return rb;
			}
		} else {
			rb.setCode(0);
			rb.setMessage("upload without token");
			return rb;
		}

		List<NameCard> an = nameCardService.getAllCards(Integer.parseInt(userid));

		if (!an.isEmpty()) {
			rb.setCode(MessageConstants.success.getValue());
			rb.setData(an);
			rb.setMessage("success");
		} else {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("fail");

		}
		return rb;
	}

	@RequestMapping(value = "/setPhoto", method = RequestMethod.POST)
	@ResponseBody
	public Object setPhoto(@RequestBody SetPhotoParam spp, HttpServletRequest req) {

		ResultBean rb = new ResultBean();
		String photo = spp.getPhoto();

		if (StringUtils.isEmpty(photo)) {
			rb.setCode(0);
			rb.setMessage("upload photo fail , photo can not be empty");
			return rb;
		}
		String filePath = servletContext.getRealPath(photoFolder);
		String filename = IdGen.uuid() + ".png";
		String fileUrl;

		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filePath + filename);

		try {
			// f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);

			byte[] b = Base64.decodeBase64(photo);

			out.write(b);
			out.close();

			logger.info(req.getServerName() + ":" + req.getServerPort() + "/" + req.getServletPath());
			System.out.println(req.getRequestURL());
			System.out.println(servletContext.getRealPath("/"));
			System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath() + photoFolder);

			fileUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
					+ photoFolder + filename;

			rb.setCode(1);
			rb.setMessage("upload photo success");
			rb.setData(fileUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rb;
	}

	@RequestMapping(value = "/setPhotos", method = RequestMethod.POST)
	@ResponseBody
	public Object setPhoto(@RequestBody SetPhotosParams sp, HttpServletRequest req) {

		ResultBean rb = new ResultBean();
			
//		ObjectMapper objectMapper;
		try {
//			String str = IOUtil.getBodyString(req.getReader());
			List<SetPhotosParam> arr = sp.getPhoto();
			
			String filePath = servletContext.getRealPath(photoFolder);

			String filename ; 
			String fileUrl;

			File f = new File(filePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			Map<String, String> map = new HashMap<String, String>();
			String photo;
			FileOutputStream out;
			
			for (SetPhotosParam spp : arr) {
				photo = spp.getContent();
				filename = IdGen.uuid() + ".png";
				f = new File(filePath + filename);
				out = new FileOutputStream(f);
				byte[] b = Base64.decodeBase64(photo);
				out.write(b);
				out.close();
				
				logger.info(req.getServerName() + ":" + req.getServerPort() + "/" + req.getServletPath());
				System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
						+ req.getContextPath() + photoFolder);
				fileUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
						+ req.getContextPath() + photoFolder + filename;
				map.put(spp.getNo(), fileUrl);
			}

			rb.setCode(1);
			rb.setMessage("upload photo success");
			rb.setData(map);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rb;
	}

}
