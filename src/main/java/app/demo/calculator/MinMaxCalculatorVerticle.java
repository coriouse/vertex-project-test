package app.demo.calculator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class MinMaxCalculatorVerticle extends AbstractVerticle {




    private String name = null;

    public MinMaxCalculatorVerticle(String name) {
        this.name = name;
    }

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("ping-address", message -> {

            System.out.println("Received message: " + message.body());
            // Now send back reply
            message.reply("pong!" +name);
        });

        System.out.println("Receiver ready!");
    }



}
