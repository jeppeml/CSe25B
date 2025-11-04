package assignment2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author pgn
 */
public class CarDealerShipTest
{

    /**
     * Test of addCarForSale method, of class CarDealerShip.
     */
    @Test
    public void testAddCarForSale()
    {
        System.out.println("addCarForSale");
        Car car = new Car();
        CarDealerShip instance = new CarDealerShip();
        instance.addCarForSale(car);
        Car result = instance.getCarsForSale().get(0);
        assertEquals(car, result);
    }

    /**
     * Test of addCarForSale method, of class CarDealerShip.
     */
    @Test
    public void testAddCarForSaleTwo()
    {
        System.out.println("addCarForSale 2");
        Car car = null;
        CarDealerShip instance = new CarDealerShip();
        instance.addCarForSale(car);
        boolean expectedResult = true;
        boolean actual = instance.getCarsForSale().isEmpty();
        assertEquals(expectedResult, actual);
    }

    /**
     * Test of getTotalCarValues method, of class CarDealerShip.
     */
    @Test
    public void testGetTotalCarValues()
    {
        System.out.println("getTotalCarValues");
        CarDealerShip instance = new CarDealerShip();
        Car one = new Car();
        one.setPrice(500.00);
        Car two = new Car();
        two.setPrice(1500.00);
        instance.addCarForSale(one);
        instance.addCarForSale(two);
        double expResult = 2000.0;
        double result = instance.getTotalCarValues();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCheapestCar method, of class CarDealerShip.
     */
    @Test
    public void testGetCheapestCar()
    {
        System.out.println("getCheapestCar");
        CarDealerShip instance = new CarDealerShip();
        Car one = new Car();
        one.setPrice(500.00);
        Car two = new Car();
        two.setPrice(100.00);
        Car three = new Car();
        three.setPrice(1500.00);
        Car expResult = two;
        instance.addCarForSale(one);
        instance.addCarForSale(two);
        instance.addCarForSale(three);
        Car result = instance.getCheapestCar();
        assertEquals(expResult, result);
    }

    /**
     * Test of sellCar method, of class CarDealerShip.
     */
    @Test
    public void testSellCar()
    {
        System.out.println("sellCar");
        Car car1 = new Car();
        Car car2 = new Car();
        CarDealerShip instance = new CarDealerShip();
        instance.addCarForSale(car1);
        instance.addCarForSale(car2);
        instance.sellCar(car1);
        boolean expResult = false;
        boolean result = instance.getCarsForSale().contains(car1);
        assertEquals(expResult, result);
    }

    /**
     * Test of sortCarsByPrice method, of class CarDealerShip.
     */
    @Test
    public void testSortCarsByPrice()
    {
        System.out.println("sortCarsByPrice");
        CarDealerShip instance = new CarDealerShip();
        Car one = new Car();
        one.setPrice(750.00);
        Car two = new Car();
        two.setPrice(1000.00);
        Car three = new Car();
        three.setPrice(500.00);
        instance.addCarForSale(one);
        instance.addCarForSale(two);
        instance.addCarForSale(three);
        instance.sortCarsByPrice();
        Car result0 = three;
        Car result1 = one;
        Car result2 = two;
        assertEquals(result0, instance.getCarsForSale().get(0));
        assertEquals(result1, instance.getCarsForSale().get(1));
        assertEquals(result2, instance.getCarsForSale().get(2));
    }

    /**
     * Test of sortCarsByMaxSpeed method, of class CarDealerShip.
     */
    @Test
    public void testSortCarsByMaxSpeed()
    {
        System.out.println("sortCarsByMaxSpeed");
        CarDealerShip instance = new CarDealerShip();
        Car one = new Car();
        one.setMaxKilometersPerHour(55);
        Car two = new Car();
        two.setMaxKilometersPerHour(200);
        Car three = new Car();
        three.setMaxKilometersPerHour(100);
        instance.addCarForSale(one);
        instance.addCarForSale(two);
        instance.addCarForSale(three);
        instance.sortCarsByMaxSpeed();
        Car result0 = one;
        Car result1 = three;
        Car result2 = two;
        assertEquals(result0, instance.getCarsForSale().get(0));
        assertEquals(result1, instance.getCarsForSale().get(1));
        assertEquals(result2, instance.getCarsForSale().get(2));
    }

    /**
     * Test of presentRandomCarToCustomer method, of class CarDealerShip.
     */
    @Test
    public void testPresentRandomCarToCustomer()
    {
        System.out.println("presentRandomCarToCustomer");
        CarDealerShip instance = new CarDealerShip();
        Car one = new Car();
        Car two = new Car();
        Car three = new Car();
        instance.addCarForSale(one);
        instance.addCarForSale(two);
        instance.addCarForSale(three);
        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        int rolls = 999999;
        for (int i = 0; i < rolls; i++)
        {
            Car c = instance.presentRandomCarToCustomer();
            if (c == one)
            {
                countOne++;
            } else if (c == two)
            {
                countTwo++;
            } else if (c == three)
            {
                countThree++;
            }
        }
        double distOne = ((double)countOne / (double)rolls);
        double distTwo = ((double)countTwo / (double)rolls);
        double distThree = ((double)countThree / (double)rolls);

        double expected = (1.0 / 3.0);

        assertEquals(expected, distOne, 0.01);
        assertEquals(expected, distTwo, 0.01);
        assertEquals(expected, distThree, 0.01);
    }

}
