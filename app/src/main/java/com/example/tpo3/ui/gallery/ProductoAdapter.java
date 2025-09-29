package com.example.tpo3.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo3.R;
import com.example.tpo3.model.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderListar> {

    private ArrayList<Producto> listado;
    private Context context;
    private LayoutInflater li;

    public ProductoAdapter(ArrayList<Producto> listado, LayoutInflater li, Context context) {
        listado.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
            }
        });
        this.context = context;
        this.listado = listado;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderListar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.item,parent,false);
        return new ViewHolderListar(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListar holder, int position) {
        if (listado.isEmpty()) {
            holder.codigo.setText("");
            holder.descripcion.setText("");
            holder.precio.setText("");
        } else {
            Producto prodActual = listado.get(position);
            holder.codigo.setText(prodActual.getCodigo());
            holder.descripcion.setText(prodActual.getDescripcion());
            holder.precio.setText("$ " + new DecimalFormat("#,##0.00").format(prodActual.getPrecio()));
        }
    }

    @Override
    public int getItemCount() {
        return listado.isEmpty() ? 1 : listado.size();
    }

    public class ViewHolderListar extends RecyclerView.ViewHolder{
    TextView codigo,descripcion,precio;
        public ViewHolderListar(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.tvCodigo);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            precio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
