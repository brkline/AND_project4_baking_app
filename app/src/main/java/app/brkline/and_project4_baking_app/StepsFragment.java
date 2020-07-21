package app.brkline.and_project4_baking_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.brkline.and_project4_baking_app.databinding.FragmentStepsBinding;

public class StepsFragment extends Fragment {

    FragmentStepsBinding stepsBinding;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        stepsBinding = FragmentStepsBinding.inflate(inflater, container, false);
        return stepsBinding.getRoot();
    }
}