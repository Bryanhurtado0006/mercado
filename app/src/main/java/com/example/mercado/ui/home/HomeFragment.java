package com.example.mercado.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mercado.databinding.FragmentHomeBinding;
import com.example.mercado.models.User;
import com.example.mercado.network.user.ApiClient;
import com.example.mercado.network.user.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnRegistrar.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            User user = new User(nombre, email, password);

            ApiService service = ApiClient.getClient().create(ApiService.class);
            service.createUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                        binding.etNombre.setText("");
                        binding.etEmail.setText("");
                        binding.etPassword.setText("");
                    } else {
                        Toast.makeText(getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getContext(), "Fallo conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


