package com.groupeleven.studentlife.ui.usefullinks;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        TextView link;

        //make each link clickable
        link = (TextView) getView().findViewById(R.id.link1);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link2);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link3);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link4);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link5);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link6);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link7);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link8);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link9);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link10);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link11);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
