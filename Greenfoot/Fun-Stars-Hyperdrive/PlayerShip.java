import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerShip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerShip extends Actor
{
    private String shipName = "";
    
    public void act()
    {
        if(isTouching(Enemy.class)){
            EndWorld end = new EndWorld();
            Greenfoot.setWorld(end);
        }
    }
    
    public void setShipName(String name){
        shipName = name;
    }
}
