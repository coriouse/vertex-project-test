package app.demo.holder;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class HolderServiceImpl extends AbstractVerticle implements HolderService {


    private final static BlockingQueue<Pair> blockingQueue = new ArrayBlockingQueue(5);

    @PostConstruct
    public void init() {
        Vertx.vertx().deployVerticle(this);
    }
    EventBus eb;

    @Override
    public void start() throws Exception {
        eb = vertx.eventBus();
        System.out.println("Init holder!");


    }

    @Override
    public void add(String json) {

        //blockingQueue.add(getPair(json));
        System.out.println("ADD = "+json);
        eb.send("DISPATCHER", json);
    }

    @Override
    public MinMax getMinMaxByCurrencies(String json) {
        return null;
    }



}
