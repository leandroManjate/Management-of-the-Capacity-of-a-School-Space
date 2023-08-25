package com.project.appglee.AppGLEE_DB.service;
import com.project.appglee.AppGLEE_DB.entity.Admin;
import com.project.appglee.AppGLEE_DB.repository.AdminRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    //Method to start Session
    public GenericResponse<Admin> login(String email, String password){
        Optional<Admin> optAdmin = this.adminRepository.login(email, password);
        if(optAdmin.isPresent()){
            return new GenericResponse<Admin>(TYPE_AUTH, RESP_OK, "Login success!", optAdmin.get());
        }else{
            return new GenericResponse<Admin>(TYPE_AUTH, RESP_WARNING, "User not found!", new Admin());
        }
    }
    //Method to save credentials of Admin
    public GenericResponse saveAdmin(Admin admin){
        Optional<Admin> optAdmin = this.adminRepository.findById(admin.getId());
        int idf = optAdmin.isPresent() ? optAdmin.get().getId() : 0;
        if(idf == 0){
            return new GenericResponse(TYPE_DATA, RESP_OK, "Admin correctly registred!", this.adminRepository.save(admin));
        }else{
            return new GenericResponse(TYPE_DATA, RESP_OK, "Updated Admin's data!", this.adminRepository.save(admin));
        }
    }

}
