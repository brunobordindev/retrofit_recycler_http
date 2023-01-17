package br.com.retrofithttprecyclerview.api;

import java.util.List;

import br.com.retrofithttprecyclerview.model.Foto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();
}
