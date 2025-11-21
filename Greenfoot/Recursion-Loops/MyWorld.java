import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private double speed = 50;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1000x700 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1); 
        //for(int i=0;i<8;i--){  // Wrong forever loop
        int i = 0;
        while(i<8){
            addObject(
                new Orb(), 
                Greenfoot.getRandomNumber(getWidth()),
                Greenfoot.getRandomNumber(getHeight())
                );
            
            i++;
        }
        
        
    }
    
    public void act(){
        speed = speed + .01;
        Greenfoot.setSpeed((int)speed);
    }
}
