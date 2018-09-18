package training.busboard.web;


import training.busboard.Bus;
import training.busboard.BusStopIDStopPoints;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {

    public String commonName;

    public ArrayList<Bus> buses;


    //gets the results for the id of the stops as a list //
//    public List<BusStopIDStopPoints> stopPoints;

//    public BusStop(){
//
//
//
//    }





//public class BusStop {
//
//    private final String busStops;
//
//    public Model(ArrayList<Bus> busList){
//
//        //Orders buses and returns closest five//
//        this.buses = ClosestBuses.getCloseBuses(busList);
//
//    }
//
//    public ArrayList<Bus> getBuses() {
//        return buses;
//    }


}

