package com.example.appgleev10.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgleev10.R;
import com.example.appgleev10.activity.DetailEventActivity;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.comunication.Communication;
import com.example.appgleev10.comunication.ShowBadgeCommunication;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Event;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class RecomendedEventsAdapter extends RecyclerView.Adapter<RecomendedEventsAdapter.ViewHolder> {
    private List<Event> events;
    private final Communication communication;
    private final ShowBadgeCommunication sbCommunication;

    public RecomendedEventsAdapter(List<Event> events, Communication communication, ShowBadgeCommunication sbCommunication) {
        this.events = events;
        this.communication = communication;
        this.sbCommunication = sbCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.events.get(position));
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    public void updateItems(List<Event> event) {
        this.events.clear();
        this.events.addAll(event);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final Event event) {
            ImageView imgEvent = itemView.findViewById(R.id.imgEventRec);
            TextView nameEvent = itemView.findViewById(R.id.nameEventRec);
            Button btnToAsk = itemView.findViewById(R.id.btnToAskRec);

            String url = ConfigAPI.BASE_URL + "/docstored/download/" + event.getPicture().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.noimage)
                    .into(imgEvent);
            nameEvent.setText(event.getName());
            btnToAsk.setOnClickListener(v -> {
                DetailRequest detailRequest = new DetailRequest();
                detailRequest.setEvent(event);
                detailRequest.setQuantity(1);
                //detailRequest.setEvent(detailRequest.getEvent().getVacancy());
                sbCommunication.add(detailRequest);
               // Toast.makeText(itemView.getContext(), "Hello World!", Toast.LENGTH_SHORT).show();
            });

            itemView.setOnClickListener(v -> {
                final Intent intent = new Intent(itemView.getContext(), DetailEventActivity.class);
                final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                intent.putExtra("detailEvent", gson.toJson(event));
                communication.showDetails(intent);

            });

        }
    }
}
