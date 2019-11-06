package com.zyf.springMVC.international;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 添加国际化解析器和拦截器
 */
@Configuration
public class InternationalConfig implements WebMvcConfigurer {

	// 国际化解析器，bean的名称必须用"localeResolver"
	@Bean("localeResolver")
	public LocaleResolver initLocaleResolver() {
		// 可以将国际化信息设置在session中;比其他解析器灵活，用户可以选择国际化区域。
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);// 配置默认国际化区域为简体中文
		// slr.setDefaultLocale(Locale.US);// 配置默认国际化区域为美国英文
		return slr;
	}

	// 国际化拦截器
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Bean
	public LocaleChangeInterceptor initLocaleChangeInterceptor() {
		if (null != localeChangeInterceptor) {
			return localeChangeInterceptor;
		}
		localeChangeInterceptor = new LocaleChangeInterceptor();
		// 设置拦截的参数，该参数的值将作为国际化区域（语言）
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	// 注册国际化拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initLocaleChangeInterceptor());
	}
}
