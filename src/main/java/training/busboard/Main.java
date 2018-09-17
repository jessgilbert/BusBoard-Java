package training.busboard;

//import everything we need//
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class Main {
    public static void main(String args[]) {

        //creates builder for json reader//
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        //creates a list of object bus, gets Json file from URL//
        List<Bus> busList = client
                .target("https://api-argon.tfl.gov.uk")
                .path("StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Bus>>() {});


        //displays all the names and buses//
        display(busList);
    }


    public static void display(List<Bus> busList){

        //itterates through list and shows time and name for each bus//
        for(int i = 0; i < busList.size(); i++){
            System.out.println(busList.get(i).getBusName());
            System.out.println(busList.get(i).getTime());
        }
    }

}
