package com.zdq.sm.controller;

import com.zdq.sm.shiro.Shiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zdq.sm.model.User;
import com.zdq.sm.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
@Autowired
	UserService userService;
@Autowired
Shiro shiro;
	Jedis jedis=new Jedis("127.0.0.1",6379);

    @RequestMapping("/all_user")
    public String all() throws Exception {
        Map<String,Object> param=new HashMap<>();
        List list=userService.getAllUser(param);
        for (Object o:list){
            System.out.println(o);
        }
        return null;
    }

    @RequestMapping("/gaoling")
    public ModelAndView hello123(){
        ModelAndView view=new ModelAndView();
        view.setViewName("jumper");
        return view;
    }

    @RequestMapping("/force")
    public ModelAndView force(){
        ModelAndView view=new ModelAndView();
        view.setViewName("chart/force");
        return view;
    }

    @RequestMapping("/slide")
    public ModelAndView hello1223(){
        ModelAndView view=new ModelAndView();
        view.setViewName("slide");
        return view;
    }

    @RequestMapping("/table")
    public ModelAndView hello3(){
        ModelAndView view=new ModelAndView();
        view.setViewName("table/test");
        return view;
    }

    @RequestMapping("/chart")
    public ModelAndView hello1423(){
        ModelAndView view=new ModelAndView();
        view.setViewName("user");
        return view;
    }
    @RequestMapping("/pie")
    public ModelAndView hello13(){
        ModelAndView view=new ModelAndView();
        view.setViewName("chart/pie");
        return view;
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

	@GetMapping("/logout")
	public ModelAndView logout(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		ModelAndView view=new ModelAndView();
		view.setViewName("login/login");
		return view;
	}


@RequestMapping("/usercheck")
	public ModelAndView control1(String username,String password){
    	Boolean status=shiro.testJdbcRealm(username, password);
		ModelAndView view=new ModelAndView();
		//User user=userService.getUserById(username,password);
		if(status){
			view.setViewName("login/success");
		    return view;
		}else {
			view.addObject("name",username);
			view.addObject("pass",password);
			view.setViewName("login/relogin");
		    return view;
		}
	}

	@RequestMapping("/data")
	public JsonMessage data(){
		Map<String,Object> map=new HashMap<>();
		List<Map<String,Object>> start= null;
		List<Map<String,Object>> end= null;
		try {
			start = userService.getStart_Spot(null);
			end=userService.getStart_End(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("start",start);
		map.put("end",end);
		return new JsonMessage().success(map);
	}

	@RequestMapping("/Piedata")
	public JsonMessage Piedata(){
	Object o=SerializeUtil.unSerialize(jedis.get(SerializeUtil.ObjTOSerialize("data")));
	if(!o.toString().equals("{data=null}")){
	    System.out.println("from redis:  "+o.toString());
		return new JsonMessage().success(o);
	}else{
		Map<String,Object> map=new HashMap<>();
		List<Map<String,Object>> data= null;
		try {
			data=userService.getPie(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("data",data);
		System.out.println("from oracle:  "+data);
		jedis.set(SerializeUtil.ObjTOSerialize("data"),SerializeUtil.ObjTOSerialize(map));
		return new JsonMessage().success(map);
	}
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
