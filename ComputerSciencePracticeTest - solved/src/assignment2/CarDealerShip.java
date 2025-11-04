/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author pgn
 */
public class CarDealerShip
{

    private List<Car> carsForSale = new ArrayList<>();

    
    
    /**
     * Adds another car for sale but only if the value is not null, and only if
     * the car has a price greater than zero.
     *
     * @param car The car to add to list of cars for sale.
     */
    public void addCarForSale(Car car)
    {
        if(car != null && car.getPrice()>0)
            carsForSale.add(car);
    }
    
    /**
     * Gets the total value of all cars for sale.
     * @return the total value of all cars for sale.
     */
    public double getTotalCarValues()
    {
        double priceSum = 0;

        //for (int i = 0; i < carsForSale.size(); i++) {
        //    priceSum += carsForSale.get(i).getPrice();
        //}

        for(Car car : carsForSale ){
            priceSum += car.getPrice();
        }

        return priceSum;
    }
    
    /**
     * Gets the cheapest car from the list ov cars for sale.
     * @return the cheapest car from the list ov cars for sale.
     */
    public Car getCheapestCar()
    {
        Car cheapestCar = carsForSale.get(0);
        for(Car car : carsForSale){
            if (car.getPrice()<cheapestCar.getPrice()){
                cheapestCar = car;
            }
        }
        return cheapestCar;
    }
    
    /**
     * Sells the car in the parameter.
     * @param car The car to be sold.
     */
    public void sellCar(Car car)
    {
        this.carsForSale.remove(car);
    }

    /**
     * Nothing to do here
     * @return 
     */
    public List<Car> getCarsForSale()
    {
        return carsForSale;
    }
        
    /**
    * Sort the list of cars for sale in order from cheapest to most expensive
    */    
    public void sortCarsByPrice()
    {
        carsForSale.sort(
                (car1, car2) ->
                        (int)(car1.getPrice()-car2.getPrice()));

        carsForSale.sort(new Comparator<>() {
            @Override
            public int compare(Car car1, Car car2) {
                return Double.compare(car1.getPrice(),car2.getPrice());
            }
        });

        carsForSale.sort(Comparator.comparing(Car::getPrice));

    }
    
    /**
    * Sort the list of cars for sale in order from slowest to fastest.
    */    
    public void sortCarsByMaxSpeed()
    {
        carsForSale.sort(
                (o1, o2) ->
                        (int)(o1.getMaxKilometersPerHour()-
                                o2.getMaxKilometersPerHour()));
    }
    
    /**
     * Get a random car for sale.
     * @return a randomly selected car for sale.
     */
    public Car presentRandomCarToCustomer()
    {
        Random rand = new Random();
        return carsForSale.get(rand.nextInt(carsForSale.size()));
    }

}