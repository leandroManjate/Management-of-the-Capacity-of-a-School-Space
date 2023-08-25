package com.example.appgleev10.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.appgleev10.R;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.databinding.ActivityStartBinding;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.Shelf;
import com.example.appgleev10.utlis.TimeSerializer;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.sql.Time;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarStart.toolbar);
        binding.appBarStart.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_events, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_start);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                this.logout();
                break;
            case R.id.myShelf:
                this.showShelf();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showShelf() {
        Intent i = new Intent(this, EventsShelfActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @SuppressLint({"UnsafeExperimentalUsageError", "UnsafeOptInUsageError"})
    private void loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        String adminJson = sp.getString("AdminJson", null);

        if(adminJson != null){

            final Admin admin = gson.fromJson(adminJson, Admin.class);
            final View viewHeader = binding.navView.getHeaderView(0);
            final TextView tvName = viewHeader.findViewById(R.id.tvUserNameNH),
                    tvEmail = viewHeader.findViewById(R.id.tvEmailNH);
            final CircleImageView imgPhoto = viewHeader.findViewById(R.id.imgPhotoNH);
            tvName.setText(admin.getVisitant().getCompleteName());
            tvEmail.setText(admin.getEmail());
            String url = ConfigAPI.BASE_URL + "/docstored/download/" + admin.getVisitant().getPicture().getFileName();
            final Picasso picasso = new Picasso.Builder(this)
                    .downloader(new OkHttp3Downloader(ConfigAPI.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.noimage)
                    .into(imgPhoto);
        }
        BadgeDrawable badgeDrawable = BadgeDrawable.create(this);
        badgeDrawable.setNumber(Shelf.getDetailRequests().size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable, findViewById(R.id.toolbar), R.id.myShelf);
    }

    private void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("AdminJson");
        editor.apply();
        this.finish();
        this.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_start);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

    }
}