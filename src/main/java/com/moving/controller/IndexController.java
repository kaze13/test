package com.moving.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moving.session.SessionInfo;

@Controller
public class IndexController {

	@RequestMapping(value = "message", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView messages() {
		ModelAndView mav = new ModelAndView("index1");
		
		if(SessionInfo.getUser() != null) {
			mav.addObject("user", SessionInfo.getUser());
		}
		
		return mav;
	}
}
