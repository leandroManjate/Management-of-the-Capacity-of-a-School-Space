package com.example.appgleev10.utlis;
import com.example.appgleev10.entity.service.DetailRequest;

import java.util.ArrayList;

public class Shelf {
    //We create an arrayList of the order detail class
    private static final ArrayList<DetailRequest> detailRequests = new ArrayList<>();

    //Method to add Event to the Shelf
    public static String reserveEvents(DetailRequest detailRequest) {
        int index = 0;
        boolean b = false;
        for (DetailRequest dr : detailRequests) {
            if (dr.getEvent().getId() == detailRequest.getEvent().getId()) {
                detailRequests.set(index, detailRequest);
                b = true;
                return "Event reserved successfuly!";
            }
            index++;
        }
        if (!b) {
            detailRequests.add(detailRequest);
            return "The Event has been successfully reserved!";
        }
        return ". . . . ";
    }

    //DELETE EVENT
    public static void deleteEvent(final int idE) {
        DetailRequest drE = null;
        for (DetailRequest dr : detailRequests) {
            if (dr.getEvent().getId() == idE) {
                drE = dr;
                break;
            }
        }
        if (drE != null) {
            detailRequests.remove(drE);
            System.out.println("The order detail Event deleted!");
        }
    }

    //GET REQUEST DETAILS
    public static ArrayList<DetailRequest> getDetailRequests() {
        return detailRequests;
    }

    //TO CLEAN
    public static void toClean() {
        detailRequests.clear();
    }

}
