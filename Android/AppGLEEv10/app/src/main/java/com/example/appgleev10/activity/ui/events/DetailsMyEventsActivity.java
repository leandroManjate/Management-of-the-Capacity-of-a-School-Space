package com.example.appgleev10.activity.ui.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appgleev10.R;
import com.example.appgleev10.adapter.DetailMyEventsAdapter;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DetailsMyEventsActivity extends AppCompatActivity {

    private RecyclerView rcvDetailsEvent;
    private DetailMyEventsAdapter detailMyEventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my_events);
        init();
        initAdapter();
        loadData();
    }

    private void init() {
        rcvDetailsEvent = findViewById(R.id.rcvDetailEvents);
        rcvDetailsEvent.setLayoutManager(new GridLayoutManager(this, 1));
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });
    }

    private void initAdapter() {
        detailMyEventsAdapter = new DetailMyEventsAdapter(new ArrayList<>());
        rcvDetailsEvent.setAdapter(detailMyEventsAdapter);
    }

    private void loadData() {
        final String detailString = this.getIntent().getStringExtra("detailsEvent");
        if (detailString != null) {
            final Gson g = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateSerializer())
                    .registerTypeAdapter(Time.class, new TimeSerializer())
                    .create();
            List<DetailRequest> detailRequests = g.fromJson(detailString,
                    new TypeToken<List<DetailRequest>>() {
                    }.getType());
            detailMyEventsAdapter.updateItems(detailRequests);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        this.overridePendingTransition(R.anim.down_in, R.anim.down_out);
    }
}