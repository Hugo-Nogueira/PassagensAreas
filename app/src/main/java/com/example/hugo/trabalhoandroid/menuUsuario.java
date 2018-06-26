package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Poltrona;
import com.example.hugo.trabalhoandroid.Model.Usuario;
import com.example.hugo.trabalhoandroid.Model.UsuarioPoltrona;
import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.Service.AllVoosService;
import com.example.hugo.trabalhoandroid.Service.LoginService;
import com.example.hugo.trabalhoandroid.Service.PoltronasVooService;
import com.example.hugo.trabalhoandroid.Service.UsuarioService;
import com.example.hugo.trabalhoandroid.View.PassagensCompradasAdapter;
import com.example.hugo.trabalhoandroid.View.VooAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class menuUsuario extends AppCompatActivity {

    TextView teste;
    String aux, token, id, resp ="";
    Button vComprados, vPesquisa;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        binding();
        preencheObjs();

        //botão pesquisa
        vPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), pesquisa_voos.class);
                i.putExtra("obj", aux);
                startActivityForResult(i, 1);
            }
        });
        //botão visualizar passagens compradas
        vComprados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resp = new UsuarioService().execute(id, token).get();
                    //cria o UsuarioPoltrona pelo Gson: Este obj contem uma lista de pltronas
                    Gson gson = new Gson();
                    UsuarioPoltrona objs = gson.fromJson(resp, UsuarioPoltrona.class);
                    //cria o adapter, passa as poltronas para a lista, seta a visibilidade e o adapter
                    PassagensCompradasAdapter adapter = new PassagensCompradasAdapter(getApplicationContext(), objs.getPoltronas());
                    lst.setVisibility(View.VISIBLE);
                    lst.setAdapter(adapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void preencheObjs() {
        aux = (String) getIntent().getExtras().get("obj");
        token = aux.substring(aux.indexOf("token")+8,aux.indexOf("}")-1);
        id = aux.substring(aux.indexOf("id")+4,aux.indexOf("nome")-2);
    }

    private void binding() {
        teste = findViewById(R.id.tvMenuUsuario);
        vComprados = findViewById(R.id.btnMenuUsuarioPassagensCompradas);
        vPesquisa = findViewById(R.id.btnMenuUsuarioBuscarVoos);
        lst = findViewById(R.id.lstMenuUsuario);
    }
}
