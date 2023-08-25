package com.example.appgleev10.api;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.DocStored;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DocStroredAPI {

    String base = "/docstored";

    @Multipart
    @POST(base)
    Call<GenericResponse<DocStored>> save(@Part MultipartBody.Part file,
                                          @Part("name")RequestBody requestBody);


}
