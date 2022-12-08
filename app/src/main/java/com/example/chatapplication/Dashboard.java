package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chatapplication.databinding.DashboardBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public final class Dashboard extends AppCompatActivity {


    private  String[] tabTitles=new String[]{"Chats","Groups"};

     DashboardBinding binding;
    TabLayout tabLayout1;
    FragmentAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        pagerAdapter=new FragmentAdapter(this);
        binding.viewpager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(binding.tablayout,binding.viewpager2,(tab, position) -> tab.setText(tabTitles[position])).attach();

    }
}
