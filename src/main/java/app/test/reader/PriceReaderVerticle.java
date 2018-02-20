package app.test.reader;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class PriceReaderVerticle extends AbstractVerticle {

    private String from;
    private String to;


    public PriceReaderVerticle(String from, String to) {
        this.from = from;
        this.to = to;
    }


    

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();

        // Send a message every second

        vertx.setPeriodic(1000, v -> {

            eb.send("ping-address", "ping!", reply -> {
                if (reply.succeeded()) {
                    System.out.println("Received reply " + reply.result().body());
                } else {
                    System.out.println("No reply");
                }
            });

        });
    }
}
