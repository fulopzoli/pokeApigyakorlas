package com.example.as.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.as.Model.Pokemon;
import com.example.as.Model.ResultsEntity;
import com.example.as.Service.PokeApi;
import com.example.as.app;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainRepository {

    @Inject
    Retrofit retrofit;
    private PokeApi pokeApi;
    private MutableLiveData<List<ResultsEntity>> data = new MutableLiveData<>();

    public MainRepository() {
        app.getApps().getPokemonCOmponent().inject(this);
        this.pokeApi = retrofit.create(PokeApi.class);

    }

    public MutableLiveData<List<ResultsEntity>> getData() {
        return data;
    }

    public void callPokemons() {
        pokeApi.POKEMON_CALL().enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                data.setValue(response.body().getResults());

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}