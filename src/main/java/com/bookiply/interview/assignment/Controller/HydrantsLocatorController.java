package com.bookiply.interview.assignment.Controller;

import com.bookiply.interview.assignment.Data.NearestHydrantsData;
import com.bookiply.interview.assignment.Facade.HydrantsLocatorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HydrantsLocatorController {

    @Autowired
    private HydrantsLocatorFacade hydrantsLocatorFacade;

    @GetMapping("/hydrants/{longitude}/{latitude}/{numberOfTrucks}")
    public NearestHydrantsData getAllHydrants(@PathVariable("longitude") double longitude,
                                              @PathVariable("latitude") double latitude,
                                              @PathVariable("numberOfTrucks") int n){
        return hydrantsLocatorFacade.getListOfNearestHydrants(longitude,latitude,n); }
}
