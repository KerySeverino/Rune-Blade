
public class Hurtbox extends Rect {

	public Hurtbox(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	int direction = 0;
	
	public void track(Rect r) 
	
	{
		x = r.x;
		y = r.y;
	}

	public void Basic_attack(Sprite player) 
		{
		if (player.direction == 1) {
			x = player.x+130;
		    y = player.y+110; 
		}
		if (player.direction == 0) 
		{	
		x = player.x + 55; 
		y = player.y+110 ;
		}	
	
	}

	
}
