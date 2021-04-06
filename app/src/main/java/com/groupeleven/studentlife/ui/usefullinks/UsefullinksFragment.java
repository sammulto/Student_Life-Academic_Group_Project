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
        link = (TextView) getView().findViewById(R.id.link_khan_academy);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_wiki);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_quizlet);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_crowdmark);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_youtube);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_ocw);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_gather);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_umlearn);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_desmos);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_iclicker);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link = (TextView) getView().findViewById(R.id.link_google_doc);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
