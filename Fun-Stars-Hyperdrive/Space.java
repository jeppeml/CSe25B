import greenfoot.*;  
public class Space extends World
{
    private int starX = 0;
    private int starY = 0;
    PlayerShip ps = new PlayerShip();
    private int acts = 0;
    private int enemyCooldown = 45; // acts to pass before spawn

    public Space()
    {    
        super(800, 600, 1);
        GreenfootImage img = getBackground();
        img.setColor(Color.BLACK);
        img.fill();

        starX = getWidth()/2;
        starY = getHeight()/2;

        addObject(ps,starX,starY);

    }
    
    public void spawnOneEnemy(){
        int chooser = Greenfoot.getRandomNumber(4);
        Enemy e = new Enemy();
        if(chooser == 0){
            addObject(e, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(chooser == 1){
            addObject(e, Greenfoot.getRandomNumber(getWidth()), 600);
        }
        else if(chooser == 2){
            addObject(e, 800, Greenfoot.getRandomNumber(getHeight()));
        }
        else if(chooser == 3){
            addObject(e, 0, Greenfoot.getRandomNumber(getHeight()));
        }
        e.turnTowards(ps.getX(), ps.getY());
    }

    public void act(){
        acts++;
        enemyCooldown--;
        if(enemyCooldown<=0){
            spawnOneEnemy();
            enemyCooldown = 120;
        }
        
        // spawnStars(50);
        // moveCenterWithKey(30);
        if(acts >= 30){
            if(Greenfoot.isKeyDown("SPACE")){
                TeddyMissile teddy = new TeddyMissile();
                addObject(teddy, starX, starY);
                teddy.setRotation(ps.getRotation());
                acts = 0;
            }
        }
        turnShipWithKeys(6);
    }

    public void turnShipWithKeys(int turnSpeed){
        if(Greenfoot.isKeyDown("A")){
            ps.turn(-turnSpeed);
        }

        if(Greenfoot.isKeyDown("D")){
            ps.turn(turnSpeed);
        }
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

        // Check if keypressed xy is out of game area
        if(starX>getWidth())  starX = getWidth();
        if(starY>getHeight()) starY= getHeight();
        if(starY<0) starY=0;
        if(starX<0) starX=0;

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
