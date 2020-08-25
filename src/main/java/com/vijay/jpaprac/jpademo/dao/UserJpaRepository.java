package com.vijay.jpaprac.jpademo.dao;
//org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.jpaprac.jpademo.Entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer> {

}
