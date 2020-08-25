package com.vijay.jpaprac.jpademo.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBTransConfig {
	
	 //@PersistenceContext
		//EntityManager em2;
	 
	 @Bean("transactionManager")
	 public PlatformTransactionManager returnTransManager()
	 {
		 JpaTransactionManager jptm = new JpaTransactionManager();
		 jptm.setEntityManagerFactory(new LocalEntityManagerFactoryBean().getObject());
		 
		 return jptm;
	 }

}
