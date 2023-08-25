package com.example.appgleev10.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgleev10.R;
import com.example.appgleev10.activity.ui.events.DetailsMyEventsActivity;
import com.example.appgleev10.comunication.CancelRequestCommunication;
import com.example.appgleev10.comunication.Communication;
import com.example.appgleev10.entity.service.dto.RequestWithDetailDTO;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyEventsAdapter extends RecyclerView.Adapter<MyEventsAdapter.ViewHolder> {
    private final List<RequestWithDetailDTO> requests;
    private final Communication communication;
    private final CancelRequestCommunication cancelRequestCommunication;

    public MyEventsAdapter(List<RequestWithDetailDTO> requests, Communication communication, CancelRequestCommunication cancelRequestCommunication) {
        this.requests = requests;
        this.communication = communication;
        this.cancelRequestCommunication = cancelRequestCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_selected_events, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.setItem(this.requests.get(position));
    }

    @Override
    public int getItemCount() {
        return this.requests.size();
    }
    public void updateItems(List<RequestWithDetailDTO> request){
        this.requests.clear();
        this.requests.addAll(request);
        this.notifyDataSetChanged();
     }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final RequestWithDetailDTO dto) {
            final TextView txtValueCodEvent = this.itemView.findViewById(R.id.txtValueCodEvent),
                    txtValueDateEvent = this.itemView.findViewById(R.id.txtValueDateEvent),
                    txtValueRequest = this.itemView.findViewById(R.id.txtValueRequest);
            //final Button btnExportInvoice = this.itemView.findViewById(R.id.btnExportInvoice);
            txtValueCodEvent.setText("C000" + Integer.toString(dto.getRequest().getId()));
            txtValueDateEvent.setText(String.valueOf(dto.getRequest().getConfirmResquest().toString()));
            txtValueRequest.setText(dto.getRequest().isCancelRequest() ? "Request canceled" : "Accepted!!!");

            itemView.setOnClickListener(v -> {
                final Intent i = new Intent(itemView.getContext(), DetailsMyEventsActivity.class);
                final Gson g = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                i.putExtra("detailsEvent", g.toJson(dto.getDetailRequest()));
                communication.showDetails(i);
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    cancelRequest(dto.getRequest().getId());
                    return true;
                }
            });
        }

        private void cancelRequest(int id) {
            new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("System Warning!")
                    .setContentText("Are you sure to cancel the requested order?, If yes, you can't change anything")
                    .setCancelText("No").setConfirmText("Yes")
                    .showCancelButton(true)
                    .setConfirmClickListener(sDialog -> {
                        sDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Great!")
                                .setContentText(cancelRequestCommunication.cancelRequest(id))
                                .show();
                    }).setCancelClickListener(sDialog -> {
                        sDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.ERROR_TYPE).setTitleText("Operation canceled!")
                                .setContentText("You don't cancel any request")
                                .show();
                    }).show();
        }

    }
}
