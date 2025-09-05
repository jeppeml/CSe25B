import greenfoot.*;  
public class Space extends World
{
    private int starX = 0;
    private int starY = 0;
    
    public Space()
    {    
        super(800, 600, 1);
        GreenfootImage img = getBackground();
        img.setColor(Color.BLACK);
        img.fill();
        
        starX = getWidth()/2;
        starY = getHeight()/2;
    }
    
    public void act(){
        spawnStars(50);
        moveCenterWithKey(30);
    }
    
    public void moveCenterWithKey(int moveSpeed){
        if(Greenfoot.isKeyDown("A")){
            starX -= moveSpeed;
        }
        if(Greenfoot.isKeyDown("D")){
            starX += moveSpeed;
        }
        if(Greenfoot.isKeyDown("W")){
            starY -= moveSpeed;
        }
        if(Greenfoot.isKeyDown("S")){
            starY += moveSpeed;
        }
    }
    
    public void spawnStars(int starsPerSpawn){
        for(int i=0;i<starsPerSpawn;i++)
        {
            int direction = Greenfoot.getRandomNumber(360);
            int speed = Greenfoot.getRandomNumber(7)+3;
            Star s = new Star(direction, speed);
            addObject(s,starX,starY);
        }
    }

}
