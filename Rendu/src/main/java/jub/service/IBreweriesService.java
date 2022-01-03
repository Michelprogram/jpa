package jub.service;

import jub.model.Brewery;

import java.util.List;

public interface IBreweriesService {
    List<Brewery> getBreweries();
    void init();
    Brewery getBrewery(Integer Id);
    Boolean addBrewery(Brewery brewery);
    Boolean updateBrewery(Brewery brewery);
    Boolean removeBrewery(Brewery brewery);
}
