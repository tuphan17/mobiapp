package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.projecttcss450.databinding.FragmentAddCourseBinding;

/**
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {

    private FragmentAddCourseBinding mBinding;

    private QuaterViewModel quaterViewModel;

    private static final String TAG = "AddAnimalFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quaterViewModel = new ViewModelProvider(getActivity()).get(QuaterViewModel.class);

        mBinding = FragmentAddCourseBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        quaterViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {

            observeResponse(response);

        });

        mBinding.buttonAddQuater.setOnClickListener(button -> processAddAnimal());

        mBinding.ViewQuaterButton.setOnClickListener(button -> Navigation.findNavController(getView()).navigate(R.id.action_addCourse_to_quaterListFragment));

    }



    private void processAddAnimal() {

        final String year = mBinding.editYear.getText().toString();

        final String course1 = mBinding.editCourse1.getText().toString();

        final String course2 = mBinding.editCourse2.getText().toString();

        final String course3 = mBinding.editCourse3.getText().toString();



        Log.i(TAG, "Data is " + year + ", " + course1 + ", " + course2+ ", " + course3);
        AuthViewModel authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        int userId = authViewModel.getUser().getUserId();
        quaterViewModel.addQuater(year, course1, course2, course3,userId);



    }



    private void observeResponse(final JSONObject response) {

        if (response.length() > 0) {

            if (response.has("error")) {

                try {

                    Toast.makeText(this.getContext(),

                            "Error Adding Quater: " +

                                    response.get("error"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {

                    Log.e("JSON Parse Error", e.getMessage());

                }

            } else {

                Toast.makeText(this.getContext(),"Quater added", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getView()).navigate(R.id.action_addCourse_to_quaterListFragment);
            }

        } else {

            Log.d("JSON Response", "No Response");

        }

    }
}