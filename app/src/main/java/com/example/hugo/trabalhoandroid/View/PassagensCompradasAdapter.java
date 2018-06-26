package com.example.hugo.trabalhoandroid.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hugo.trabalhoandroid.Model.Poltrona;
import com.example.hugo.trabalhoandroid.Model.UsuarioPoltrona;
import com.example.hugo.trabalhoandroid.R;

import java.util.List;

public class PassagensCompradasAdapter extends BaseAdapter{

    List<Poltrona> objs;
    LayoutInflater layout;

    public PassagensCompradasAdapter(Context context, List<Poltrona> objs){
        this.objs = objs;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public Object getItem(int position) {
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objs.get(position).getAssento();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView assento, origem, destino, data, valor, aviao;

        View view = layout.inflate(R.layout.cadeirastemplate, parent,false);

        assento = view.findViewById(R.id.txtPgPassAssento);
        origem = view.findViewById(R.id.txtPgPassOrigem);
        destino = view.findViewById(R.id.txtPgPassDestno);
        data = view.findViewById(R.id.txtPgPassDataVoo);
        valor = view.findViewById(R.id.txtPgPassValor);
        aviao = view.findViewById(R.id.txtPgPassAviao);

        Poltrona obj = (Poltrona) getItem(position);

        assento.setText("Assento: "+obj.getAssento().toString());
        origem.setText("Origem: "+obj.getOrigem().toString());
        destino.setText("Destino: "+obj.getDestino().toString());
        data.setText("Data: "+obj.getDataVoo().toString());
        valor.setText("Valor: "+obj.getValorPassagem().toString());
        aviao.setText("Avião: "+obj.getAviao().toString());

        return view;
    }
}
