package com.vijay.jpaprac.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.vijay.jpaprac.jpademo.Entities.User;
import com.vijay.jpaprac.jpademo.dao.UserJpaRepository;
import com.vijay.jpaprac.jpademo.dao.UserRepository;
import com.vijay.jpaprac.jpademo.service.UserService;
import com.vijay.jpaprac.jpademo.service.UserService2;
import com.vijay.jpaprac.jpademo.service.UserService3;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
public class JpademoApplication implements CommandLineRunner{

	@Autowired
	UserService us;
	
	@Autowired
	UserService2 us2;
	
	@Autowired
	UserService3 us3;
	
	@Autowired
	UserJpaRepository userJpa;
	
	@Autowired
	UserRepository ur;
	public static void main(String[] args) {
		SpringApplication.run(JpademoApplication.class, args);
		//UserService us = new UserService();
		//us.listOfUsers().forEach(System.out::println);
		
	}
	
	//@Override
	public void run(String ...args)
	{
		//UserService us = new UserService();
			Iterable<User> iterable=	ur.findAll();
			Iterator<User> iterator = iterable.iterator();
			while(iterator.hasNext())
			{
				User u = iterator.next();
				System.out.println(u.getUserId()+":"+u.getUserName());
			}
			us.listOfUsers().forEach(System.out::println);
			
			System.out.println(ur.count());
		
			System.out.println(ur.existsById(17));
			
			User u17 = new User();
			u17.setUserId(17);
			ur.delete(u17);
			System.out.println(ur.existsById(17));
			User u18 = ur.findById(18).get();
			System.out.println(u18.getUserId());
			
			Iterator<User> iteratorJpa =userJpa.findAll(Sort.by(Sort.Direction.DESC,"userId")).iterator();
			while(iteratorJpa.hasNext())
			{
				System.out.println(iteratorJpa.next().getUserId());
			}
			//userJpa.findOne(u18)
			System.out.println(userJpa.equals(u18));
			Example<User> ex = Example.of(u18);
			System.out.println(userJpa.findOne(ex).get().getUserId());
			//userJpa.findAllById(ids)
			Pageable pg = PageRequest.of(0, 5);
			Iterator<User> pgi=userJpa.findAll(pg).iterator();
			System.out.println("pageination results");
			while(pgi.hasNext())
			{
				System.out.println(pgi.next().getUserId());
			}
			User ucf=ur.customFind(8);
			System.out.println(ucf.getUserId()+":"+ucf.getUserName());
			
			User ucfN1 = ur.customFindNative(8);
			System.out.println(ucfN1.getUserId()+":"+ucfN1.getUserName());
			
			Iterator<User> ci=us.criteriaExample().iterator();
			while(ci.hasNext())
			{
				System.out.println(ci.next().getUserId());
			}
			System.out.print("criteria one completed");
			us.methodTransaction();
			us3.methodUseTgatrans();// when directly called it didn't throw error uses never
			us2.methodTranUsr2();
			us2.methodTransRequired();
			try {
			us2.methodTransMandatory();//exact opposite of never it cannot be called directly
			
			}
			catch(Exception e)
			{
			
			}
			us.methodCallingNonSupport();
			try
			{
			us.rollBackExample("rollBack");
			}
			catch(Exception e)
			{
				
			}
			try {
				us.rollBackExample("persist");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//us.criteriaEx2("ajay")
		/*Iterator<User> cige= us.criteriaEx2("ajay").iterator();
			while(cige.hasNext())
			{
				System.out.println(ci.next().getUserId());
			}*/
	}
}
