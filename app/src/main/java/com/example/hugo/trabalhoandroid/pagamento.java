package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Cartao;
import com.example.hugo.trabalhoandroid.Model.Poltrona;
import com.example.hugo.trabalhoandroid.Service.CartaoService;
import com.example.hugo.trabalhoandroid.Service.FinalizarCompraService;
import com.example.hugo.trabalhoandroid.Service.PoltronasVooService;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class pagamento extends AppCompatActivity {
    TextView mes, ano, tarja, cartao;
    String valor, resp = "", code, id, poltrona, aux_usu;
    Button pagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        binding();

        valor = getIntent().getExtras().get("valor").toString();
        code = getIntent().getExtras().get("code").toString();
        id = getIntent().getExtras().get("id").toString();
        poltrona = getIntent().getExtras().get("poltrona").toString();
        aux_usu = getIntent().getExtras().get("aux_usu").toString();

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if( mes.getText().length() < 2   || ano.getText().length() < 4 ||
                        tarja.getText().length() < 3 || cartao.getText().length() < 16 ){
                        Toast.makeText(getApplicationContext(), "ATENÇÃO: Dados inválidos!\nVerifique os dados e tente novamente.", Toast.LENGTH_LONG).show();
                    }else {
                        resp = new CartaoService().execute(cartao.getText().toString(), mes.getText().toString(), ano.getText().toString(), tarja.getText().toString(), valor).get();

                        Gson gson = new Gson();
                        Cartao obj = gson.fromJson(resp, Cartao.class);

                        if (obj.getStatus().equals("APROVADO")) {
                            darBaixaNoSistema(code, id, poltrona);
                        }
                        if(obj.getStatus().equals("REPROVADO")){
                            Toast.makeText(getApplicationContext(), "Cartão reprovado", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }


        });

    }

    private void darBaixaNoSistema(String code, String id, String poltrona) {
        try {
            resp = new FinalizarCompraService().execute(code,id,poltrona).get();

            if (resp.equals("200")){
                Toast.makeText(getApplicationContext(), "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplication(), pesquisa_voos.class);
                i.putExtra("aux_usu", aux_usu);
                i.putExtra("obj ", aux_usu);
                finish();
                startActivityForResult(i, 1);
            }else{
                Toast.makeText(getApplicationContext(),"Erro no site da operadora. Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void binding() {
        mes = findViewById(R.id.edtPagamentoMes);
        ano = findViewById(R.id.edtPagamentoAno);
        tarja = findViewById(R.id.edtPagamentoTarja);
        cartao = findViewById(R.id.edtPagamentoNumeroCartao);
        pagar = findViewById(R.id.btnPagamentoConfirmarPagamento);
    }
}
