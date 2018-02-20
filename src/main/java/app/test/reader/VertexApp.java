package app.test.reader;

import app.test.calculator.EventBusReceiverVerticle;
import io.vertx.core.Vertx;

public class VertexApp {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new EventBusReceiverVerticle("R1"));


        Thread.sleep(3000);
        vertx.deployVerticle(new PriceReaderVerticle("BTC", "USD"));
    }
}
