package com.heylhh.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
		return "admin/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
		return "admin/register";
	}

	/**
	 * 单纯的页面跳转
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/page/{name}", method = RequestMethod.GET)
	public String page(@PathVariable String name) {
		return "admin/"+name;
	}

	/**
	 * 单纯的页面跳转
	 *
	 * 页面名称，即jsp文件名
	 *
	 * @return
	 */
	@RequestMapping(value = "/page/{model}/{fun}", method = RequestMethod.GET)
	public String page(@PathVariable String model, @PathVariable String fun) {
		return model + "/" + fun;
	}

	/**
	 * 单纯的页面跳转
	 *
	 * 页面名称，即jsp文件名
	 *
	 * @return
	 */
	@RequestMapping(value = "/page/{model}/{fun}/{file}", method = RequestMethod.GET)
	public String page(@PathVariable String model, @PathVariable String fun, @PathVariable String file) {
		return model + "/" + fun + "/" + file;
	}

}
