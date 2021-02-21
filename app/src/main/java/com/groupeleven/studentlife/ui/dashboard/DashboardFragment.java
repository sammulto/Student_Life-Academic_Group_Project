package com.groupeleven.studentlife.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupeleven.studentlife.R;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private ArrayList<DashboardTempData> sampleData;

    private DashboardViewModel dashboardViewModel;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //find the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.dashboard_recyclerView);
        //init the event list
        sampleData = DashboardTempData.createTempData(15);
        //pass list to adapter
        DashboardEventListAdapter adapter = new DashboardEventListAdapter(sampleData);
        //populate the RecyclerView
        recyclerView.setAdapter(adapter);
        //position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



}