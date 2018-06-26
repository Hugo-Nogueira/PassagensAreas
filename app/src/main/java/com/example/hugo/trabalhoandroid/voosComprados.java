package com.example.hugo.trabalhoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.Service.AddService;
import com.example.hugo.trabalhoandroid.Service.AllVoosService;
import com.example.hugo.trabalhoandroid.View.VooAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class voosComprados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voos_comprados);
        String token, resp = "";
        TextView teste = findViewById(R.id.tvVoosCompradosTeste);
        ListView lstVoos = findViewById(R.id.lstVisualizarVoosComprados);

        ArrayList<Voo> objs = null;

        try {
            resp = new AllVoosService().execute().get();
            Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//       objs = (ArrayList<Voo>) new TimeDAO(getApplicationContext());

        if (objs.size()==0){
                Toast.makeText(getApplicationContext(),"Para visualizar a lista de classificação completa, favor informar os resultados no menu 'Gerenciar Rodada Atual'." , Toast.LENGTH_LONG).show();
            }
        else if (objs != null) {
            VooAdapter adapter = new VooAdapter(getApplicationContext(), objs);
            lstVoos.setAdapter(adapter);
        }
    }
}
