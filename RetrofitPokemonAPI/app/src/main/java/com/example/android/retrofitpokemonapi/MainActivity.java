package com.example.android.retrofitpokemonapi;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.retrofitpokemonapi.models.Pokemon;
import com.example.android.retrofitpokemonapi.models.PokemonResposta;
import com.example.android.retrofitpokemonapi.podeapi.PokemonApiService;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "POKEMON API ON";

    private Retrofit retrofit;

    private RecyclerView recyclerView;

    private PokemonAdapter pokemonAdapter;

    private int offset;

    private boolean aptoParaCarregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy > 0 ){
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCoumt = layoutManager.getChildCount();
                        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCarregar ) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCoumt) {
                            Log.i(TAG, "Chegamos ao final");
                            aptoParaCarregar = false;
                            offset += 20;
                            obterDados(offset);

                        }
                    }
                    }
                }
            });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCarregar = true;
        offset = 0;
        obterDados(offset);
        }



    private void obterDados(int offset){


        PokemonApiService service = retrofit.create(PokemonApiService.class);
        Call <PokemonResposta> pokemonRespostaCall = service.obterListaPokemon(40, offset);

        pokemonRespostaCall.enqueue(new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                aptoParaCarregar = true;
                if (response.isSuccessful()){

                    PokemonResposta pokemonResposta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonResposta.getResults();

                    pokemonAdapter.adicionarListaPokemon(listaPokemon);
                    //for(int i=0; i < listaPokemon.size(); i++){
                    //Pokemon p = listaPokemon.get(i);
                    //Log.i(TAG,"Pokemon :" + p.getName());
                    //}

                } else {
                    Log.e(TAG, "onResponse"+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {
                aptoParaCarregar = true;
                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });

    }
}
