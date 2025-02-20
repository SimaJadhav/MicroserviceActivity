package com.ibm.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ibm.ms.model.UserCredential;

@Component
public interface AuthenticationRepository extends JpaRepository<UserCredential, String>{

}
