package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Usuario;
import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.Service.AllVoosService;
import com.example.hugo.trabalhoandroid.Service.LoginService;
import com.example.hugo.trabalhoandroid.View.VooAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class pesquisa_voos extends AppCompatActivity {
    EditText origem, destino, data_digitada;
    ListView lstResultado;
    Button btnPesquisar;
    String aux_usu, resp = "", aux, data;
    ArrayList<Voo> objsFiltrados = new ArrayList<Voo>();
    Usuario user = new Usuario(); //não saquei porque estava com final
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_voos);

        binding();
        preencheObjs();

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resp = new AllVoosService().execute(user.getToken()).get();

                    Voo[] objs = gson.fromJson(resp, Voo[].class);

                    if (data_digitada.getText().toString().length()!=10){
                        Toast.makeText(getApplicationContext(), "Formato da data inválido.", Toast.LENGTH_LONG).show();
                    }else {
                        objsFiltrados.clear();
                        for (int i = 0; i < objs.length; i++){
                             aux = objs[i].getDataVoo();
                             data = aux.substring(0,10);
                             if (objs[i].getDestino().getCidade().equals(destino.getText().toString()) && objs[i].getOrigem().getCidade().equals(origem.getText().toString()) && data.equals(data_digitada.getText().toString())){
                                objsFiltrados.add(objs[i]);
                             }
                        }
                    }
                    if (objsFiltrados.size()==0) {
                        lstResultado.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Nenhum voo foi encontrado com as condições determinadas.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        lstResultado.setVisibility(View.VISIBLE);
                        VooAdapter adapter = new VooAdapter(getApplicationContext(), (objsFiltrados));
                        lstResultado.setAdapter(adapter);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        lstResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplication(), poltronas.class);
                i.putExtra("code_usuario", user.getToken().toString());
                i.putExtra("modelo_aviao", objsFiltrados.get(position).getAviao().getModelo().toString());
                i.putExtra("id_aviao", objsFiltrados.get(position).getAviao().getId().toString());
                i.putExtra("prefixo_aviao", objsFiltrados.get(position).getAviao().getPrefixo().toString());
                i.putExtra("id_voo", String.valueOf(id));
                i.putExtra("aux_usu", aux_usu.toString());
                //finish();
                startActivityForResult(i, 1);
            }
        });
    }

    private void preencheObjs() {
        aux_usu = (String) getIntent().getExtras().get("obj");
        user = gson.fromJson(aux_usu, Usuario.class);
    }

    private void binding() {
        origem = findViewById(R.id.edtPesquisarOrigem);
        destino = findViewById(R.id.edtPesquisarDestino);
        data_digitada = findViewById(R.id.edtPesquisarData);
        btnPesquisar = findViewById(R.id.btnPesquisarPesquisar);
        lstResultado = findViewById(R.id.lstPesquisarResultado);
    }
}
