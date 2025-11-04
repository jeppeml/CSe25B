import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fries extends Actor
{
    private int speed = 0;

    public Fries(int speed){ // Constructor, when new is called
        this.speed = speed;
    }
    
    public void act()
    {
        setLocation(getX(),getY()+speed);
        if(isOutOfBounds()){
            die();
        }
    }
    
    private void die(){
        World w = getWorld();
        w.removeObject(this);
    }
    
    private boolean isOutOfBounds(){
        return getY()>=599;
    }    
}
