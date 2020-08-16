package com.deep.msscbrewery.service.v2;

import com.deep.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {

    Object getBeerById(UUID beerId);
    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDtoV2);
    void updateBeer(UUID beerId, BeerDtoV2 beerDtoV2);
    void deleteById(UUID beerIdV2);
}
