package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.FriendRequest;
@Repository
public interface FriendRequestDAO extends JpaRepository<FriendRequest,Integer>{
	List<FriendRequest> findByRequestedTo(String userId);
}
