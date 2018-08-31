package com.example.android.requestapi.ResquetAPI;

import com.example.android.requestapi.Model.PojoRequestList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetResquetAPI {

    @GET("api/users?page")
    Call<PojoRequestList> getListaRequest();

}
