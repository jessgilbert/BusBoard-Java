package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.Bus;
import training.busboard.BusStopID;
import training.busboard.Coordinates;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {

        // Talk to TFL API
        //
        //
        //

        ///








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

        //outputs the id of the two nearest stops//
        System.out.println(busID.stopPoints.get(0).id);
        System.out.println(busID.stopPoints.get(1).id);
        System.out.println(busID.stopPoints.get(2).id);
        System.out.println(busID.stopPoints.get(3).id);


        ArrayList<Bus> busList = client
                .target("https://api-argon.tfl.gov.uk/StopPoint/" + busID.stopPoints.get(0).id + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ArrayList<Bus>>() {
                });

        // Make BusInfo object
        BusInfo busInfo = new BusInfo(busList);

        // Generate HTML
        return new ModelAndView("info", "busInfo", busInfo) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}