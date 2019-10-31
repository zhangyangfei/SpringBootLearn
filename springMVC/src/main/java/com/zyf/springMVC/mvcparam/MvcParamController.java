package com.zyf.springMVC.mvcparam;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * springmvc 获取url参数
 */
@Controller
@RequestMapping(path = { "/mvcparam" })
public class MvcParamController {

	/**
	 * 无注解获取参数 
	 * 	控制器方法参数与url参数必须同名才能获取到参数。
	 *  url参数可以为空，但int参数不能为空，转换为null赋值给int或报错。
	 *  http://localhost:8080/mvcparam/mp1?id=2
	 */
	@RequestMapping(value = { "/mp1" })
	public ModelAndView mp1(String name, int id, Integer age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("name", name);
		mv.setViewName("mvcparam/mp1");
		return mv;
	}
	
	/**
	 * 注解@RequestParam获取参数 
	 *  
	 *  http://localhost:8080/mvcparam/mp2?int_id=2&str_name=zhangsan&integer_age=99
	 *  http://localhost:8080/mvcparam/mp2?int_id=2&str_name=zhangsan
	 */
	@RequestMapping(value = { "/mp2" })
	public ModelAndView mp2(
			@RequestParam("str_name") String name,//注解参数要与url中的一致
			@RequestParam(value = "int_id", required = false) int id,// required的默认值是true，改成false表名不是必须参数
			@RequestParam(value = "integer_age", defaultValue = "100") Integer age) {//如果url没有有该参数，则使用defaultValue给该参数默认值
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("mvcparam/mp1");
		return mv;
	}
	
	/**
	 * 获取数组参数 
	 *  url中数组元素用逗号隔开，控制器中用数组类型接收
	 *  http://localhost:8080/mvcparam/mp3?id=2,3,4&name=zhangsan,lisi&age=99,100,101
	 */
	@RequestMapping(value = { "/mp3" })
	public ModelAndView mp3(int[] id, String[] name, Integer[] age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", Arrays.toString(id));
		mv.addObject("name", Arrays.toString(name));
		mv.addObject("age", Arrays.toString(age));
		mv.setViewName("mvcparam/mp1");
		return mv;
	}
	
	/**
	 * url传递参数 
	 * 	@RequestMapping占位符{}与@PathVariable的value相等，组合获取url的参数。
	 *  有占位符则url参数必填。
	 */
//	@RequestMapping(value = { "/mp4/{int_id}" }) // http://localhost:8080/mvcparam/mp4/2
	@RequestMapping(value = { "/mp4/{int_id}/{string_name}/{integer_age}" }) // http://localhost:8080/mvcparam/mp4/2/wangwu/18
	public ModelAndView mp4(
			@PathVariable(value = "int_id") int id, 
			@PathVariable(value = "string_name") String name,
			@PathVariable(value = "integer_age") Integer age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("mvcparam/mp1");
		return mv;
	}
	
	/**
	 * 获取格式化参数 
	 */
	@RequestMapping(value = { "/mp5" }) // http://localhost:8080/mvcparam/mp5?btd=2019-11-06&dtt=2018-01-06T01:30:59.123-05:00&money=293,884.453
	public ModelAndView mp5(
			@DateTimeFormat(iso = ISO.DATE) Date btd,//字符串转换为date
			@DateTimeFormat(iso = ISO.DATE_TIME) Date dtt,//字符串转换为date time
			@NumberFormat(pattern = "#,###.##") double money//货币字符串转换为double
			) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("btd", btd);
		mv.addObject("dtt", dtt);
		mv.addObject("money", money);
		mv.setViewName("mvcparam/mp1");
		return mv;
	}
	
	/**
	 * 获取json参数-1：跳转到mp6界面
	 */
	@RequestMapping(value = { "/mp6" }) 
	public ModelAndView mp6() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mvcparam/mp6");
		return mv;
	}
	
	/**
	 * 获取json参数-2
	 */
	@RequestMapping(value = { "/mp7" })
	@ResponseBody//@ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】。
	public User mp7(@RequestBody User user) {//@RequestBody表示通过User接收前段的json请求，且二者属性名称必须保持一致，否则无法接收到对应属性的数据。
		System.out.println(user);
		return user;
	}
}
