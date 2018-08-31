package com.example.android.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.retrofitdemo.GetAPI.GetEmployeeDataService;
import com.example.android.retrofitdemo.GetAPI.RetrofitInstance;
import com.example.android.retrofitdemo.R;
import com.example.android.retrofitdemo.adapter.AdapterPojoDemon;
import com.example.android.retrofitdemo.model.PojoDemon;
import com.example.android.retrofitdemo.model.PojoList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterPojoDemon adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Create handle for the RetrofitInstance interface*/
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<PojoList> call = service.getEmployeeData(100);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PojoList>() {
            @Override
            public void onResponse(Call<PojoList> call, Response<PojoList> response) {
                generateEmployeeList(response.body().getPojoDemons());
            }

            @Override
            public void onFailure(Call<PojoList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<PojoDemon> empDataList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new AdapterPojoDemon(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
