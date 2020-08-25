package com.vijay.jpaprac.jpademo.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService3 {

	@Transactional(propagation=Propagation.NEVER)
	public void methodUseTgatrans()
	{
		System.out.println("Inside userService3 never transaction");
		
	}
	
}
