package com.example.appgleev10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgleev10.R;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.entity.service.DetailRequest;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailMyEventsAdapter extends RecyclerView.Adapter<DetailMyEventsAdapter.ViewHolder> {
    private final List<DetailRequest> details;

    public DetailMyEventsAdapter(List<DetailRequest> detalles) {
        this.details = detalles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_my_events, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.setItem(this.details.get(position));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
    public void updateItems(List<DetailRequest> details){
        this.details.clear();
        this.details.addAll(details);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgEvent;
        private TextView txtValueCodDetailEvent, txtValueEvent, txtValueQuantity, txtValueVacancy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgEvent = itemView.findViewById(R.id.imgEventDET);
            this.txtValueCodDetailEvent = itemView.findViewById(R.id.txtValueCodDetailEvent);
            this.txtValueEvent = itemView.findViewById(R.id.txtValueEvent);
            this.txtValueQuantity = itemView.findViewById(R.id.txtValueQuantity);
            this.txtValueVacancy = itemView.findViewById(R.id.txtValueVacancyDET);
        }

        public void setItem(final DetailRequest detailRequest) {
            String url = ConfigAPI.BASE_URL + "/docstored/download/" + detailRequest.getEvent().getPicture().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.noimage)
                    .into(imgEvent);
            txtValueCodDetailEvent.setText("C000" + Integer.toString(detailRequest.getRequest().getId()));
            txtValueEvent.setText(detailRequest.getEvent().getName());
            txtValueQuantity.setText(Integer.toString(detailRequest.getQuantity()));
            txtValueVacancy.setText(Integer.toString(detailRequest.getEvent().getVacancy()));
        }
    }
}
