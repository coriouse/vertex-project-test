package app.demo.dispatcher;

import app.demo.calculator.MinMaxCalculatorVerticle;
import app.demo.handler.Pair;
import app.demo.reader.PriceReaderVerticle;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Create new price reader
 */
@Component
public class PriceDispatcher extends AbstractVerticle {

    @Value("${interval}")
    private String interval;

    @Override
    public void start() {
        EventBus eb = vertx.eventBus();
        eb.consumer("DISPATCHER", message -> {
            Pair pair = getPair(message.body().toString());
            System.out.println("Pair [" + pair.getFrom() + "," + pair.getTo() + "] is registered");
            vertx.deployVerticle(new PriceReaderVerticle(pair.getFrom(), pair.getTo(), Long.valueOf(interval)));
            vertx.deployVerticle(new MinMaxCalculatorVerticle(pair.getFrom(), pair.getTo()));
        });
        System.out.println("Receiver ready!");
    }

    private static Pair getPair(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Pair.class);
    }
}
