package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM User WHERE role_id = :roleId")
    List<User> findAllByRoleId(@Param("roleId") Integer roleId);


    @Query(nativeQuery = true, value = "SELECT * FROM User WHERE email LIKE %:email%")
    List<User> findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM User WHERE username = :username")
    User findByUsername(@Param("username") String username);
}
