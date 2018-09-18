package training.busboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClosestBuses {
    public static ArrayList<Bus> getCloseBuses( ArrayList<Bus> busList){

        //create new list//
        ArrayList<Bus> closestFive = new ArrayList<>();

        //sorts the objects in order of smallest time//
        Collections.sort(busList, Comparator.comparing(Bus::getTimeToStation));

        //for loop pust the first 5 closest buses//
        int numberOfBusesToGet = Math.min(5, busList.size());
        for(int i = 0; i < numberOfBusesToGet; i++){
            closestFive.add(busList.get(i));
        }
        return closestFive;
    }

}
