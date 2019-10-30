package com.zyf.springMVC.mvcrequestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 映射关系注解@RequestMapping的用法
 */
@Controller
// @RequestMapping("/mvcrequestmapping")
// @RequestMapping(value="/mvcrequestmapping")
@RequestMapping(
		name = "reqmapping", // 指定映射的名称
		// path = "/mvcrequestmapping", // 指定请求的路径映射
		value = "/mvcrequestmapping", // 指定请求的路径映射
		method = RequestMethod.GET, // 限定请求方法为get
		params = { "id!=1", "name" }, // 请求参数中必须包含id和name,且蚕参数id的值不能为1
		headers = { "Accept-Encoding=gzip, deflate, br" }, // 请求头部必须包含的参数
		// consumes = "text/html",// 限定请求的提交内容类型
		produces = "text/html"// 限定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
)
public class MvcRequestMappingController {

	@RequestMapping(value={"/*/rm1","/rm2"})//匹配多个路径，且其中一个匹配任意一个目录
	public ModelAndView mvc1(int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		modelAndView.setViewName("mvcrequestmapping/rm1");
		return modelAndView;
	}
}
