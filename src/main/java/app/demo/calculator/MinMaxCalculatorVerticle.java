package app.demo.calculator;

import app.demo.handler.MinMax;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;


/**
 * Calculator Min and Max
 * Holder for min and max value
 */
public class MinMaxCalculatorVerticle extends AbstractVerticle {


    private String from;
    private String to;

    private MinMax minMax;

    public MinMaxCalculatorVerticle(String from, String to) {
        this.from = from;
        this.to = to;
        this.minMax = new MinMax();

    }


    @Override
    public void start() throws Exception {
        String address = from + "-" + to;
        EventBus eb = vertx.eventBus();

        eb.consumer(address, message -> {

            Float curr = Float.valueOf(message.body().toString());

            if (curr > minMax.getMax()) {
                minMax.setMin(minMax.getMax());
                minMax.setMax(curr);
            }

            System.out.println("Calculated for [" + address + "]: " + minMax.toString());

        });

        eb.consumer(address + "-STATUS", message -> {
            Gson gson = new Gson();
            message.reply(gson.toJson(minMax));

            System.out.println("Status for for [" + address + "]: " + minMax.toString());
        });

        System.out.println("Calculator for [" + address + "] is ready!");
    }

}
