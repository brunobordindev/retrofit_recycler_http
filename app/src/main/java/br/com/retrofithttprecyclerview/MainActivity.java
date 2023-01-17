package br.com.retrofithttprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.retrofithttprecyclerview.adapter.AdapterFoto;
import br.com.retrofithttprecyclerview.api.DataService;
import br.com.retrofithttprecyclerview.databinding.ActivityMainBinding;
import br.com.retrofithttprecyclerview.model.Foto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Retrofit retrofitFotos;
    private List<Foto> listasFotos = new ArrayList<>();
    private AdapterFoto adapterFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //mexer no manisfet permissao
        retrofitFotos = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        binding.btn.setOnClickListener(view -> {
            recuperarListaFotosRetrofit();
        });



    }

    private void recuperarListaFotosRetrofit(){

        DataService service = retrofitFotos.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {

                if (response.isSuccessful()){
                    listasFotos = response.body();
                    adapterFoto= new AdapterFoto(listasFotos, getApplicationContext());

                    binding.recyclerFotos.setHasFixedSize(true);
                    binding.recyclerFotos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.recyclerFotos.setAdapter(adapterFoto);

                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }
}