package com.yaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yaha.model.User;



public interface UserRepository extends JpaRepository<User,Long> {
	
	public User findByEmail (String email);

}
