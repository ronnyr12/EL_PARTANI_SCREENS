package com.example.el_partani_screens;

import com.example.el_partani_screens.databinding.ActivityAdminTestingScreenBinding;
import com.google.android.gms.common.server.response.FastParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AdminTesting_Screen extends AppCompatActivity implements View.OnClickListener {
    ActivityAdminTestingScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminTestingScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnEmailAdmin.setOnClickListener(this);
        binding.btnPassAdmin.setOnClickListener(this);

        //adds titles of tab_layout
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("מקצועות");
        titleList.add("שעות");

        //setup tab_layout
        binding.tablayoutAdmin.setupWithViewPager(binding.viewPagerAdmin);
        
        //prepare view pager
        prepareViewPager(binding.viewPagerAdmin, titleList);
    }

    private void prepareViewPager(ViewPager viewPagerAdmin,
                                  ArrayList<String> titleList) {
        //initialize main-adapter
        AdminTesting_Adapter adapter =
                new AdminTesting_Adapter(getSupportFragmentManager());
        Subjects_Edit_Fragment subjects_edit_fragment = new Subjects_Edit_Fragment();
        for (int i = 0; i < titleList.size(); i++) {
            Bundle bundle = new Bundle();
            //put title
            bundle.putString("title", titleList.get(i));
            //set arguments
            subjects_edit_fragment.setArguments(bundle);
            adapter.addFragment(subjects_edit_fragment, titleList.get(i));
            subjects_edit_fragment = new Subjects_Edit_Fragment();
        }
        //set adapter
        viewPagerAdmin.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_email_admin:
                //
                binding.btnEmailAdmin.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                break;
            case R.id.btn_pass_admin:

                break;
        }
    }

    private class AdminTesting_Adapter extends FragmentPagerAdapter {
        //initialize array list
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> titleArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.ic_baseline_calendar_month_24,
                R.drawable.ic_baseline_school_24};
        //create constructor
        public void addFragment(Fragment fragment, String s) {
            //add fragment
            fragmentArrayList.add(fragment);
            //add title
            titleArrayList.add(s);
        }

        public AdminTesting_Adapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //initialize drawable
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                    imageList[position]);
            //set bound
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            //initialize spannable string
            SpannableString spannableString = new SpannableString("  "+
                    titleArrayList.get(position));
            //initialize image span
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            //set span
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;

        }
    }
}