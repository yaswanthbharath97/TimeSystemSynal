package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    private final String[] tabTitles=new String[]{"Chats","Groups"};

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public FragmentAdapter(Dashboard dashboard) {
        super(dashboard);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {

            case 0:
                return new ChatFragment();

            case 1:
                return new GroupFragment();

        }
        return new ChatFragment();
    }

    @Override
    public int getItemCount()
    {

        return tabTitles.length;
    }
}
