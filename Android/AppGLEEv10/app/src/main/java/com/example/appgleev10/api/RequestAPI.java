package com.example.appgleev10.api;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Request;
import com.example.appgleev10.entity.service.dto.GenereteRequestDTO;
import com.example.appgleev10.entity.service.dto.RequestWithDetailDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface RequestAPI {
    String base = "/request";

    @GET(base + "/myRequests/{idVis}")
    Call<GenericResponse<List<RequestWithDetailDTO>>> listRequestsByVisitant(@Path("idVis") int idVis);

    @POST(base)
    Call<GenericResponse<GenereteRequestDTO>> saveRequest(@Body GenereteRequestDTO dto);

    @DELETE(base + "/{id}")
    Call<GenericResponse<Request>> cancelRequest(@Path("id") int id);

    @Streaming
    @GET(base + "/exportInvoice")
    Call<ResponseBody> exportInvoicePDF(@Query("idVisitant") int idVis, @Query("idOrden") int idOrden);
}
