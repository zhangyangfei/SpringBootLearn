package com.zyf.springMVC.mvcuploadfile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * mvc文件上传 
 * MultipartHttpServletRequest接口 
 * MultipartFile接口 
 * Part接口
 */
@Controller
@RequestMapping(value = { "/mvcuploadfile" })
public class MvcUploadFileController {

	/** 跳转到文件上传页面 */
	@RequestMapping("/muf1")
	public String muf1() {
		return "mvcuploadfile/muf1";
	}

	/**
	 * 上传文件MultipartHttpServletRequest接口
	 */
	@RequestMapping("/muf2")
	@ResponseBody
	public Map<String, Object> muf2(HttpServletRequest reqeust) {
		MultipartHttpServletRequest msr = null;
		if (reqeust instanceof MultipartHttpServletRequest) {
			msr = (MultipartHttpServletRequest) reqeust;
		} else {
			return dealResultMap(false, "上传失败");
		}
		MultipartFile multipartFile = msr.getFile("file");//注意从请求中取"file"参数
		String fileName = multipartFile.getOriginalFilename();
		File file = new File(fileName);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}

	/**
	 * 上传文件MultipartHttpServletRequest接口 
	 *  注意从请求中取"file"参数
	 */
	@RequestMapping("/muf3")
	@ResponseBody
	public Map<String, Object> muf3(@RequestParam(value = "file") MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		File file = new File(fileName);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}

	/**
	 * 上传文件Part接口
	 * 	注意从请求中取"file"参数
	 */
	@RequestMapping("/muf4")
	@ResponseBody
	public Map<String, Object> muf4(@RequestParam(value = "file") Part partFile) {
		String fileName = partFile.getSubmittedFileName();
		try {
			partFile.write(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}

	private Map<String, Object> dealResultMap(Boolean issuccess, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issuccess", issuccess);
		map.put("msg", msg);
		return map;
	}
}
