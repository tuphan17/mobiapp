package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.projecttcss450.databinding.FragmentQuaterListBinding;

/**
 * create an instance of this fragment.
 */
public class QuaterListFragment extends Fragment {
    private AuthViewModel authViewModel;
    private QuaterViewModel quaterViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        quaterViewModel = new ViewModelProvider(requireActivity()).get(QuaterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quater_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentQuaterListBinding binding = FragmentQuaterListBinding.bind(view);

        int userId = authViewModel.getUser().getUserId();

        quaterViewModel.getQuaters(userId);

        quaterViewModel.addQuaterListObserver(getViewLifecycleOwner(), quaterList -> {
            Log.d("QuaterListFragment", "Received quaterList: " + quaterList);
            if (!quaterList.isEmpty()) {
                QuaterRecyclerViewAdapter adapter = new QuaterRecyclerViewAdapter(quaterList);
                binding.recyclerView.setAdapter(adapter);
            } else {
                Log.d("QuaterListFragment", "QuaterList is empty");
            }
        });
    }
}