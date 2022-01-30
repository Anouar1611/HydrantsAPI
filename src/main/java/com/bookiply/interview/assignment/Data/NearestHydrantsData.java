package com.bookiply.interview.assignment.Data;

import java.io.Serializable;
import java.util.List;

public class NearestHydrantsData implements Serializable {

    private Integer totalHydrantsDistance;
    private List<HydrantData> nearestHydrants;


    public Integer getTotalHydrantsDistance() {
        return totalHydrantsDistance;
    }

    public void setTotalHydrantsDistance(Integer totalHydrantsDistance) {
        this.totalHydrantsDistance = totalHydrantsDistance;
    }

    public List<HydrantData> getNearestHydrants() {
        return nearestHydrants;
    }

    public void setNearestHydrants(List<HydrantData> nearestHydrants) {
        this.nearestHydrants = nearestHydrants;
    }
}
