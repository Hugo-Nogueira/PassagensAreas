package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Poltrona;
import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.Service.AllVoosService;
import com.example.hugo.trabalhoandroid.Service.CartaoService;
import com.example.hugo.trabalhoandroid.Service.PoltronasVooService;
import com.example.hugo.trabalhoandroid.View.PoltronaAdapter;
import com.example.hugo.trabalhoandroid.View.VooAdapter;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class poltronas extends AppCompatActivity {
    TextView modelo, prefixo;
    String resp, idVoo, codigo, aux_usu;
    ListView lstPoltronasParaComprar;
    Poltrona[] objs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poltronas);

        binding();
        preencheDados();

        try {
            resp = new PoltronasVooService().execute(idVoo,codigo).get();

            Gson gson = new Gson();
            objs = gson.fromJson(resp, Poltrona[].class);

            PoltronaAdapter adapter = new PoltronaAdapter(getApplicationContext(), Arrays.asList(objs));
            lstPoltronasParaComprar.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        lstPoltronasParaComprar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (objs[position].getOcupado()){
                    Toast.makeText(getApplicationContext(), "Esta poltrona já está vendida", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplication(), pagamento.class);
                    i.putExtra("code",codigo);
                    i.putExtra("id",idVoo);
                    i.putExtra("poltrona",objs[position].getAssento());
                    i.putExtra("valor",objs[position].getValorPassagem());
                    i.putExtra("aux_usu",aux_usu.toString());
                    Integer aux = 10;
                    //finish();
                    startActivityForResult(i, 1);
                }
            }
        });

    }

    private void preencheDados() {
        modelo.setText(getIntent().getExtras().get("modelo_aviao").toString());
        prefixo.setText(getIntent().getExtras().get("prefixo_aviao").toString());
        idVoo = getIntent().getExtras().get("id_voo").toString();
        codigo  = getIntent().getExtras().get("code_usuario").toString();
        aux_usu  = getIntent().getExtras().get("aux_usu").toString();
    }

    private void binding() {
        modelo = findViewById(R.id.txtModeloAviao);
        prefixo = findViewById(R.id.txtPrefixoAviao);
        lstPoltronasParaComprar = findViewById(R.id.lstPoltronasParaComprar);
    }
}
