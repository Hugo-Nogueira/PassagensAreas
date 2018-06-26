package com.example.hugo.trabalhoandroid.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hugo.trabalhoandroid.Model.Voo;
import com.example.hugo.trabalhoandroid.R;

import java.util.List;

public class VooAdapter extends BaseAdapter{

    List<Voo> voo;
    LayoutInflater layout;

    public VooAdapter(Context context, List<Voo> voo){
        this.voo = voo;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return voo.size();
    }

    @Override
    public Object getItem(int position) {
        return voo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return voo.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView cidadeOrigem, cidadeDestino, aeroportoOrigem, aeroportoDestino, dataHora, preco;

        View view = layout.inflate(R.layout.voostemplate, parent,false);

        aeroportoOrigem = view.findViewById(R.id.voosOrigemAeroporto);
        aeroportoDestino = view.findViewById(R.id.voosDestinoAeroporto);
        preco = view.findViewById(R.id.voosValor);

        Voo obj = (Voo) getItem(position);

        aeroportoOrigem.setText(String.valueOf(obj.getOrigem().getAeroporto()));
        aeroportoDestino.setText(String.valueOf(obj.getDestino().getAeroporto()));
        preco.setText(String.valueOf(obj.getValorPassagem()));

        return view;
    }
}
