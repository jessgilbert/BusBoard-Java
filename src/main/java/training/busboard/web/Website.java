package training.busboard.web;

//everything we need to import//
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.Bus;
import training.busboard.BusStopID;
import training.busboard.ClosestBuses;
import training.busboard.Coordinates;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
public class Website {

    // when it is just "/" it tells it to go to index.html//
    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }


    //requests postcode as string//
    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {

        //creates builder for json reader//
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        //creates Coordinates and CoordinatesResult object, gets Long and Lat//
        Coordinates postcodeInfo = client
                .target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Coordinates>() {});

        //gets the busStop id from long and lat input as an Object "BusStopID"//
        BusStopID busID = client
                .target("https://api-argon.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&radius=1000&lat="
                        + postcodeInfo.result.latitude + "&lon=" + postcodeInfo.result.longitude)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<BusStopID>() {});

        //creates an array for busStop object//
        ArrayList<BusStop> busStops = new ArrayList<>();

        //for loop that iterates through the BusStops and puts them in the Array//
        for (int i = 0; i < 5; i++) {
            //creates new object BusStop//
            BusStop stop = new BusStop();

            //makes commonName in BusStop object equal to its common name//
            stop.commonName = busID.stopPoints.get(i).commonName;

            //creates an array full of object Bus that are coming to that stop//
            ArrayList<Bus> busList = client
                    .target("https://api-argon.tfl.gov.uk/StopPoint/" + busID.stopPoints.get(i).id + "/Arrivals")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(new GenericType<ArrayList<Bus>>() {
                    });
            //puts the array into class closest BusList to order them and get the next five buses//
            ArrayList<Bus> closestBusList = ClosestBuses.getCloseBuses(busList);
            //makes the array full of object bus equal to BusStop buses//
            stop.buses = closestBusList;

            //adds the object BusStop to stop Array//
            busStops.add(stop);
        }

        // Make Model object and make busStop equal to array busStop//
        Model model = new Model();
        model.busStops = busStops;

        // Tells where your sending in ("info")//
        // Class you can access (model) and how to reference it in the html ("model")
        return new ModelAndView("info", "model", model) ;
    }

    //entry point to the app//
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}