package com.example.tpo3.ui.DetalleDelProducto;

import static com.example.tpo3.MainActivity.listado;

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
import android.widget.Toast;

import com.example.tpo3.R;
import com.example.tpo3.databinding.FragmentDetalleDelProductoBinding;
import com.example.tpo3.model.Producto;

public class DetalleDelProductoFragment extends Fragment {

    private FragmentDetalleDelProductoBinding binding;
    private DetalleDelProductoViewModel mv;
    private Producto producto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleDelProductoViewModel.class);

        binding = FragmentDetalleDelProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            String codigo = getArguments().getString("codigo");
            String descripcion = getArguments().getString("descripcion");
            double precio = getArguments().getDouble("precio");
            producto = new Producto(codigo, descripcion, precio);

            binding.tvCodigo.setText("Código: " + codigo);
            binding.tvDescripcion.setText("Descripción: " + descripcion);
            binding.tvPrecio.setText("Precio: $" + precio);
        }
        binding.btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.eliminarProducto(producto);
                Navigation.findNavController(v).popBackStack(R.id.eliminarFragment, false);
            }
        });

        mv.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                if (mensaje != null) {
                    Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}