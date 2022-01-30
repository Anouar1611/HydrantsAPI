package com.bookiply.interview;

import com.bookiply.interview.assignment.Data.HydrantData;
import com.bookiply.interview.assignment.Data.NearestHydrantsData;
import com.bookiply.interview.assignment.Facade.HydrantsLocatorFacade;
import com.bookiply.interview.assignment.Model.HydrantModel;
import com.bookiply.interview.assignment.Service.HydrantsLocatorService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class HydrantsLocatorFacadeTest extends AbstractHydrantsLocatorFacadeTest{

    @Test
    public void getListOfNearestHydrants() throws Exception {
        String uri = "http://localhost:8080/hydrants/40.64434814/-1.9128952/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        HydrantData h1 = new HydrantData();
        h1.setDistanceFromFire(5922000);
        h1.setUnitId("H501758");

        HydrantData h2 = new HydrantData();
        h2.setDistanceFromFire(5922000);
        h2.setUnitId("H500350");

        List <HydrantData> listHydrants = new ArrayList<HydrantData>(1000);
        listHydrants.add(h1);
        listHydrants.add(h2);

        NearestHydrantsData nearestHydrantsData = new NearestHydrantsData();

        nearestHydrantsData.setNearestHydrants(listHydrants);
        nearestHydrantsData.setTotalHydrantsDistance(11844000);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        NearestHydrantsData listHydrants1 = super.mapFromJson(content, NearestHydrantsData.class);

        List<NearestHydrantsData> nearestHydrantsDataList = new ArrayList<>(Arrays.asList(listHydrants1));

        assertEquals(nearestHydrantsData.getNearestHydrants().size(), nearestHydrantsDataList.get(0).getNearestHydrants().size());
        assertEquals(nearestHydrantsData.getTotalHydrantsDistance(), nearestHydrantsDataList.get(0).getTotalHydrantsDistance());
    }

}
