package com.example.tomasaluch.entregable2java.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomasaluch.entregable2java.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by tomasaluch on 30/11/17.
 */

public class AdapterRecyclePaints extends RecyclerView.Adapter {
    private Context context;
    private List<Paint> listPaint;
    private Notificable notificable;

    public AdapterRecyclePaints(Context context, List<Paint> listPaint) {
        this.context = context;
        this.listPaint = listPaint;
        this.notificable = (Notificable) context;
    }

    private class Holder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView nombre;
        private TextView id;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            imageView =(ImageView) itemView.findViewById(R.id.imageViewPaint);
            nombre = (TextView)  itemView.findViewById(R.id.namePaint);
            id = (TextView)  itemView.findViewById(R.id.artistID);
        }

        public void bindProducto (Paint paint){
            nombre.setText(paint.getName());
            id.setText(paint.getArtistId());
            Picasso.with(context).load(paint.getImage().toString()).resize(50, 50)
                    .centerCrop().into(imageView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.detalle_paint_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Paint paint = listPaint.get(position);
        Holder productoHolder = (Holder) holder;
        productoHolder.bindProducto(paint);
        productoHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paint paint1 = listPaint.get(position);
                notificable.notificarClick(paint1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPaint.size();
    }

    public void cargarNuevaLista(List<Paint> paints){
        listPaint.addAll(paints);
        notifyDataSetChanged();
    }

    public interface Notificable{
        public void notificarClick(Paint paint);
    }
}
