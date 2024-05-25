/*
This class is an fragment class
Shows Login, password fields, and two buttons for login and register.
 */
package edu.tacoma.uw.projecttcss450;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;

import org.json.JSONArray;

import edu.tacoma.uw.projecttcss450.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private AuthViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            viewModel.loginUser(email, password);
        });

        binding.registerButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        });

        viewModel.getAuthResult().observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                storeUserData(result.getUserId(), result.getCourses());
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_courseListFragment);
                Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Login failed: " + result.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void storeUserData(int userId, JSONArray courses) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", true);

        // Store the user's ID
        editor.putInt("user_id", userId);

        // Store the user's courses
        Gson gson = new Gson();
        String coursesJson = gson.toJson(courses);
        editor.putString("user_courses", coursesJson);

        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}