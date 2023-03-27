package com.pzd.repository;

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
	public void updateUserByEmail(@Param("email") String email,@Param("name") String name,@Param("contact") int contact,@Param("address") String address);
	
	
}
