package com.vijay.jpaprac.jpademo.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.
import org.springframework.stereotype.Repository;

import com.vijay.jpaprac.jpademo.Entities.User;
@Repository
public interface UserRepository extends CrudRepository<User,Integer>{

	
	
	@Query("from User where UserId=:uid")
	public User customFind(@Param("uid")Integer uid);
	
	//observe the table name difference
	
	@Query(value="select * from Userh where UserId=:uid",nativeQuery=true)
	public User customFindNative(@Param("uid")Integer uid);
	
	
}
