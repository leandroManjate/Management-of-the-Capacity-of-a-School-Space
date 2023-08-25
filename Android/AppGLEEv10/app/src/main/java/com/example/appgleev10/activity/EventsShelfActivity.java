package com.example.appgleev10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgleev10.R;
import com.example.appgleev10.adapter.EventShelfAdapter;
import com.example.appgleev10.comunication.ShelfCommunication;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.dto.GenereteRequestDTO;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.Shelf;
import com.example.appgleev10.utlis.TimeSerializer;
import com.example.appgleev10.viewmodel.RequestViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class EventsShelfActivity extends AppCompatActivity implements ShelfCommunication {

    private RequestViewModel requestViewModel;
    private EventShelfAdapter eventShelfAdapter;
    private RecyclerView rcvMyShelf;
    private Button btnConfirmEvent;

    final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializer())
            .registerTypeAdapter(Time.class, new TimeSerializer())
            .create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_shelf);

        init();
        initViewModel();
        initAdapter();

    }

    private void init(){
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {  //
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        rcvMyShelf = findViewById(R.id.rcvMyShelf);
        btnConfirmEvent = findViewById(R.id.btnConfirmEvent);

        btnConfirmEvent.setOnClickListener(v -> {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String pref = preferences.getString("AdminJson", "");
            Admin admin = gson.fromJson(pref, Admin.class);
            int idV = admin.getVisitant().getId();
            if (idV != 0) {
                if (Shelf.getDetailRequests().isEmpty()) {
                    toastError("Your Shelf is empty! Please, select any event...");
                } else {
                    toastOkay("Loading request...");
                    registerRequest(idV);
                }
            } else {
                toastError("You're not logged, will redirect to the login screen!");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.requestViewModel = vmp.get(RequestViewModel.class);
    }

    private void initAdapter() {
        eventShelfAdapter = new EventShelfAdapter(Shelf.getDetailRequests(), this);
        rcvMyShelf.setLayoutManager(new LinearLayoutManager(this));
        rcvMyShelf.setAdapter(eventShelfAdapter);
    }

    private void registerRequest(int idV){
        ArrayList<DetailRequest> detailRequests = Shelf.getDetailRequests();
        GenereteRequestDTO dto = new GenereteRequestDTO();
        java.util.Date date = new java.util.Date();
        dto.getRequest().setConfirmResquest(new Date(date.getTime()));
        dto.getRequest().setCancelRequest(false);
        dto.getVisitant().setId(idV);
        dto.setDetailRequests(detailRequests);
        this.requestViewModel.saveRequest(dto).observe(this, response -> {
            if(response.getResp() == 1){
                toastOkay("Request Succes! Wait for confirmation message...");
                Shelf.toClean();
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }else{
                toastError("Error! , There's something wrong about the request...");
            }
        });

    }

    public void toastError(String texto) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layouView = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.ll_custom_toast_error));
        TextView textView = layouView.findViewById(R.id.txtMessageToast2);
        textView.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layouView);
        toast.show();

    }

    public void toastOkay(String texto) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layouView = layoutInflater.inflate(R.layout.custom_toast_ok, (ViewGroup) findViewById(R.id.ll_custom_toast_ok));
        TextView textView = layouView.findViewById(R.id.txtMessageToast1);
        textView.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layouView);
        toast.show();
    }

    @Override
    public void deleteDetail(int idE) {
        Shelf.deleteEvent(idE);
        this.eventShelfAdapter.notifyDataSetChanged();
    }


}