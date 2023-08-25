package com.project.appglee.AppGLEE_DB.repository;

import com.project.appglee.AppGLEE_DB.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    @Query("SELECT A FROM Admin A WHERE A.email=:email AND A.password=:password")
    Optional<Admin> login(String email, String password);
}
