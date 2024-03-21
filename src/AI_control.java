import java.awt.Graphics;
import java.util.Random;

public class AI_control extends Rect{
	
	Animation [] animations;
	boolean moving = false;
	
	int direction = 1; // 0 = left, 1 = right
	int distanceFromPlayer;
	Random rand = new Random();

	//Loads the animations
	public AI_control(String name, String [] pose, int x, int y, int w, int h, int [] count, int [] duration) {
		super(x, y, w, h);
		
		animations  = new Animation[pose.length];
		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(name + "_" + pose[i], count[i], duration[i]);
			System.out.println(name + "_" + pose[i] );		
		}

	}
	
	// Draws the AI actions based on player
	public void draw(Graphics pen, Rect r) {
		
		if(direction == 0 && overlaps(r)){
			//LTattack
			pen.drawImage(animations[3].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
		}else if(direction == 1 && overlaps(r)) {
			//RTattack
			pen.drawImage(animations[4].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
		} else if (direction == 0 && !overlaps(r)) {
			//LTmove
			pen.drawImage(animations[1].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
		}else if ((direction == 1 && !overlaps(r))){
			//RTmove
			pen.drawImage(animations[2].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
		}
		
	}
	
	
	// Chases the player
	public void chase(Rect player, Rect ai, int dx)
	{
		moving = true;
		distanceFromPlayer = Math.abs(player.x - ai.x);
		
		// Chases the player if player is within range
		if(distanceFromPlayer <= 350) 
		{
			if(isLeftOf(player)) {  
				direction = 1;
				moveRT(dx); 
			}
			if(isRightOf(player)) { 
				direction = 0;
				moveLT(dx);
			}
			
		} //else {
			// Idle behavior
			//System.out.println("Idle");
		//}
		
//		if(isAbove(player))    moveDN(dx); 
//		if(isBelow(player))    moveUP(dx); 
	}

	// Runs away from the player
	public void evade(Rect player, Rect ai, int dx)
	{
		moving = true;
		distanceFromPlayer = Math.abs(player.x - ai.x);
		
		if(isLeftOf(player)) {
				direction = 0;
				moveLT(dx); 
		}
		if(isRightOf(player)) {   
				direction = 1;
				moveRT(dx);
		} //else {
			// Idle behavior
			//System.out.println("Idle");
		//}
		
//		if(isAbove(r))    moveUP(dx); 
//		if(isBelow(r))    moveDN(dx); 
	}
	
	// Checks which direction is the player
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w < x;
	}
	
	public boolean isAbove(Rect r)
	{
		return y + h < r.y;
	}
	
	public boolean isBelow(Rect r)
	{
		return r.y + r.h < y;
	}
	
	// Checks player hitbox overlap
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	
}
