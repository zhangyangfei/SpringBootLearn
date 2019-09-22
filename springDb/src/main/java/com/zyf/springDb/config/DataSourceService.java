package com.zyf.springDb.config;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DataSourceService implements ApplicationContextAware {
	ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
		DataSource ds = applicationContext.getBean(DataSource.class);
		System.out.println("数据库连接池：" + ds.getClass().getName());
	}
}
