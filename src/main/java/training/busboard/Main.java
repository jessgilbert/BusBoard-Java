package training.busboard;

//import everything we need//
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.*;


public class Main {
    public static void main(String args[]) {

        //creates builder for json reader//
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        //calls user input method
        String stopCode = getCodeInput();

        //creates a list of object bus, gets Json file from URL//
        ArrayList<Bus> busList = client
                .target("https://api-argon.tfl.gov.uk")
                .path("StopPoint/" + stopCode + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ArrayList<Bus>>() {});



        //displays all the names and buses//
        display(getClosestBuses(busList));
    }

    public static String getCodeInput() {

        //creates scanner that can read line//
        Scanner userinput = new Scanner(System.in);

        //asks for input and returns it//
        System.out.println("Input stop code:");
        userinput.hasNextLine();
        String stopCode = userinput.nextLine();
        return stopCode;

    }

    public static void display(ArrayList<Bus> busList){

        //itterates through list and shows time and name for each bus//
        for(int i = 0; i < busList.size(); i++){
            System.out.println("Bus name: " + busList.get(i).getBusName() + " in " + busList.get(i).getTime() + " seconds ");

        }
    }

    public static ArrayList<Bus> getClosestBuses(ArrayList<Bus> busList){
        //create new list//
        ArrayList<Bus> closestFive = new ArrayList<>();

        //sorts the objects in order of smallest time//
        Collections.sort(busList, Comparator.comparing(Bus::getTime));

        //for loop pust the first 5 closest buses//
        for(int i = 0; i < 5; i++){
            closestFive.add(busList.get(i));
        }
        return closestFive;
    }


}





