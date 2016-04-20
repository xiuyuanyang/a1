package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.NameCard;
import beans.ResultBean;
import service.NameCardService;
import utils.MessageConstants;

@Controller
@RequestMapping("/card")
public class CardController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	private NameCardService nameCardService;

	@RequestMapping("/get")
	@ResponseBody
	public Object getCard(HttpServletRequest req, HttpServletResponse res) {

		int id = Integer.parseInt(req.getParameter("id"));
		int uid = Integer.parseInt(req.getParameter("uid"));
		NameCard nc = nameCardService.getOneCard(id, uid);
		ResultBean rb = new ResultBean();

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
	public Object addCard(HttpServletRequest req, HttpServletResponse res, @RequestBody NameCard nc) {

		ResultBean rb = new ResultBean();

		if (nameCardService.addOneCard(nc)) {
			rb.setMessage("upload success");
			rb.setCode(MessageConstants.success.getValue());
		} else {
			rb.setMessage("upload fail");
			rb.setCode(MessageConstants.error.getValue());
		}
		return rb;

	}

	@RequestMapping("/modify")
	@ResponseBody
	public Object updateNameCard(HttpServletRequest req, HttpServletResponse res) {
		// PrintWriter out = null;
		// NameCard nc = nameCardService.getOneCard(1 , 1);
		// boolean r = nameCardService.modifyOneCard(nc);
		// try {
		// out = res.getWriter();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// out.write(r + "modify ");
		// out.flush();
		// out.close();
		// System.out.println(r);
		// return "index";
		return null;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public String removeCard(HttpServletRequest req, HttpServletResponse res) {
		PrintWriter out = null;
		boolean r = nameCardService.deleteOneCard(2, 1);
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(r + "remove ");
		out.flush();
		out.close();
		System.out.println(r);
		return "index";
	}

	@RequestMapping("/count")
	@ResponseBody
	public Object countCards(HttpServletRequest req, HttpServletResponse res) {
//		PrintWriter out = null;		
		ResultBean rb = new ResultBean();
		List<Integer> r = nameCardService.countCards(1);
		try {
//			out = res.getWriter();	
			rb.setCode(MessageConstants.success.getValue());
			rb.setMessage("count cards success");
			rb.setData(r);
			return rb;
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(MessageConstants.error.getValue());
			rb.setMessage("count cards fail");
		}

		System.out.println(r+"");
		return rb;
	}

	@RequestMapping("/all")
	@ResponseBody
	public Object getAllCards(HttpServletRequest req, HttpServletResponse res) {

		List<NameCard> an = nameCardService.getAllCards(1);

		ResultBean rb = new ResultBean();
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

	@RequestMapping("/setPhoto")
	@ResponseBody
	public Object setPhoto(HttpServletRequest req, HttpServletResponse res) {

		ResultBean rb = new ResultBean();

		String savePath = servletContext.getRealPath("/WEB-INF/upload");

		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + " not there ");
			file.mkdir();
		}

		String message = "";

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setHeaderEncoding("UTF-8");

		if (!ServletFileUpload.isMultipartContent(req)) {
			return rb;
		}

		List<FileItem> list;
		try {
			list = upload.parseRequest(req);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
				} else {
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;

					}

					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					message = "file upload success";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rb;

	}

}
