package com.groupeleven.studentlife.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groupeleven.studentlife.MainActivity;
import com.groupeleven.studentlife.R;
import com.groupeleven.studentlife.domainSpecificObjects.Task;
import com.groupeleven.studentlife.logic.DashboardLogic;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private Task[] listData; //events to show in the Dashboard
    private DashboardLogic logicUnit;
    private DashboardEventListAdapter adapter;
    private RecyclerView recyclerView;
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

        //init the event list
        logicUnit = new DashboardLogic();
        listData = logicUnit.getData();

        //populate the RecyclerView fire up the adapter
        recyclerView = view.findViewById(R.id.dashboard_recyclerView);
        adapter = new DashboardEventListAdapter(listData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(listData.length == 0) {
            //display empty list message
            TextView textView = requireView().findViewById(R.id.text_dashboard);
            textView.setText(R.string.noEvent);
        }

    }

    //on resume update the list data
    @Override
    public void onResume() {
        super.onResume();
        adapter.refreshView();
    }



}