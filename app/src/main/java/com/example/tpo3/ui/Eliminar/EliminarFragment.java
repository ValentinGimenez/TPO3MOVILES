package com.example.tpo3.ui.Eliminar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpo3.R;
import com.example.tpo3.databinding.FragmentEliminarBinding;
import com.example.tpo3.model.Producto;

public class EliminarFragment extends Fragment {

    private FragmentEliminarBinding binding;
    private EliminarViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(EliminarViewModel.class);

        binding = FragmentEliminarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.buscarProducto(binding.etCodigo.getText().toString());
            }
        });
        mv.getProductoEncontrado().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                if (producto != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("codigo", producto.getCodigo());
                    bundle.putString("descripcion", producto.getDescripcion());
                    bundle.putDouble("precio", producto.getPrecio());
                    Navigation.findNavController(requireView()).navigate(R.id.detalleDelProductoFragment, bundle);
                } else {
                    binding.tvError.setText("Producto no encontrado.");
                }
            }
        });
        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        binding.etCodigo.setText("");
        binding.tvError.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}