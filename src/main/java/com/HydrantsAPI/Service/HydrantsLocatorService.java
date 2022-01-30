package com.bookiply.interview.assignment.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bookiply.interview.assignment.Model.HydrantModel;

@Repository
public class HydrantsLocatorService {

    @Value("${url.hydrants.of.NewYork.city}")
    private String url;

    public List<HydrantModel> getListOfNearestHydrants(double latitude, double longitude, int numberOfTrucks){


        RestTemplate restTemplete = new RestTemplate();
        ResponseEntity<HydrantModel[]> listHydrants= restTemplete.getForEntity(url, HydrantModel[].class);
        List <HydrantModel> hydrantModels = new ArrayList<>(Arrays.asList(listHydrants.getBody()));

        hydrantModels.sort((HydrantModel h1, HydrantModel h2) -> {
            if (distance(h1.getLatitude(), latitude, h1.getLongitude(), longitude) <
                    distance(h2.getLatitude(), latitude, h2.getLongitude(), longitude)){
                return 1;
            }else if (distance(h1.getLatitude(), latitude, h1.getLongitude(), longitude) >
                    distance(h2.getLatitude(), latitude, h2.getLongitude(), longitude)){
                return -1;
            }
            return 0;
        });

        hydrantModels.subList(numberOfTrucks, hydrantModels.size()).clear();
        hydrantModels.stream()
                .forEach(hydrant -> hydrant.setDistanceFromFire(distance(hydrant.getLatitude(), latitude, hydrant.getLongitude(), longitude)));

        return hydrantModels;
    }

    public static int distance(double latitude1, double latitude2, double longitude1, double longitude2)
    {
        longitude1 = Math.toRadians(longitude1);
        longitude2 = Math.toRadians(longitude2);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double differenceLongitude = longitude2 - longitude1;
        double differenceLatitude = latitude2 - latitude1;

        double h = Math.pow(Math.sin(differenceLatitude / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(differenceLongitude / 2),2);

        double mul = 2 * Math.asin(Math.sqrt(h));

        double result = 6371;

        return (int) (mul * result) * 1000;
    }
}
