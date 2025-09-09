import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TeddyMissile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TeddyMissile extends SmoothMover
{
    public TeddyMissile(){

    }

    public void act()
    {
        move(1);
        
        Space sWorld = (Space)getWorld();
        if(isAtEdge()){
            sWorld.removeObject(this);
        }

        Actor e = getOneIntersectingObject(Enemy.class);
        
        if(e != null){
            sWorld.removeObject(e);
            sWorld.enemyHasBeenKilled();
            sWorld.removeObject(this);
        }

    }
}
