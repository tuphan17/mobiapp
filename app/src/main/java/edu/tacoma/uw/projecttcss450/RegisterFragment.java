/*
This class is a fragment class
Holds Email, Password fields, and a register button.
 */
package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.tacoma.uw.projecttcss450.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private AuthViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            viewModel.registerUser(email, password);
        });

        viewModel.getAuthResult().observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                storeUserId(result.getUserId());
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                Toast.makeText(getContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Registration failed: " + result.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void storeUserId(int userId) {
        // Store the user's ID in a shared preference or a Room database
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}