package com.example.appgleev10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgleev10.R;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Event;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.Shelf;
import com.example.appgleev10.utlis.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Time;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetailEventActivity extends AppCompatActivity {

    private ImageView imgEventDet;
    private Button btnToShelf, btnConfirm;
    private TextView tvNameEventDet, tvVacancies, tvDescripcionEventDet, tvAuthor, tvDateEvent;
    final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializer())
            .registerTypeAdapter(Time.class, new TimeSerializer())
            .create();
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        init();
        loadData();
    }

    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        this.imgEventDet = findViewById(R.id.imgEventDET2);
        this.btnToShelf = findViewById(R.id.btnToShelfDET2);
        this.btnConfirm = findViewById(R.id.btnConfirmEventDET2);
        this.tvNameEventDet = findViewById(R.id.tvNameEventDET2);
        this.tvVacancies = findViewById(R.id.tvVacancyDET2);
        this.tvDescripcionEventDet = findViewById(R.id.tvDescripcionEventDET2);
        this.tvAuthor = findViewById(R.id.tvAuthorEventDET2);
        this.tvDateEvent = findViewById(R.id.tvDateEventDET2);

    }

    private void loadData() {
        final String detailString = this.getIntent().getStringExtra("detailEvent");
        if (detailString != null) {
            event = gson.fromJson(detailString, Event.class);
            this.tvNameEventDet.setText(event.getName());
            this.tvVacancies.setText(String.valueOf("Vacancies Number: " + event.getVacancy()));
            this.tvDescripcionEventDet.setText(event.getDescripcionEvent());
            this.tvAuthor.setText(event.getAuthorEvent());
            this.tvDateEvent.setText(event.getDateEvent());

            String url = ConfigAPI.BASE_URL + "/docstored/download/" + event.getPicture().getFileName();

            Picasso picasso = new Picasso.Builder(this)
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.noimage)
                    .into(this.imgEventDet);
        } else {
            System.out.println("Error obtaining the event details");
        }
        //ADD TO SHELF (RESERVE)
        this.btnToShelf.setOnClickListener(v -> {

            DetailRequest detailRequest = new DetailRequest();

            detailRequest.setEvent(event);
            detailRequest.setQuantity(1);
            detailRequest.getEvent().setVacancy(event.getVacancy());
            successMessage(Shelf.reserveEvents(detailRequest));
        });

        this.btnConfirm.setOnClickListener(v -> {
            toastError("Feature not active yet");
        });

    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("GREAT!")
                .setContentText(message).show();
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

}