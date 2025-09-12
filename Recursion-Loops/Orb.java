import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orb extends Actor
{
    private int timeToBirth = Greenfoot.getRandomNumber(20); 
    private int timeToLive = 20;
    
    public Orb(){
        turn(Greenfoot.getRandomNumber(360));
        //killComputer();
    }
    
    private void killComputer(){
        anotherMethod();
    }
    
    private void anotherMethod(){
        killComputer();
    }
    public void act()
    {
        
        
        timeToBirth--; // increment varible by one
        if (timeToBirth<1){
            getWorld().addObject(new Orb(), getX(), getY());
            timeToBirth = 15; 
        }
        
        move(5);
        
        if (timeToLive==0){
            getWorld().removeObject(this);
        }
        timeToLive--;
        
    }
}
