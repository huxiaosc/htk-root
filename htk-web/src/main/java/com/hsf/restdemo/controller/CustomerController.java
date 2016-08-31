package com.hsf.restdemo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hsf.restdemo.service.CustomerService;
import com.hsf.restdemo.utils.CookieTool;

@Controller
@Scope("prototype")
@RequestMapping("/s")
public class CustomerController extends BaseController {
	@Resource
	private CustomerService customerService;
	
	private int i=0;
	
	
	/**
	 * query 获取分类来源
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	@ResponseBody
	public String customers(HttpServletRequest request, ModelMap model) {
		model.addAttribute(customerService.list());
		model.addAttribute(new Integer[]{1,2,3,4,5});
		
		return "{1,2,3,4,5}";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(HttpServletRequest request, ModelMap model) {
		model.addAttribute("i:"+i++);
		model.addAttribute(customerService.list());
	}
	
	@RequestMapping(value = "/setCookie", method = RequestMethod.GET)
	public void setCookie(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CookieTool.addCookie(response, "hx", "yuanbao", 10);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		model.addAttribute("customers",customerService.list());
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
