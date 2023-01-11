package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {
    protected ArrayList<Fragment> fragments;



    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        fragments =new ArrayList<>(2);
        fragments.add(new ChatFragment());
        fragments.add(new GroupFragment());
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getItemCount()
    {
        return fragments.size();
    }

}
