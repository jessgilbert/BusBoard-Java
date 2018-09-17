package training.busboard;

//everything we need to import//
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

    //initialises time and name//
    public int timeToStation;
    public String lineName;

    //returns time//
    public int getTime(){
        return timeToStation;
    }

    //returns name//
    public String getBusName(){
        return lineName;
    }

}
