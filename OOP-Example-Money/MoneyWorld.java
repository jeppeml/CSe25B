import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MoneyWorld extends World
{
    private int acts = 0; // instance variable
    
    // Constructor
    public MoneyWorld()
    {    
        super(800, 600, 1); 
        int x = 5; // local variable (unused)
    }
    
    public void act(){
        acts = acts + 1;
        
        if(acts == 30){
            spawnFries();
            acts = 0;
        }
    }
    
    public void spawnFries(){
        addObject(new Fries(7), Greenfoot.getRandomNumber(800), 0);
    }
}
