package com.example.tpo3.ui.slideshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tpo3.R;
import com.example.tpo3.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        new AlertDialog.Builder(requireContext())
                .setTitle("Cerrar aplicación")
                .setMessage("¿Querés cerrar la aplicación ahora?")
                .setPositiveButton("Sí, salir", (dialog, which) ->
                        requireActivity().finishAffinity())
                .setNegativeButton("No, volver", (dialog, which) ->
                        Navigation.findNavController(requireActivity(),
                                R.id.nav_host_fragment_content_main).popBackStack())
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
