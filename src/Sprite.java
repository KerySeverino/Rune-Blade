import java.awt.Graphics;

public class Sprite extends Rect {
	
	Animation [] animations;
	boolean moving = false;
	boolean crouching = false;
	boolean attacking = false;
	
	int direction = 1; // 0 = left, 1 = right
	int action = 0;
	
	public Sprite(String name, String [] pose, int x, int y, int [] count, int [] duration) {
		
		//Male Knight Size
		super(x, y, 240, 160);
				
		animations  = new Animation[pose.length];
		
		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(name + "_" + pose[i], count[i], duration[i]);
			
			// Testing/Debugging: Checks the poses that are being loaded
			//System.out.println(name + "_" + pose[i] + "_" + i + ".png");
		}
		
	} 
	


	public void draw(Graphics pen) {
		
		if (crouching) {
			
			if (moving) {
				
				if (direction == 0) pen.drawImage(animations[6].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
				if (direction == 1) pen.drawImage(animations[7].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
				
				
			}else {
				if (direction == 0) {
					pen.drawImage(animations[4].crouch(), x - Camera.x, y - Camera.y, w, h, null);
				}else {
					pen.drawImage(animations[5].crouch(), x - Camera.x, y - Camera.y, w, h, null);
				}
			}
			
		}else if (attacking) {
			if (direction == 0) {
				pen.drawImage(animations[8].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}else {
				pen.drawImage(animations[9].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}
			
			
			
		}else if (!moving) {
			
			if (direction == 0) {
				pen.drawImage(animations[2].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}else {
				pen.drawImage(animations[3].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}
			
			

		}else if (moving){
			pen.drawImage(animations[action].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
		}
		
		
		
	}
	
	@Override
	public void moveLT(int dx){
		
		
		old_x = x;
		
		action = 0;
		
		x -= dx;	
		
		moving = true;
		direction = 0;
	}
	
	@Override
	public void moveRT(int dx){
		old_x = x;
		
		action = 1;
		
		x += dx;	
		
		moving = true;
		direction = 1;
	}
	
	public void crouch() {
		
		crouching = true;
	}
	
	public void attack() {
		attacking = true;
	}
	
}
