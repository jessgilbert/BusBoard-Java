package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Main {
    public static void main(String args[]) {

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        client.

    }
}
