package com.test.demo.myapp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.myapp.business.InputStreamHelper;
import com.test.demo.myapp.data.Boat;
import com.test.demo.myapp.data.Car;
import com.test.demo.myapp.data.ElectricEngine;
import com.test.demo.myapp.data.PetrolEngine;
import com.test.demo.myapp.data.Transportation;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        assertTrue(car.engine instanceof PetrolEngine);
        assertFalse(car.engine instanceof ElectricEngine);

        PetrolEngine engine = (PetrolEngine) car.engine;
        assertTrue(engine.volume == 1.6);
    }

    @Test
    public void TestTransportationParsing() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("transports.txt");
        String file = InputStreamHelper.inputStreamToString(in);
        System.out.println(file);
        ObjectMapper objectMapper = new ObjectMapper();
        Transportation[] transportationArray = objectMapper.readValue(file, Transportation[].class);

        List<Car> cars = new ArrayList<>();
        List<Boat> boats = new ArrayList<>();

        for (Transportation transport : transportationArray) {
            switch (transport.type) {
                case "Car":
                    cars.add((Car) transport);
                    break;
                case "Boat":
                    boats.add((Boat) transport);
                    break;
            }
        }

        assertTrue(boats.size() == 1);
        assertTrue(boats.get(0).classOfBoat.equalsIgnoreCase("Sailboat"));


        assertTrue(cars.size() == 1);
        assertTrue(cars.get(0).numberOfWheels == 6);
        assertTrue(cars.get(0).engine instanceof PetrolEngine);
        assertFalse(cars.get(0).engine instanceof ElectricEngine);

        PetrolEngine engine = (PetrolEngine) cars.get(0).engine;
        assertTrue(engine.volume == 1.6);
    }
}
