package training.busboard;
//everything we need to import//
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopID {

    //gets the results for the id of the stops as a list //
    public List<BusStopIDStopPoints> stopPoints;

}
