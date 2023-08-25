package com.example.appgleev10.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgleev10.R;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.comunication.ShelfCommunication;
import com.example.appgleev10.entity.service.DetailRequest;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EventShelfAdapter extends RecyclerView.Adapter<EventShelfAdapter.ViewHolder> {

    private final List<DetailRequest> details;
    private final ShelfCommunication sc;

    public EventShelfAdapter(List<DetailRequest> details, ShelfCommunication sc) {
        this.details = details;
        this.sc = sc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events_shelf, parent, false);
        return new ViewHolder(v, this.sc);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.details.get(position));
    }

    @Override
    public int getItemCount() {
        return this.details.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgEvent, btnDeleteEShelf;
        private final EditText edtQuantity;
        private final TextView tvNameEvent, txtVancancies;
        private final ShelfCommunication sc;


        public ViewHolder(@NonNull View itemView, ShelfCommunication sc) {
            super(itemView);
            this.sc = sc;
            this.imgEvent = itemView.findViewById(R.id.imgEventS);
            this.btnDeleteEShelf = itemView.findViewById(R.id.btnDeleteEventS);
            this.tvNameEvent = itemView.findViewById(R.id.tvEventName);
            this.edtQuantity = itemView.findViewById(R.id.edtQuantity);
            this.txtVancancies = itemView.findViewById(R.id.tvVancanciesSH);
        }

        public void setItem(final DetailRequest dr) {
            this.tvNameEvent.setText(dr.getEvent().getName());
            this.txtVancancies.setText(String.valueOf("Event Vacancies: " + dr.getEvent().getVacancy()));
            this.edtQuantity.setText(String.valueOf(dr.getQuantity()));


            String url = ConfigAPI.BASE_URL + "/docstored/download/" + dr.getEvent().getPicture().getFileName();
            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.noimage)
                    .into(this.imgEvent);

            //------------------DELETE EVENT FROM SHELF-----------------------
            this.btnDeleteEShelf.setOnClickListener(v -> {
                /*toastCorrecto("Event deleted successfully");
                c.eliminarDetalle(dp.getPlatillo().getId());*/
                showMsg(dr.getEvent().getId());
            });


        }

        public void toastOk(String text) {
            LayoutInflater layoutInflater = LayoutInflater.from(itemView.getContext());
            View layouView = layoutInflater.inflate(R.layout.custom_toast_ok, (ViewGroup) itemView.findViewById(R.id.ll_custom_toast_ok));
            TextView textView = layouView.findViewById(R.id.txtMessageToast1);
            textView.setText(text);

            Toast toast = new Toast(itemView.getContext());
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layouView);
            toast.show();
        }
        private void showMsg(int idEvent) {
            new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("System warning!")
                    .setContentText("Do you want to withdraw from the event?")
                    .setCancelText("No").setConfirmText("Yes")
                    .showCancelButton(true).setCancelClickListener(sDialog -> {
                sDialog.dismissWithAnimation();
                new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.ERROR_TYPE).setTitleText("Operation canceled")
                        .setContentText("You may continue")
                        .show();
            }).setConfirmClickListener(sweetAlertDialog -> {
                sc.deleteDetail(idEvent);
                sweetAlertDialog.dismissWithAnimation();
                new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Great!")
                        .setContentText("Event deleted successfully")
                        .show();
            }).show();
        }
    }
}
