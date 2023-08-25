package com.example.appgleev10.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appgleev10.R;
import com.example.appgleev10.activity.ListEventByCategoryActivity;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.entity.service.Category;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private final String url = ConfigAPI.BASE_URL + "/docstored/download/";

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categories, parent, false);
        }
        Category category = this.getItem(position);
        ImageView imgCategory = convertView.findViewById(R.id.imgCategory);
        TextView txtCategoryName= convertView.findViewById(R.id.txtcategoryName);

        Picasso picasso = new Picasso.Builder(convertView.getContext())
                .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                .build();
        picasso.load(url + category.getPicture().getFileName())
                //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .error(R.drawable.noimage)
                .into(imgCategory);
        txtCategoryName.setText(category.getName());
        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ListEventByCategoryActivity.class);
            intent.putExtra("idC", category.getId()); // GET CATEGORY ID
            getContext().startActivity(intent);
        });
        return convertView;
    }
}
