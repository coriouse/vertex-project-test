package app.demo.reader;

import app.demo.handler.Pair;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Init dispatcher verticle
 */
@Component
public class InitReader extends AbstractVerticle {


    private static final String DISPATCHER = "DISPATCHER";

    @Value("${currency}")
    public String currencie;

    @Value("${currencies}")
    public String[] currencies;


    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        for (String c : currencies) {
            Gson gson = new Gson();
            Pair pair = new Pair(c, currencie);

            eb.send(DISPATCHER, gson.toJson(pair), reply -> {
                if (reply.succeeded()) {
                    System.out.println("Received reply " + reply.result().body());
                } else {
                    System.out.println("No reply");
                }
            });
        }
    }


}
