package com.pzd.repository;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByName(String username);

	public User findByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.name = :name")
	public User getUserByUsername(@Param("name") String name);

	@Transactional
	@Modifying
	@Query("update Users u set u.name = :name , u.contactNumber = :contact , u.address = :address where u.email = :email ")
	public void updateUserByEmail(@Param("email") String email, @Param("name") String name,
			@Param("contact") long contact, @Param("address") String address);

	@Query("SELECT count(*) from Users u where u.role LIKE '%ROLE_USER%'")
	public int getCustomerCount();

	@Query("SELECT u.id , u.name, u.email , u.contactNumber, u.role from Users u")
	public ArrayList<String> getCustomerNameList();
	
//	@Query("SELECT  (SELECT COUNT(*) FROM Users u WHERE u.role LIKE '%ROLE_USER%') AS userCount,   COUNT(DISTINCT p_id) AS productCount,   COUNT(DISTINCT categoryld) AS categoryCount FROM Users, product, category")
	@Query("SELECT  COUNT(DISTINCT id) AS userCount,   COUNT(DISTINCT p_id) AS productCount,   COUNT(DISTINCT categoryld) AS categoryCount FROM Users, product, category")
	public String getCountOfCustomerProductCategory();

	
	@Transactional
	@Modifying
	@Query("update Users u set u.password = :password where u.email = :email")
	public void updatePassword(@Param("email") String email, @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query("update Users u set u.contactNumber = :contactNumber , u.address = :address where u.email = :email")
	public void updateUserContactAndAddress(@Param("email") String email,@Param("contactNumber") long contact,@Param("address") String addresss);

}
