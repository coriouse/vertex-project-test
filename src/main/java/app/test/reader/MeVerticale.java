package app.test.reader;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MeVerticale extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        System.out.println("MyVerticle started!");
    }


    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("MyVerticle stopped!");
    }


}
