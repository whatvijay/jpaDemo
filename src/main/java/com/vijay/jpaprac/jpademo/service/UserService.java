package com.vijay.jpaprac.jpademo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vijay.jpaprac.jpademo.Entities.User;
import com.vijay.jpaprac.jpademo.dao.UserRepository;


@Service
public class UserService {
 
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService2 us2;
	
	 @PersistenceContext
		EntityManager em2;
	
	public List listOfUsers()
	{
	 return (List) userRepo.findAll();
	}
	
	public List<User> criteriaExample()
	{
		CriteriaBuilder cb=em2.getCriteriaBuilder();
		CriteriaQuery<User> cq=cb.createQuery(User.class);
		Root<User> userRoot= cq.from(User.class);
		cq.distinct(true);
		Predicate pUserName=cb.equal(userRoot.get("userName"), "vijay");
		Predicate pUserID = cb.gt((Expression)userRoot.get("userId"), new Integer(5));
		Predicate[] pa = {pUserName,pUserID};
		cq.where(pa);
		TypedQuery<User> tq= em2.createQuery(cq);
		return tq.getResultList();
	}
	
	public List<User> criteriaEx2(String userName)
	{
		CriteriaBuilder cb2 = em2.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb2.createQuery(User.class);
		Root<User> ru = cq.from(User.class);
		System.out.println(userName);
		Predicate p2=cb2.equal(ru.get("userName"),userName);
		Predicate p3=cb2.ge((Expression)ru.get("userId"),new Integer(6));
		//List<Predicate> predList = new ArrayList();
		//predList.add(p2);
		//predList.add(p3);
		Predicate[] pa = {p3};//(Predicate[]) predList.toArray();
		cq.where(pa);
		TypedQuery<User> tq= em2.createQuery(cq);
		return tq.getResultList();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void methodTransaction()
	{
		System.out.println("user service method transaction");
		us2.methodTranUsr2();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void methodCallingNonSupport()
	{
		System.out.println("methodCallingNonSupport");
		
			us2.methodTransNotSupported();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void rollBackExample(String rollBack) throws Exception
	{
		User persistUser = new User();
		persistUser.setUserName("abcd"+rollBack);
		persistUser.setUserRole("admin");
		persistUser.setPassWord("pwd123");
		em2.persist(persistUser);
		if(rollBack.equals("rollback"))
		{
			throw new Exception();
		}
	}
}
