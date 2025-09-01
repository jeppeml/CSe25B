import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fries extends Actor
{
    public void act()
    {
        
        //setRotation(450);
        //move(6);
        int speed = Greenfoot.getRandomNumber(20);
        setLocation(getX(),getY()+speed);
        
        if(getY()>=599){
            World w = getWorld();
            w.removeObject(this);
        }
        
    }
}
