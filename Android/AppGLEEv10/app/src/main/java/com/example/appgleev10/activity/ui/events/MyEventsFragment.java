package com.example.appgleev10.activity.ui.events;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgleev10.R;
import com.example.appgleev10.adapter.MyEventsAdapter;
import com.example.appgleev10.comunication.CancelRequestCommunication;
import com.example.appgleev10.comunication.Communication;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.example.appgleev10.viewmodel.RequestViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MyEventsFragment extends Fragment implements Communication, CancelRequestCommunication {

    private RequestViewModel requestViewModel;
    private RecyclerView rcvRequests;
    private MyEventsAdapter myEventsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_events, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initViewModel();
        initAdapter();
        loadData();

    }

    private void init(View v) {
        rcvRequests = v.findViewById(R.id.rcvMyEventsFG);
    }

    private void initViewModel() {
        requestViewModel = new ViewModelProvider(this).get(RequestViewModel.class);
    }

    private void initAdapter() {
        myEventsAdapter = new MyEventsAdapter(new ArrayList<>(), this, this);
        rcvRequests.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rcvRequests.setAdapter(myEventsAdapter);
    }

    private void loadData() {
       SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final Gson g = new GsonBuilder().
                registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        String adminJson = sp.getString("AdminJson", null);
        if (adminJson != null) {
            final Admin admin = g.fromJson(adminJson, Admin.class);
            this.requestViewModel.listRequestsByVisitant(admin.getVisitant().getId()).observe(getViewLifecycleOwner(), response -> {
              myEventsAdapter.updateItems(response.getBody());

            });
        }

    }

    @Override
    public void showDetails(Intent i) {
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.above_in, R.anim.above_out);
    }

    @Override
    public String cancelRequest(int id) {
        this.requestViewModel.cancelRequest(id).observe(getViewLifecycleOwner(), response -> {
            if (response.getResp() == 1) {
                loadData();
            }
        });
        return "Request canceled!";
    }
}