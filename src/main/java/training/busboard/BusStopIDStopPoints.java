package training.busboard;

//everything we need to import//
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopIDStopPoints {

    //returns bus stop id//
    public String id;
    public String stopLetter;
    public String commonName;

}
