package training.busboard;

//everything we need to import//
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignores all the other parameters we have not included//
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesResult {

    //returns long and lat//
    public double longitude;
    public double latitude;

}
