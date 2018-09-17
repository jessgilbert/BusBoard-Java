package training.busboard;
//everything we need to import//
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    //gets the results form CoordiantesResult//
    public CoordinatesResult result;

}
