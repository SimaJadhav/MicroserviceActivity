package com.ibm.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ms.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {

}
