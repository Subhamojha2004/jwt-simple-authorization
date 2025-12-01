package com.example.jwt_auth_with_authorisation.repository;

import com.example.jwt_auth_with_authorisation.entity.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepo extends CrudRepository<MyUser,Long> {

    // for user detials service
    Optional<MyUser> findByUsername(String username);
}
