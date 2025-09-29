package com.example.tpo3.ui.Eliminar;

import static com.example.tpo3.MainActivity.listado;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo3.model.Producto;

public class EliminarViewModel extends ViewModel {

    private MutableLiveData<Producto> productoEncontrado;
    public LiveData<Producto> getProductoEncontrado() {
        if (productoEncontrado == null) {
            productoEncontrado = new MutableLiveData<>();
        }
        return productoEncontrado;
    }
    public void buscarProducto(String codigo) {
        if (productoEncontrado == null) {
            productoEncontrado = new MutableLiveData<>();
        }
        Producto encontrado = null;
        for (Producto p : listado) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = p;
                break;
            }
        }
        productoEncontrado.setValue(encontrado);
    }
}