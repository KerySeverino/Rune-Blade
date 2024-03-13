
public class Hitbox extends Hurtbox {

	int c = 0;
	private int health;
	public Hitbox(int x, int y, int w, int h, int health) {
		super(x, y, w, h);
		 this.health = health;
		// TODO Auto-generated constructor stub
	}
public void track_Player(Sprite r, boolean attack) 
	
	{
		if(r.direction == 0 && attack ) x = r.x + 85;
		
		if(r.direction == 0 && !attack ) x = r.x + 110;
		

		if(r.direction == 1 && attack) x = r.x + 110;
		if(r.direction == 1 && !attack) x = r.x + 85;
		
		
		y = r.y+95;
		 
	}

public void crouch (boolean crouching)
{
	

	if(crouching) 
	{
		
		if(c==0) 
		{
			h-=15;
			y+=15;
		}
		c=1;
	}
	if(crouching == false) 
	{
		y-=15;
		if(c==1)h+=15;
		c=0;

	}
	
}
	public int getHealth()
	{
	return health;		
	}
	public void Hit () 
	{
		health --;
	}
	public boolean is_alive() {
		
		if (health>0) return true;
		else return false;
	}

public void moveRT(int dx)
{
	old_x = x;
	
	x += dx;
	
	direction = 0;
}
public void moveLT(int dx)
{
	old_x = x;
	
	x -= dx;	
	
	direction = 1 ;
}

}
