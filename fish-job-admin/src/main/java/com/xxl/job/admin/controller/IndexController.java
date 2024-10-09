package com.xxl.job.admin.controller;

import com.fish.common.core.util.ReturnT;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.service.LoginService;
import com.xxl.job.admin.service.XxlJobService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * index controller
 *
 * @author xuxueli
 * @since 2015-12-19 16:13:16
 */
@Controller
public class IndexController {

	@Resource
	private XxlJobService xxlJobService;

	@Resource
	private LoginService loginService;

	@RequestMapping("/")
	public String index(Model model) {

		Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
		model.addAllAttributes(dashboardMap);

		return "index";
	}

	@RequestMapping("/chartInfo")
	@ResponseBody
	public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {
		return xxlJobService.chartInfo(startDate, endDate);
	}

	@RequestMapping("/toLogin")
	@PermissionLimit(limit = false)
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
		if (loginService.ifLogin(request, response) != null) {
			modelAndView.setView(new RedirectView("/", true, false));
			return modelAndView;
		}
		return new ModelAndView("login");
	}

	@PostMapping("/login")
	@ResponseBody
	@PermissionLimit(limit = false)
	public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName,
			String password, String ifRemember) {
		boolean ifRem = ifRemember != null && !ifRemember.trim().isEmpty() && "on".equals(ifRemember);
		return loginService.login(request, response, userName, password, ifRem);
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	@PermissionLimit(limit = false)
	public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
		return loginService.logout(request, response);
	}

	@RequestMapping("/help")
	public String help() {

		/*
		 * if (!PermissionInterceptor.ifLogin(request)) { return "redirect:/toLogin"; }
		 */

		return "help";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
