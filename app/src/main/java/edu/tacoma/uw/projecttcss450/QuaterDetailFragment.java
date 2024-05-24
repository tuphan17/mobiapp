package edu.tacoma.uw.projecttcss450;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        mBinding.buttonSendEmail.setOnClickListener(button -> sendEmail());


    }

    //Method for opening email app with quarter detail.
    private void sendEmail() {
        String year = mBinding.yearTextView.getText().toString();
        String course1 = mBinding.course1TextView.getText().toString();
        String course2 = mBinding.course2TextView.getText().toString();
        String course3 = mBinding.course3TextView.getText().toString();

        String emailBody = "Year: " + year + "\n" +
                "Course 1: " + course1 + "\n" +
                "Course 2: " + course2 + "\n" +
                "Course 3: " + course3;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Quarter/Classes");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(Intent.createChooser(emailIntent, ""));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }


    }
}