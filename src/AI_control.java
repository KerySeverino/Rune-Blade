import java.awt.Graphics;

public class AI_control extends Rect{
	
	Animation [] animations;
	boolean moving = false;
	
	int direction = 1; // 0 = left, 1 = right

	public AI_control(String name, String [] pose, int x, int y, int w, int h, int [] count, int [] duration) {
		super(x, y, w, h);
		
		animations  = new Animation[pose.length];
		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(name + "_" + pose[i], count[i], duration[i]);
			
		}

	}
	
	public void draw(Graphics pen) {
		
		if (direction == 0) {
			pen.drawImage(animations[0].nextImage(), x, y, w, h, null);
		}else {
			pen.drawImage(animations[1].nextImage(), x, y, w, h, null);
		}
		
	}
	
	public void chase(Rect r, int dx)
	{
		moving = true;
		
		if(isLeftOf(r)) {  
			direction = 1;
			moveRT(dx); 
		}
		if(isRightOf(r)) { 
			direction = 0;
			moveLT(dx);
		}
		
		if(isAbove(r))    moveDN(dx); 
		if(isBelow(r))    moveUP(dx); 
	}
	
	public void evade(Rect r, int dx)
	{
		moving = true;
		
		if(isLeftOf(r)) {
			direction = 0;
			moveLT(dx); 
		}
		if(isRightOf(r)) {   
			direction = 1;
			moveRT(dx);
		}
		
		if(isAbove(r))    moveUP(dx); 
		if(isBelow(r))    moveDN(dx); 
	}
	
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
	
}
