package com.moving.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moving.dto.UserDto;
import com.moving.service.UserService;
import com.moving.session.SessionInfo;
import com.moving.session.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IndexController indexController;
	
	
	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		
		if(SessionInfo.getUser() != null) {
			return new ModelAndView("redirect:/message");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "register", method = { RequestMethod.GET})
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		
		if(SessionInfo.getUser() != null) {
			return new ModelAndView("redirect:/message");
		}
		
		return mav;
	}
	
	
	
	@RequestMapping(value="verify", method = { RequestMethod.GET, RequestMethod.POST })
	public String verify(@RequestParam String mail, @RequestParam String password, 
			HttpServletRequest request) {
		
		UserDto dto = userService.getUser(mail, password);
		HttpSession session = request.getSession();
		
		if(dto == null) {
			return "redirect:/login";
		} else {
			session.setAttribute("user", User.of(dto));
			return "redirect:/message";
		}
	}
	
	@RequestMapping(value="regist", method = { RequestMethod.GET, RequestMethod.POST })
	public String regist(@RequestParam String mail, @RequestParam String password,
			@RequestParam String password_confirm,
			HttpServletRequest request) {
		if(password != password_confirm && !this.isValidEmailAddress(mail)){
			return null;
		}
		
		if(this.userService.hasUser(mail)) {
			return null;
		}
		
		UserDto dto = userService.addUser(this.getUserNameByMail(mail), password, mail);
		HttpSession session = request.getSession();
		
		if(dto == null) {
			return "redirect:/login";
		} else {
			session.setAttribute("user", User.of(dto));
			return "redirect:/message";
		}
	}
	
	@RequestMapping(value="logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/message";
	}
	
	public String getUserNameByMail(String mail){
		return mail.substring(0, mail.indexOf('@'));
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
		}
	
}
