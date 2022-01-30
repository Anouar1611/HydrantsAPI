package com.bookiply.interview.assignment.Facade;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookiply.interview.assignment.Data.HydrantData;
import com.bookiply.interview.assignment.Data.NearestHydrantsData;
import com.bookiply.interview.assignment.Model.HydrantModel;
import com.bookiply.interview.assignment.Service.HydrantsLocatorService;

@Repository
public class HydrantsLocatorFacade {

    @Autowired
    private HydrantsLocatorService hydrantsLocatorService;

    public NearestHydrantsData getListOfNearestHydrants(double latitude, double longitude, int numberOfTrucks){

        NearestHydrantsData nearestHydrantsData = new NearestHydrantsData();
        List<HydrantModel> hydrantModels = hydrantsLocatorService.getListOfNearestHydrants(latitude,longitude,numberOfTrucks);
        List<HydrantData> hydrantDataList = new LinkedList<>();
        DecimalFormat df = new DecimalFormat("#.#");

        hydrantModels.forEach(hydrantModel -> {

            HydrantData hydrantData = new HydrantData();
            String distanceString = df.format(hydrantModel.getDistanceFromFire());
            hydrantData.setDistanceFromFire(Integer.valueOf(distanceString));
            hydrantData.setUnitId(hydrantModel.getUnitId());
            hydrantDataList.add(hydrantData);
        });

        Integer sum = hydrantDataList.stream().mapToInt(HydrantData::getDistanceFromFire).sum();
        nearestHydrantsData.setNearestHydrants(hydrantDataList);
        nearestHydrantsData.setTotalHydrantsDistance(sum);

        return nearestHydrantsData;
    }


}
