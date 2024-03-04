import java.awt.Graphics;

public class Sprite extends Rect {
	
	Animation [] animations;
	boolean moving = false;
	
	int action = 0;
	
	public Sprite(String name, String [] pose, int x, int y, int count, int duration) {
		
		super(x, y, 240, 160);
		
		animations  = new Animation[pose.length];
		
		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(pose[i] + "/" + name + pose[i], count, duration);
			System.out.println(pose[i] + "/" + name + pose[i]);
		}
		
	} 
	
	
	public void draw(Graphics pen) {
		
		if (!moving) {
			pen.drawImage(animations[action + 2].nextImage(), x, y, w, h, null);

		}else {
			pen.drawImage(animations[action].nextImage(), x, y, w, h, null);
		}
		
		
	}
	
	@Override
	public void moveLT(int dx){
		
		old_x = x;
		
		action = 0;
		
		x -= dx;	
		
		moving = true;
	}
	
	@Override
	public void moveRT(int dx){
		old_x = x;
		
		action = 1;
		
		x += dx;	
		
		moving = true;
	}
}
