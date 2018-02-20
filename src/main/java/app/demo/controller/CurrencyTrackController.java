package app.demo.controller;


import app.demo.holder.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cryptos")
public class CurrencyTrackController {

    @Autowired
    HolderService holderService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    Object track() {

        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody String pair) {
        holderService.add(pair);
    }



}