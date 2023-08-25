package com.example.appgleev10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgleev10.R;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.comunication.ShowBadgeCommunication;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Event;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class EventsByCategoryAdapter extends RecyclerView.Adapter<EventsByCategoryAdapter.ViewHolder> {
    private List<Event> eventListByCategory;
    //private final ShowBadgeCommunication sbCommunication;


    public EventsByCategoryAdapter(List<Event> eventListByCategory) {
        this.eventListByCategory = eventListByCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events_by_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.eventListByCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return this.eventListByCategory.size();
    }
    public void updateItems(List<Event> eventsByCategory){
        this.eventListByCategory.clear();
        this.eventListByCategory.addAll(eventsByCategory);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgEventC;
        private final TextView nameEventC, txtVacanciesC;
        private final Button btnToAsk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgEventC = itemView.findViewById(R.id.imgEventCat);
            this.nameEventC = itemView.findViewById(R.id.nameEventCat);
            this.txtVacanciesC = itemView.findViewById(R.id.txtVacanciesC);
            this.btnToAsk = itemView.findViewById(R.id.btnToAskCAT);
        }

        public void setItem(final Event event) {
            String url = ConfigAPI.BASE_URL + "/docstored/download/" + event.getPicture().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //
                    .error(R.drawable.noimage)
                    .into(imgEventC);
            nameEventC.setText(event.getName());
            txtVacanciesC.setText("  Vacancies: " + String.valueOf(event.getVacancy()));
            btnToAsk.setOnClickListener(v -> {
                Toast.makeText(this.itemView.getContext(), "You clicked to Ask something", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
