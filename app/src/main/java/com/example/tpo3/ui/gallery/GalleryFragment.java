package com.example.tpo3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tpo3.databinding.FragmentGalleryBinding;
import com.example.tpo3.model.Producto;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel mv;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv=ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getListadoMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
        @Override
        public void onChanged(ArrayList<Producto> productos) {
            ProductoAdapter la = new ProductoAdapter((ArrayList<Producto>) productos,getLayoutInflater(),getContext());
            GridLayoutManager glm = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
            binding.rvListar.setLayoutManager(glm);
            binding.rvListar.setAdapter(la);
        }
    });
        mv.cargarListado();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}