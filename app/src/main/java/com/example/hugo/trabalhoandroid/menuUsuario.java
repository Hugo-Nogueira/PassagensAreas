package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Usuario;
import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.Service.AllVoosService;
import com.example.hugo.trabalhoandroid.Service.LoginService;
import com.example.hugo.trabalhoandroid.Service.PoltronasVooService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class menuUsuario extends AppCompatActivity {

    TextView teste;
    String aux, token, id, resp ="";
    Button vComprados, vPesquisa;
    Integer maiorAviao = 0;
    Voo[] vetVoo;
    String [] vetResp = new String[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        binding();

        aux = (String) getIntent().getExtras().get("obj");
        token = aux.substring(aux.indexOf("token")+8,aux.indexOf("}")-1);
        id = aux.substring(aux.indexOf("id")+4,aux.indexOf("nome")-2);

        vPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), pesquisa_voos.class);
                Usuario u = new Usuario();

                i.putExtra("obj", aux);
                startActivityForResult(i, 1);
            }
        });

        vComprados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncontrarQtdDosVoosEMaiorAviao();
                EncontrarIdDasPoltronas();
            }
        });

    }

    private Integer[][] EncontrarIdDasPoltronas() {
        Integer idDasPoltronas[][] = new Integer[maiorAviao][vetVoo.length];

        try {

            for (Integer i = 0; i < vetVoo.length-1 ;i++ ) {
                resp = new PoltronasVooService().execute(token, vetVoo[i].getId().toString()).get();
                vetResp[i] = resp;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return idDasPoltronas;
    }

    private void EncontrarQtdDosVoosEMaiorAviao() {
        try {
            resp = new AllVoosService().execute(token).get();

            Gson gson = new Gson();
            vetVoo = gson.fromJson(resp, Voo[].class);


            for (int i = 0; i < vetVoo.length; i++){
                if (vetVoo[i].getAviao().getCapacidade()>maiorAviao){
                    maiorAviao=vetVoo[i].getAviao().getCapacidade();
                }
            }

            Toast.makeText(getApplicationContext(), maiorAviao+"", Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void binding() {
        teste = findViewById(R.id.tvMenuUsuario);
        vComprados = findViewById(R.id.btnMenuUsuarioPassagensCompradas);
        vPesquisa = findViewById(R.id.btnMenuUsuarioBuscarVoos);
    }
}
