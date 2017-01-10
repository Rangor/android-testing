package com.test.demo.myapp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.myapp.business.InputStreamHelper;
import com.test.demo.myapp.data.Boat;
import com.test.demo.myapp.data.Car;
import com.test.demo.myapp.data.Transportation;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class JacksonTesting {

    @Test
    public void TestBoatParsing() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("boat.txt");
        String file = InputStreamHelper.inputStreamToString(in);
        System.out.println(file);
        ObjectMapper objectMapper = new ObjectMapper();
        Transportation transportation = objectMapper.readValue(file, Transportation.class);
        assertTrue(transportation instanceof Boat);
        assertFalse(transportation instanceof Car);

        Boat boat = (Boat) transportation;
        assertTrue(boat.classOfBoat.equalsIgnoreCase("Sailboat"));
    }

    @Test
    public void TestCarParsing() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("car.txt");
        String file = InputStreamHelper.inputStreamToString(in);
        System.out.println(file);
        ObjectMapper objectMapper = new ObjectMapper();
        Transportation transportation = objectMapper.readValue(file, Transportation.class);
        assertTrue(transportation instanceof Car);
        assertFalse(transportation instanceof Boat);

        Car car = (Car) transportation;
        assertTrue(car.numberOfWheels == 6);
    }
}
