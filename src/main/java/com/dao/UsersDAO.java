package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {

	Users findByUserId(String userId);

}
