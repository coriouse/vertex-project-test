package app.demo.controller;

import app.demo.handler.TrackHandlerVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST
 */
@RestController
@RequestMapping(value = "/cryptos")
public class CurrencyTrackController {

    @Autowired
    TrackHandlerVerticle trackHandlerVerticle;


    @RequestMapping(method = RequestMethod.GET, value = "/{from}/{to}")
    public String track(@PathVariable String from, @PathVariable String to) {
        return trackHandlerVerticle.track(from,to);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody String pair) {
        trackHandlerVerticle.add(pair);
    }



}
