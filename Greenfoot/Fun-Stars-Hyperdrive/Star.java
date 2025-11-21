import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Star extends SmoothMover
{
    private int speed = 0;
    private int direction = 0;
    
    // Constructor
    public Star(int direction, int speed){
        this.direction = direction;
        this.speed = speed;
        setBackgroundImage();
        setRotation(direction);
    }
    
    public void setBackgroundImage(){
        int rand = Greenfoot.getRandomNumber(5)+1;
        GreenfootImage img = new GreenfootImage(rand+2,rand);
        int min = 215;
        int max = 255;
        img.setColor(
            new Color(
                Greenfoot.getRandomNumber(max-min)+min,
                Greenfoot.getRandomNumber(max-min)+min,
                Greenfoot.getRandomNumber(max-min)+min,
                Greenfoot.getRandomNumber(128)+128));
        img.fill();
        setImage(img);
    }
    
    public void act()
    {
        move(speed);
        if(isAtEdge())
            getWorld().removeObject(this);
    }
}
