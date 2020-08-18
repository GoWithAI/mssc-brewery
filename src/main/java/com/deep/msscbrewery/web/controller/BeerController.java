package com.deep.msscbrewery.web.controller;

import com.deep.msscbrewery.web.model.BeerDto;
import com.deep.msscbrewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Deprecated
@Slf4j  //directly can use log around application
@RequiredArgsConstructor //Genrate Constructor for us
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;
    //Best Practice Instead autowire
    /*public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }*/

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        //BeerDto savedDto=beerService.saveNewBeer(beerDto);
        //HttpHeaders headers = new HttpHeaders();
        val savedDto=beerService.saveNewBeer(beerDto);
        var headers = new HttpHeaders();
        log.debug("in handle post....");
        //todo add hostname to url
        headers.add("Location", "/api/v1/beer"+savedDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    //Idompotent
    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId,beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }

}

