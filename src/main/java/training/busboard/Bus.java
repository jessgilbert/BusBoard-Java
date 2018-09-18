package training.busboard;

//everything we need to import//
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.DecimalFormat;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

    //initialises time and name//
    public double timeToStation;
    public String lineName;
    public String destinationName;

    //returns time//
    public int getTimeToStation(){

        double time = timeToStation / 60;
        Math.rint(time);
        int timeInMinutes = (int) time;
        return timeInMinutes;

    }

    public String getDestinationName() {
        return destinationName;
    }

    //returns name//
    public String getLineName(){

        return lineName;
    }

}
