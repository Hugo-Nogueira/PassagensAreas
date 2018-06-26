package com.example.hugo.trabalhoandroid.View;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugo.trabalhoandroid.Model.Poltrona;
import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.R;

import java.util.List;

public class PoltronaAdapter extends BaseAdapter {

    List<Poltrona> poltronas;
    LayoutInflater layout;

    public PoltronaAdapter(Context context, List<Poltrona> poltronas){
        this.poltronas = poltronas;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return poltronas.size();
    }

    @Override
    public Object getItem(int position) {
        return poltronas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return poltronas.get(position).getAssento();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView numero, preco, status;

        View view = layout.inflate(R.layout.cadeirastemplate, parent,false);

        numero = view.findViewById(R.id.tvListPoltronaAssento);
        preco = view.findViewById(R.id.tvListPoltronaPreco);
        status = view.findViewById(R.id.tvListPoltronaStatus);

        Poltrona obj = (Poltrona) getItem(position);

        numero.setText(obj.getAssento().toString());

        if (obj.getOcupado()){
            status.setText("Indisponível");
        }else {
            status.setText("Disponível");
        }

        preco.setText(obj.getValorPassagem().toString());

        return view;
    }
}
