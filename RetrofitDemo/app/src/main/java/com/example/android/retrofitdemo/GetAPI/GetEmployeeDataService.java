package com.example.android.retrofitdemo.GetAPI;

import com.example.android.retrofitdemo.model.PojoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetEmployeeDataService {

    @GET("retrofit-demo.php")
    Call<PojoList> getEmployeeData(@Query("company_no") int companyNo);

}
