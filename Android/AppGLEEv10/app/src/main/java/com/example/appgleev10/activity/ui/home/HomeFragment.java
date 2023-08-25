package com.example.appgleev10.activity.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgleev10.R;
import com.example.appgleev10.adapter.CategoryAdapter;
import com.example.appgleev10.adapter.RecomendedEventsAdapter;
import com.example.appgleev10.adapter.SliderAdapter;
import com.example.appgleev10.comunication.Communication;
import com.example.appgleev10.comunication.ShowBadgeCommunication;
import com.example.appgleev10.entity.SliderItem;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Event;
import com.example.appgleev10.utlis.Shelf;
import com.example.appgleev10.viewmodel.CategoryViewModel;
import com.example.appgleev10.viewmodel.EventViewModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeFragment extends Fragment implements Communication, ShowBadgeCommunication {

    private CategoryViewModel categoryViewModel;
    private EventViewModel eventViewModel;
    private RecyclerView rcvRecEvents;
    private RecomendedEventsAdapter recEventsdAdapter;
    private List<Event> events = new ArrayList<>();
    private GridView gridViewCat;
    private CategoryAdapter categoryAdapter;
    private SliderView svCarrusel;
    private SliderAdapter sliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View view) {

        svCarrusel = view.findViewById(R.id.svCarrusel);
        ViewModelProvider vmp = new ViewModelProvider(this);

        categoryViewModel = vmp.get(CategoryViewModel.class);
        gridViewCat = view.findViewById(R.id.gvCategories);

        rcvRecEvents = view.findViewById(R.id.rcvRecomendedEvents);
        rcvRecEvents.setLayoutManager(new GridLayoutManager(getContext(), 1));
        eventViewModel = vmp.get(EventViewModel.class);

    }

    private void initAdapter() {
        //Carrusel of Imagens
        sliderAdapter = new SliderAdapter(getContext());

        svCarrusel.setSliderAdapter(sliderAdapter);
        svCarrusel.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svCarrusel.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svCarrusel.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svCarrusel.setIndicatorSelectedColor(Color.WHITE);
        svCarrusel.setIndicatorUnselectedColor(Color.GRAY);
        svCarrusel.setScrollTimeInSec(4); //set scroll delay in seconds :
        svCarrusel.startAutoCycle();

        categoryAdapter = new CategoryAdapter(getContext(), R.layout.item_categories, new ArrayList<>());
        gridViewCat.setAdapter(categoryAdapter);

        recEventsdAdapter = new RecomendedEventsAdapter(events, this, this);
        rcvRecEvents.setAdapter(recEventsdAdapter);

    }

    private void loadData() {
        List<SliderItem> list = new ArrayList<>();
        list.add(new SliderItem(R.drawable.intro_springboot, "Spring Boot Management"));
        list.add(new SliderItem(R.drawable.android_lessons, "Intro to Android Studio 8.0"));
        list.add(new SliderItem(R.drawable.rest_api, "Learning About REST APIs"));

        sliderAdapter.updateItem(list);

        categoryViewModel.listActiveCategories().observe(getViewLifecycleOwner(), response -> {
            if(response.getResp() == 1){
                categoryAdapter.clear();
                categoryAdapter.addAll(response.getBody());
                categoryAdapter.notifyDataSetChanged();
            }else{
                System.out.println("Error getting active categories! ");
            }
        });

        eventViewModel.listRecomendedEvents().observe(getViewLifecycleOwner(), response -> {
            recEventsdAdapter.updateItems(response.getBody());
        });

    }

    @Override
    public void showDetails(Intent i) {
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }


    @SuppressLint("UnsafeOptInUsageError")
    @Override
    public void add(DetailRequest detailRequest) {
        successMessage(Shelf.reserveEvents(detailRequest));
        BadgeDrawable badgeDrawable = BadgeDrawable.create(this.getContext());
        badgeDrawable.setNumber(Shelf.getDetailRequests().size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable, getActivity().findViewById(R.id.toolbar), R.id.myShelf);
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this.getContext(),
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("GREAT!")
                .setContentText(message).show();
    }

}