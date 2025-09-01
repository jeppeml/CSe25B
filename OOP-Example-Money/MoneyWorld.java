import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MoneyWorld extends World
{
    int acts = 0;
    // Constructor
    public MoneyWorld()
    {    
        super(800, 600, 1); 
    }
    
    public void act(){
        acts = acts + 1;
        
        if(acts == 30){
            spawnFries();
            acts = 0;
        }
    }
    
    public void spawnFries(){
        addObject(new Fries(), Greenfoot.getRandomNumber(800), 0);
    }
}
