package com.example.el_partani_screens;

import com.example.el_partani_screens.databinding.FragmentSubjectsEditBinding;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Subjects_Edit_Fragment extends Fragment {

    FragmentSubjectsEditBinding binding;

    //todo - porpoise of this fragment?
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubjectsEditBinding.inflate(getLayoutInflater(), container,
                false);
        View view = binding.getRoot();

        String title = getArguments().getString("title");
        binding.tvSubjectsFragment.setText(title);
        return view;
    }
}