package com.vijay.jpaprac.jpademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService2 {

	@Autowired
	UserService3 us3;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void methodTranUsr2()
	{
		System.out.println("user2 trans is called it can be called directly");
		//us3.methodUseTgatrans();
		// since it used never in propogation it throwed error.
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void methodTransRequired()
	{
		System.out.println("user2 trans is called it can be called directly");
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void methodTransMandatory()
	{
		System.out.println("mandatory transaction cannot be called directly");
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void methodTransNotSupported()
	{
		System.out.println("not supported ");
	}
	
	
}
