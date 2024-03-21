
public class Hurtbox extends Rect {

	public Hurtbox(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	int direction = 0;
	
	public void track(Rect r) 
	
	{
		x = r.x - Camera.x;
		y = r.y - Camera.y;
	}

	public void enemy_attack(AI_control enemy,Hitbox player) 
	{
		
		if (enemy.overlaps(player)) 
		{
				x = enemy.x;
			    y = enemy.y;
			    h = enemy.h;
			    w = enemy.w;
		}
			
		else
		{
			x = 1000; 
			y = 1000;
		}


	}
	public void Basic_attack(Sprite player) 
	{
		if (player.direction == 1) 
		{
			x = player.x+155 - Camera.x;
		    y = player.y+80 - Camera.y;
			
		}
		if (player.direction == 0) 
		{	
		x = player.x + 5 - Camera.x; 
		y = player.y + 80 - Camera.y;
		}	
		w=80;
		h=75;

	}

	
}
