package training.busboard.web;

import training.busboard.Bus;
import training.busboard.ClosestBuses;

import java.util.ArrayList;

public class BusInfo {
    private final ArrayList<Bus> buses;

    public BusInfo(ArrayList<Bus> busList){

        //Orders buses and returns closest five//
       this.buses = ClosestBuses.getCloseBuses(busList);

    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }


}

