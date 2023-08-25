package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.repository.AdminRepository;

import org.jetbrains.annotations.NotNull;

public class AdminViewModel extends AndroidViewModel {

    private final AdminRepository adminRepository;

    public AdminViewModel(@NonNull @NotNull Application application) {
        super((application));
        this.adminRepository = AdminRepository.getInstance();
    }
    public LiveData<GenericResponse<Admin>> login(String email, String password) {
        return this.adminRepository.login(email, password);
    }

    public LiveData<GenericResponse<Admin>> save(Admin admin){
        return this.adminRepository.save(admin);
    }
}
