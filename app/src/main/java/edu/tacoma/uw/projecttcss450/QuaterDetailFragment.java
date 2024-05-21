package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.projecttcss450.databinding.FragmentQuaterDetailBinding;

/**
 * create an instance of this fragment.
 */
public class QuaterDetailFragment extends Fragment {
    private FragmentQuaterDetailBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentQuaterDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get a reference to the SafeArgs object
        QuaterDetailFragmentArgs args = QuaterDetailFragmentArgs.fromBundle(getArguments());
        Quater quater = (Quater) args.getQuater();
        mBinding.yearTextView.setText(quater.getYear());
        mBinding.course1TextView.setText(quater.getCourse1());
        mBinding.course2TextView.setText(quater.getCourse2());
        mBinding.course3TextView.setText(quater.getCourse3());
    }
}