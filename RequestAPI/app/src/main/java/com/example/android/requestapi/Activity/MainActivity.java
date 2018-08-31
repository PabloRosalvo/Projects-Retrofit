package com.example.android.requestapi.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.android.requestapi.Model.PojoRequest;
import com.example.android.requestapi.Model.PojoRequestList;
import com.example.android.requestapi.R;
import com.example.android.requestapi.RequestAdapter.AdapterRequest;
import com.example.android.requestapi.ResquetAPI.ClientRequest;
import com.example.android.requestapi.ResquetAPI.GetResquetAPI;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private AdapterRequest adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetResquetAPI service = ClientRequest.getClientRequest().create(GetResquetAPI.class);

        Call<PojoRequestList> call = service.getListaRequest();
        call.enqueue(new Callback<PojoRequestList>() {
            @Override
            public void onResponse(Call<PojoRequestList> call, Response<PojoRequestList> response) {
            generateResquestList(response.body().getData());


            }

            @Override
            public void onFailure(Call<PojoRequestList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void generateResquestList(ArrayList<PojoRequest> resquetListAPI) {

        recyclerView = (RecyclerView) findViewById(R.id.recylerview);

        adapter = new AdapterRequest(resquetListAPI, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
