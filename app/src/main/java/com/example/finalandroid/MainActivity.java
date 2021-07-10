package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalandroid.clases.entrenador;
import com.example.finalandroid.clases.respon;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static String url="https://upn.lumenes.tk/entrenador/{n00033222}";
    static String urls="https://upn.lumenes.tk/entrenador/{n00033222}/pokemones";
    public TextView Nombre;
    public TextView pueblo;
    public ImageView imas;
    public entrenador entrenadors;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Intent intent = new Intent(getApplicationContext(),EntrenadorPokemonActivity.class);
        startActivity(intent);*/

        Button fab = findViewById(R.id.AgregarMaestroBtn);
        PetitionGet();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                intent = new Intent(getApplicationContext(), EntrenadorPokemonActivity.class);

                intent.putExtra("nombres",entrenadors.nombres);
                intent.putExtra("pueblo",entrenadors.pueblo);
                startActivity(intent);


            }
        });
    }
    List<entrenador> items;
    public void PetitionGet(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.e("Lista",response);
                  entrenadors =  gson.fromJson(response, entrenador.class) ;

                //AdapterSet(entrenador);
                Log.e("DATA","OK");
                Log.e("Entrenadores",entrenadors.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR",error.toString());
            }
        });
        queue.add(request);
    }
   public void AdapterSet(entrenador entrenadors){
         AppCompatActivity EntrenadorPokemonActiv = new AppCompatActivity();

         Intent intent = new Intent(this, EntrenadorPokemonActivity.class);
          startActivity(intent);
    }
}