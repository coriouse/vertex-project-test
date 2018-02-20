package app.demo.reader;

import app.demo.calculator.MinMaxCalculatorVerticle;
import app.demo.holder.Pair;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;


/**
 * Create new price reader
 */
public class PriceDispatcher extends AbstractVerticle {


    @Override
    public void start() throws Exception {

        Long interval = 2000L;
        EventBus eb = vertx.eventBus();

        eb.consumer("DISPATCHER", message -> {
            Pair pair = getPair(message.body().toString());
            System.out.println("Pair ["+pair.getFrom()+","+pair.getTo()+"] is registered");
            vertx.deployVerticle(new PriceReaderVerticle(pair.getFrom(), pair.getTo(), interval));
            vertx.deployVerticle(new MinMaxCalculatorVerticle(pair.getFrom(), pair.getTo()));


        });

        System.out.println("Receiver ready!");
    }


    private static Pair getPair(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Pair.class);
    }

}
