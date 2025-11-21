import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Orb extends Actor
{
    private int timeToBirth = 10; 
    private int timeToLive = 120;
    
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
            timeToBirth = Greenfoot.getRandomNumber(1440); 
        }
        
        
        move(5);
        
        if (timeToLive==0){
            getWorld().removeObject(this);
        }
        timeToLive--;
        
    }
}
