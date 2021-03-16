package com.groupeleven.studentlife.ui.usefullinks;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.groupeleven.studentlife.R;

public class UsefullinksFragment extends Fragment {
    private UsefullinksViewModel usefullinksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usefullinksViewModel =
                new ViewModelProvider(this).get(UsefullinksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_usefullinks, container, false);
        return root;
    }
}
