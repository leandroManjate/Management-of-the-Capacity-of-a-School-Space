package com.example.appgleev10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appgleev10.R;
import com.example.appgleev10.adapter.EventsByCategoryAdapter;
import com.example.appgleev10.comunication.ShowBadgeCommunication;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Event;
import com.example.appgleev10.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListEventByCategoryActivity extends AppCompatActivity{

    private EventViewModel eventViewModel;
    private EventsByCategoryAdapter eventCatAdapter;
    private List<Event> events = new ArrayList<>();
    private RecyclerView rcvEventByCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_by_category);

        init();
        initViewModel();
        initAdapter();
        loadData();

    }

    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.eventViewModel = vmp.get(EventViewModel.class);
    }

    private void initAdapter() {
        eventCatAdapter = new EventsByCategoryAdapter(events);
        rcvEventByCat = findViewById(R.id.rcvEventsByCat);
        rcvEventByCat.setAdapter(eventCatAdapter);
        rcvEventByCat.setLayoutManager(new LinearLayoutManager(this));
        //rcvPlatilloPorCategoria.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void loadData() {
        int idC = getIntent().getIntExtra("idC", 0);//GET CATEGORY ID
        eventViewModel.listEventsByCategory(idC).observe(this, response -> {
            eventCatAdapter.updateItems(response.getBody());
        });
    }

}