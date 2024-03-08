import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	Image offScreenImg;
	Graphics offScreenPen;
	
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean DN_Pressed = false;
	boolean Test_Tool = false;
	
	String [] pose = {"RunLeft", "RunRight", "IdleLeft", "IdleRight", "CrouchLeft","CrouchRight", "CrouchWalkLeft" ,"CrouchWalkRight"}; // title of each animation.
	
	TileMap map = new TileMap();
	
;	int [] count = {10, 10, 10, 10, 3, 3, 8, 8}; // number of frames in the animations above.
	int [] duration = {5, 5, 10, 10, 5, 5, 10, 10}; // higher the duration, slower the animation.
	
	Sprite player = new Sprite("player", pose, 600, 450, count, duration);
	
	Rect player_hitbox = new Rect(705,530, 50,90);
	AI_control enemy = new AI_control(100, 560, 40, 50);
	AI_control enemy_scared = new AI_control(600, 560, 40, 50);

	GameOver deathScreen = new GameOver();
	
	Rect[] hurtboxes =
	{ 
		new Rect(0,500,160,160),
		new Rect(800,500,160,160)
	};
	
	//Health_UI Hp = new Health_UI(hitbox,hurtboxes,100);
	
	//Animation animation = new Animation("runLeft/runLeft", 10, 5);
	
	public void init() {
		
		//Write code to query resolution of screen.
		
		addKeyListener(this);
		offScreenImg = this.createImage(1920, 1080);
		offScreenPen = offScreenImg.getGraphics();
		
		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		
		while(true) { 
			
			player.moving = false;
			player.crouching = false;
			//Hp.damage_taken();
			
			if(DN_Pressed) {
				player.crouch();
				
				if(LT_Pressed) 
				{
					
				player.moveLT(4);
				player_hitbox.moveLT(4);
				}
				if(RT_Pressed) 
				{
				player.moveRT(4);
				player_hitbox.moveRT(4);
				}
				
			}else {
				if(LT_Pressed) 
				{
					
				player.moveLT(4);
				player_hitbox.moveLT(4);
				}
				if(RT_Pressed) 
				{
				player.moveRT(4);
				player_hitbox.moveRT(4);
				}
			}


			//Hp.damage_taken();

			enemy.chase(player_hitbox, 3);
			enemy_scared.evade(player_hitbox, 2);
			
			
			if(enemy_scared.x < 0 || enemy_scared.x > 1000) enemy_scared.x = 500;
			
			//Hp.damage_taken();

			if(LT_Pressed) 
			{
			player.moveLT(5);
			player_hitbox.moveLT(5);
			}
			if(RT_Pressed) 
			{
			player.moveRT(5);
			player_hitbox.moveRT(5);
			}
			//Hp.damage_taken();

			repaint();
			
			
			try {
				Thread.sleep(16);
			}catch(Exception x) {};
		}

		
	}
	
	
	public void paint(Graphics pen) {
		//pen.drawImage(animation.nextImage(), 100, 100, 240, 160, null);
		
		map.draw(pen);
		
		
		player.draw(pen);
		enemy.draw(pen);
		enemy_scared.draw(pen);
		//Hp.draw(pen);
		//deathScreen.draw(pen);
		
		if(Test_Tool == true) {
		pen.setColor(Color.red);
		for(int i =0;i<hurtboxes.length;i++)
		hurtboxes[i].draw(pen);
		pen.setColor(Color.green);
		player_hitbox.draw(pen);
		pen.setColor(Color.black);
		}
	}
	
	public void update(Graphics pen) {
		offScreenPen.clearRect(0, 0, 1920, 1080);
		
		paint(offScreenPen);
		
		pen.drawImage(offScreenImg, 0, 0, null);
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed = true;  
		if (code == e.VK_D)    RT_Pressed = true; 
		if (code == e.VK_S)    DN_Pressed = true;

		if (code == e.VK_T ) {
			if (Test_Tool == true) {
				Test_Tool = false; 
			}else {
				Test_Tool = true; 
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false; 
		if (code == e.VK_S)    DN_Pressed = false;

		//if (code == e.VK_T )  Test_Tool = false; 		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
