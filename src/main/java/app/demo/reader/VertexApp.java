package app.demo.reader;

import app.demo.calculator.MinMaxCalculatorVerticle;
import app.demo.holder.HolderService;
import app.demo.holder.HolderServiceImpl;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class VertexApp {



    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new PriceDispatcher());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vertx.deployVerticle(new InitReader());


    }


    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();


        vertx.deployVerticle(new PriceDispatcher());

        Thread.sleep(2000);

        vertx.deployVerticle(new InitReader());
    }
}
