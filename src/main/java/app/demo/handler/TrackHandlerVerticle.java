package app.demo.handler;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.stereotype.Component;

/**
 * Handler for modification
 *
 */
@Component
public class TrackHandlerVerticle extends AbstractVerticle {


    private static final String DISPATCHER = "DISPATCHER";

    EventBus eb;

    @Override
    public void start() {
        eb = vertx.eventBus();
    }

    public void add(String json) {
        eb.send(DISPATCHER, json);
    }


    public String track(String from, String to) {
        //TODO read data from storage or cache
        //TODO this code is wrong
        Gson gson = new Gson();
        String adderess = from + "-" + to + "-STATUS";
        final String[] minMax = {null};
        eb.send(adderess, "ping!", reply -> {
            if (reply.succeeded()) {
                minMax[0] = reply.result().body().toString();

                System.out.println("Received reply " + reply.result().body());
            } else {
                System.out.println("No reply for [" + from + "],[" + to + "]");
            }
        });
        return minMax[0];
    }


}
