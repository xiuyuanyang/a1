package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.ResultBean;
import utils.Global;

@Controller
@RequestMapping("/info")
public class InfoController {

	private Logger logger = LoggerFactory.getLogger(CardController.class);

	@RequestMapping(value = "/version")
	@ResponseBody
	public Object getVersion(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getLocalName());
		System.out.println(req.getLocalAddr());
		System.out.println(req.getServerName());
		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("version_info"));
		return rb;

	}

	@RequestMapping(value = "/iosLink")
	@ResponseBody
	public Object getIosDownloadLink(HttpServletResponse res) {

		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("ios_download_link"));
		return rb;

	}

	@RequestMapping(value = "/androidLink")
	@ResponseBody
	public Object getAndroidDownloadLink(HttpServletResponse res) {

		ResultBean rb = new ResultBean();
		rb.setCode(1);
		rb.setMessage("get version success");
		rb.setData(Global.getConfig("android_download_link"));
		return rb;

	}

	@RequestMapping(value = "/getAppID/{AppType}")
	@ResponseBody
	public Object getAppID(@PathVariable String AppType) {

		ResultBean rb = new ResultBean();
		String r = AppType.toLowerCase();
		String s;
		switch (r) {
		case "weixinappid":
			s = r;
			break;
		case "qqappid":
			s = r;
			break;
		case "weiboappkey":
			s = r;
			break;
		default:
			rb.setCode(0);
			rb.setMessage("get AppID fail , no such APP type");
			return rb;
		}

		rb.setCode(1);
		rb.setMessage("get AppID success");
		rb.setData(Global.getConfig(s));
		return rb;

	}

}
