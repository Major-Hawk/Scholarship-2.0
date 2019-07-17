package com.nsp.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nsp.dao.StateDao;

import com.nsp.model.StateLogin;

@Controller
public class StateController {

	
	@Autowired
	StateDao statedao;
	
	@RequestMapping("/StateLogin")
	public ModelAndView statelogin() {
		return new ModelAndView("StateLogin");
		
	}
	
	/**
	 * 
	 * @param st
	 * @return
	 */
	@RequestMapping("/state_login")
	public ModelAndView StateLogin(@ModelAttribute StateLogin st,HttpServletRequest request,HttpServletResponse response)

	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		System.out.println(" Check State Atrribute : "+st.getGstate_name());
		
		
		
		HttpSession session=request.getSession(true);
		session.setAttribute("str", st.getGstate_name());
		
		String str=(String)session.getAttribute("str");
		
		if(st.getGstate_password().equals(statedao.getpassword(st.getGstate_name())))
		{ 
			                                              
			System.out.println("Login Session : "+str);
			System.out.println("State login successful."); 	
			return new ModelAndView("state dashboard"); 
			
		}
		  else
			  {
			  	return new ModelAndView("index");	
			  }
	}
	
	@RequestMapping(value="/state_logout",method=RequestMethod.GET)
	  public String logout(HttpSession session) {
	    
	    String str=(String)session.getAttribute("str");
	    System.out.println("Cheking Session : "+str);
	    session.removeAttribute("str");
	    session.invalidate();
	    System.out.println("Cheking Session : "+str);
	    return "index";
	  }
	
}		
