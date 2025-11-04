package assignment1;


import org.junit.Test;

import static org.junit.Assert.*;


/**
 *
 * @author pgn
 */
public class UtilityTest
{

    /**
     * Test of doesInputEndWithEnd method, of class Utillity.
     */
    @Test
    public void testDoesInputEndWithEndOne()
    {
        System.out.println("doesInputEndWithEnd");
        String input = "Hello world";
        String end = "world";
        Utillity instance = new Utillity();
        boolean expResult = true;
        boolean result = instance.doesInputEndWithEnd(input, end);
        assertEquals(expResult, result);
    }

    /**
     * Test of doesInputEndWithEnd method, of class Utillity.
     */
    @Test
    public void testDoesInputEndWithEndTwo()
    {
        System.out.println("doesInputEndWithEnd");
        String input = "Hello danish class";
        String end = "world";
        Utillity instance = new Utillity();
        boolean expResult = false;
        boolean result = instance.doesInputEndWithEnd(input, end);
        assertEquals(expResult, result);
    }

    /**
     * Test of allCapitalsAndNoWhiteSpace method, of class Utillity.
     */
    @Test
    public void testAllCapitalsAndNoWhiteSpace()
    {
        System.out.println("allCapitalsAndNoWhiteSpace");
        String aString = " this is a test ";
        Utillity instance = new Utillity();
        String expResult = "THIS IS A TEST";
        String result = instance.allCapitalsAndNoWhiteSpace(aString);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of allCapitalsAndNoWhiteSpace method, of class Utillity.
     */
    @Test
    public void testAllCapitalsAndNoWhiteSpaceTwo()
    {
        System.out.println("allCapitalsAndNoWhiteSpace");
        String aString = " this is also a test ";
        Utillity instance = new Utillity();
        String expResult = "THIS IS ALSO A TEST";
        String result = instance.allCapitalsAndNoWhiteSpace(aString);
        assertEquals(expResult, result);
    }

}
