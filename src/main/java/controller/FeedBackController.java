package controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.FeedBack;
import beans.ResultBean;
import param.FeedBackParam;
import service.FeedBackService;
import utils.Global;
import utils.MessageConstants;
import utils.RedisUtil;

@Controller
@RequestMapping("/feed")
public class FeedBackController {
	
	private Logger logger = LoggerFactory.getLogger(FeedBackController.class);
	private static final String tokenExpiresIn = Global.getConfig("token_expires_in");
	
	
	@Autowired
	private FeedBackService feedbackservice;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFeedBack(@RequestBody FeedBackParam fp) {

		String token = fp.getToken();
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
			rb.setMessage("input without token");
			return rb;
		}

		int uid = Integer.parseInt(userid);
		String feed = fp.getFeed();
			
		FeedBack fb = new FeedBack();
		
		fb.setUid(uid);
		if (!StringUtils.isEmpty(feed)) {
			fb.setFeed(feed.trim());
		}
		
		if (feedbackservice.addFeedBack(fb)>0) {
			rb.setCode(MessageConstants.success.getValue());
			rb.setMessage("add feedback success");
			return rb;
		} else {
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("add feedback fail");
			return rb;
		}

	}

}
