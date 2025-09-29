package com.example.tpo3.ui.home;

import static com.example.tpo3.MainActivity.listado;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo3.model.Producto;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mError, mCorrecto;
    private MutableLiveData<Boolean> mLimpiarCampos;

    public LiveData<String> getMError() {
        if(mError==null){
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getMCorrecto() {
        if(mCorrecto==null){
            mCorrecto = new MutableLiveData<>();
        }
        return mCorrecto;
    }

    public LiveData<Boolean> getMLimpiarCampos() {
        if (mLimpiarCampos == null) {
            mLimpiarCampos = new MutableLiveData<>();
        }
        return mLimpiarCampos;
    }

    public void validacion(String codigo, String descripcion, String precio) {
        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            mError.setValue("Debe completar todos los campos.");
            return;
        }
        for (Producto p : listado) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                mError.setValue("El código ya está registrado.");
                return;
            }
        }
        for (Producto p : listado) {
            if (p.getDescripcion().equalsIgnoreCase(descripcion)) {
                mError.setValue("Ya existe un producto con esa descripción.");
                return;
            }
        }
        if (precio.equals("0")) {
            mError.setValue("El precio debe ser mayor que cero.");
            return;
        }
        if (precio.startsWith(".")) {
            mError.setValue("El precio no puede comenzar con un punto.");
            return;
        }
        if (!precio.matches("^(0|[1-9][0-9]*)(\\.[0-9]{1,2})?$")) {
            mError.setValue("Ingrese un precio válido (números enteros o con hasta dos decimales).");
            return;
        }
        Producto producto = new Producto(codigo,descripcion,Double.parseDouble(precio));
        listado.add(producto);
        mCorrecto.setValue("¡Producto agregado correctamente!");
        mLimpiarCampos.setValue(true);
    }

}