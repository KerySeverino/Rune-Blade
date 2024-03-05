import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Health_UI {

	
	
	public Rect  hitbox;
	public Rect [] hurtbox;
	public int   health;
	public int   duration;
	public int   delay;
	public Image image;
	
	
	public Health_UI (Rect hitbox, Rect[] hurtboxes,int duration)
	{
		health   = 0;
		this.hitbox   = hitbox;
		this.hurtbox  = hurtboxes;
			 image    = Toolkit.getDefaultToolkit().getImage("Health/Health_" + health + ".png");
		this.duration = duration;
		
		// keeps track of the duration
		delay = 0;
	}
	
	public void damage_taken()
	{
		int p = health;
		for(int i =0; i < hurtbox.length;i++) {
		if(hitbox.overlaps(hurtbox[i])) 
		{
			if(delay == health)
			{
			health++;
	
			if(health > p) { p++;
			
			delay = duration;}
			}
			if (delay>0)delay--;

			
			 	if (health<4) 
			 	{
				 image = Toolkit.getDefaultToolkit().getImage("Health/Health_" + health + ".png");
			 	}
		}
		}
	}
	public void draw (Graphics g) 
	{
		g.drawImage(image, 0, 0,40,40, null );
		
	}
	
}
