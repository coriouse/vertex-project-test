package app.demo.reader;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Price reader
 *
 *
 */
public class PriceReaderVerticle extends AbstractVerticle {

    //https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD
    private static final String URI = "https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=%s";

    private String from;
    private String to;

    private Long interval;

    private RestTemplate restTemplate = new RestTemplate();

    public PriceReaderVerticle(String from, String to, Long interval) {
        this.from = from;
        this.to = to;
        this.interval = interval;
    }


    @Override
    public void start() throws Exception {
        String address = from+"-"+to;
        EventBus eb = vertx.eventBus();
        vertx.setPeriodic(interval, v -> {
            eb.send(address, readPrice(), reply -> {
                if (reply.succeeded()) {
                    System.out.println("Received reply " + reply.result().body());
                } else {
                    System.out.println("No reply");
                }
            });
        });
    }

    private String readPrice() {
        String URL = String.format(URI, from, to);
        Map<String, Float> price = restTemplate.getForObject(URL, Map.class);
        return String.valueOf(price.get(to));


    }


}
