package app.demo.dispatcher;

import app.demo.dispatcher.PriceDispatcher;
import app.demo.handler.TrackHandlerVerticle;
import app.demo.reader.InitReader;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Start verticls
 */
@Component
public class VertexApp {

    @Autowired
    private TrackHandlerVerticle trackHandlerVerticle;

    @Autowired
    private InitReader initReader;

    @Autowired
    PriceDispatcher priceDispatcher;

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(priceDispatcher);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vertx.deployVerticle(trackHandlerVerticle);
        vertx.deployVerticle(initReader);


    }


   /* public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new PriceDispatcher());
        Thread.sleep(2000);
        vertx.deployVerticle(new InitReader());
    }*/
}
