package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import beans.NameCard;
import service.NameCardService;


@Controller  
@RequestMapping("/card")  
public class CardController {
	
    @Autowired
    private NameCardService nameCardService;
	
	@RequestMapping("/get") 
	public String getCard(HttpServletRequest req,HttpServletResponse res) {
		PrintWriter	out = null;
		int id =1 ;
	//	int id = Integer.parseInt(req.getParameter("id"));
		String result = "";
		NameCard nc = nameCardService.getOneCard(id);
		result = new JSONObject(nc).toString();
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(result);
		out.flush();
		out.close();
		System.out.println(result);
		return "index";		
	}
	
}
