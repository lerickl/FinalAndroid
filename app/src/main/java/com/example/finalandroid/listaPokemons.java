package com.example.finalandroid;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalandroid.adaptadores.AdapterPokemon;
import com.example.finalandroid.clases.pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class listaPokemons extends AppCompatActivity {
    private FloatingActionButton Agregar;
    public static RecyclerView listaPokemonVista;
    public AdapterPokemon adapterL;
    private static String url ="https://upn.lumenes.tk/pokemons/{n00033222}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerID();
        // ClicAgregar();
        PeticonGet();
    }
    public void PeticonGet(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.e("Lista",response);
                List<pokemon> pokemons = Arrays.asList(gson.fromJson(response,pokemon[].class));

                AdapterSet(pokemons);
                Log.e("DATA","OK");
                Log.e("pokemons",pokemons.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR",error.toString());
            }
        });
        queue.add(request);
    }

    public void AdapterSet(List<pokemon> pokemons){
          listaPokemonVista =findViewById(R.id.recyclerPokemon);

        listaPokemonVista.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listaPokemonVista.setLayoutManager(layoutManager);

        listaPokemonVista.setAdapter(adapterL);

        Log.e("DATA","OK");
    }
    public void ObtenerID(){
        Agregar = findViewById(R.id.fab);
    }
}
