package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.projecttcss450.databinding.FragmentQuaterBinding;
import edu.tacoma.uw.projecttcss450.databinding.FragmentQuaterListBinding;

/**
 * create an instance of this fragment.
 */
public class QuaterListFragment extends Fragment {

    private QuaterViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(QuaterViewModel.class);
        mModel.getQuaters();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quater_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        @NonNull FragmentQuaterListBinding binding = FragmentQuaterListBinding.bind(getView());

        mModel.addQuaterListObserver(getViewLifecycleOwner(), quaterList -> {
            if (!quaterList.isEmpty()) {
                binding.layoutRoot.setAdapter(
                        new QuaterRecyclerViewAdapter(quaterList)
                );
            }
        });
    }
}