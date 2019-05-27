package com.zdq.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.recompile;
import com.zdq.sm.model.User;
import com.zdq.sm.service.UserService;

@RestController
public class LoginController {
@Autowired
	UserService userService;
	
@RequestMapping("/usercheck")
	public ModelAndView control1(String username,String password){
		ModelAndView view=new ModelAndView();
		User user=userService.getUserById(username,password);
		if(user!=null){
			view.setViewName("login/success");
		    return view;
		}else {
			User a=new User();
			a.setName(username);
			view.addObject(a);
			view.setViewName("login/relogin");
		    return view;
		}

	}

@RequestMapping("/login")
public ModelAndView hello(){
	ModelAndView view=new ModelAndView();
	view.setViewName("login/login");
    return view;
}

@RequestMapping("/register")
public ModelAndView hello1(){
	ModelAndView view=new ModelAndView();
	view.setViewName("login/register");
    return view;
}

@RequestMapping("/doregister")
public ModelAndView doregister(@RequestParam("username") String username,@RequestParam("password")String password){ //注册
	User user=new User();
	user.setName(username);
	user.setPassword(password);
	System.out.println(username);
	System.out.println(password);
	userService.addUser(user);
	ModelAndView view=new ModelAndView();
	view.setViewName("login/login");
	return view;
}
}
