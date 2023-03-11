package com.pzd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from Users u Where u.email =:email")
//	public User findByUsername(@Param("email") String email);
	public User findByName(String username);

}