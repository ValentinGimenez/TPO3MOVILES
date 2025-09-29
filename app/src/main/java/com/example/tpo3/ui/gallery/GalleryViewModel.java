package com.example.tpo3.ui.gallery;

import static com.example.tpo3.MainActivity.listado;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo3.model.Producto;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> ListadoMutable;

    public LiveData<ArrayList<Producto>> getListadoMutable(){
        if(ListadoMutable==null){
            ListadoMutable = new MutableLiveData<>();
        }
        return ListadoMutable;
    }

    public void cargarListado(){
        ListadoMutable.setValue(listado);
    }
}