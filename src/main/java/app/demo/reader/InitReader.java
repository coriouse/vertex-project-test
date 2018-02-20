package app.demo.reader;

import app.demo.holder.Pair;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;


/**
 * Test init class app
 */

public class InitReader extends AbstractVerticle {


    public String currencie = "USD";
    public String[] currencies = {"ETH", "DASH", "LTC", "BTC"};


    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        for(String c  : currencies) {
            Gson gson = new Gson();
            Pair pair = new Pair(c, currencie);

            eb.send("DISPATCHER", gson.toJson(pair), reply -> {
                if (reply.succeeded()) {
                    System.out.println("Received reply " + reply.result().body());
                } else {
                    System.out.println("No reply");
                }
            });
        }
    }



}
