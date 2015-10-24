package com.moving.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moving.session.SessionInfo;

@Controller
public class InputController {

	@RequestMapping(value = "inventory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView messages() {
		ModelAndView mav = new ModelAndView("inventory");
		
		if(SessionInfo.getUser() != null) {
			mav.addObject("user", SessionInfo.getUser());
		}
		
		return mav;
	}
}
