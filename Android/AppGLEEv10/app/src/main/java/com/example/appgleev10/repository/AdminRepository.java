package com.example.appgleev10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.AdminAPI;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Admin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepository {

    private static AdminRepository adminRepository;
    private final AdminAPI adminAPI;

    public AdminRepository() {
        this.adminAPI = ConfigAPI.getAdminAPI();
    }

    public static AdminRepository getInstance() {
        if (adminRepository == null) {
            adminRepository = new AdminRepository();
        }
        return adminRepository;
    }

    public LiveData<GenericResponse<Admin>> login(String email, String password) {
        final MutableLiveData<GenericResponse<Admin>> mutableLiveData = new MutableLiveData<>();
        this.adminAPI.login(email, password).enqueue(new Callback<GenericResponse<Admin>>() {
            @Override
            public void onResponse(Call<GenericResponse<Admin>> call, Response<GenericResponse<Admin>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Admin>> call, Throwable t) {
                mutableLiveData.setValue(new GenericResponse());
                System.out.println("ERROR IN LOGIN: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<GenericResponse<Admin>> save(Admin admin) {
        final MutableLiveData<GenericResponse<Admin>> mld = new MutableLiveData<>();
        this.adminAPI.save(admin).enqueue(new Callback<GenericResponse<Admin>>() {
            @Override
            public void onResponse(Call<GenericResponse<Admin>> call, Response<GenericResponse<Admin>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Admin>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Error detected! " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
