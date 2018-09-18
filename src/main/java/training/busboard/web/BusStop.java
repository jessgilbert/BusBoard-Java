package training.busboard.web;

//everything we need to import//
import training.busboard.Bus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;


//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {

    //creates string for name of busStop//
    public String commonName;
    //the array with all the buses for the busStop//
    public ArrayList<Bus> buses;

}

