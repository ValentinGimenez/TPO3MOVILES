package com.example.tpo3.ui.DetalleDelProducto;

import static com.example.tpo3.MainActivity.listado;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo3.model.Producto;

public class DetalleDelProductoViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje;

    public DetalleDelProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void eliminarProducto(Producto producto) {
        if (producto != null) {
            boolean eliminado = listado.removeIf(p -> p.getCodigo().equalsIgnoreCase(producto.getCodigo()));
            if (eliminado) {
                mMensaje.setValue("Producto eliminado correctamente");
            } else {
                mMensaje.setValue("No se pudo eliminar el producto");
            }
        } else {
            mMensaje.setValue("Producto inválido");
        }
    }
}