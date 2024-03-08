import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	Image offScreenImg;
	Graphics offScreenPen;
	
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean DN_Pressed = false;
	boolean Attack_Pressed = false;
	boolean Test_Tool = false;
	boolean Game_Over = false;
	boolean is_crouching = false;
	String [] pose = {"RunLeft", "RunRight", "IdleLeft", "IdleRight", "CrouchLeft","CrouchRight", 
			          "CrouchWalkLeft" ,"CrouchWalkRight", "AttackLeft","AttackRight"}; // title of each animation.
	
	TileMap map = new TileMap();
	
	int [] count = {10, 10, 10, 10, 3, 3, 8, 8}; // number of frames in the animations above.
	int [] duration = {5, 5, 10, 10, 5, 5, 10, 10}; // higher the duration, slower the animation.

	
	Sprite player = new Sprite("player", pose, 600, 450, count, duration);
	
	Hitbox player_hitbox = new Hitbox(705,530, 44,80,4);
	Hurtbox Player_hurtboxes = new Hurtbox (1000,1000,50,20);
	
	AI_control enemy = new AI_control(100, 560, 40, 50);
	AI_control enemy_scared = new AI_control(600, 560, 40, 50);

	Hurtbox[] Enemy_hurtboxes =
	{ 
		new Hurtbox(0,500,40,50),
		new Hurtbox(800,500,40,50)
	};
	Hitbox[] Enemy_hitboxes = 
	{
			new Hitbox(0,500,40,50,1),
			new Hitbox(800,500,40,50,1)		
			
	};
	GameOver deathScreen = new GameOver();	
	
	Health_UI Hp = new Health_UI(player_hitbox,Enemy_hurtboxes,100);
	
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
			player.attacking = false;
			
			player_hitbox.track_Player(player);
			player_hitbox.crouch(is_crouching);
			
			if(DN_Pressed == false) is_crouching = false;
			if(DN_Pressed) {
				player.crouch();
				is_crouching = true;
				if(LT_Pressed) 
				{
					
				player.moveLT(2);
			
				}
				if(RT_Pressed) 
				{
				player.moveRT(2);
				
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
			


			enemy.chase(player_hitbox, 3);
			if(Enemy_hitboxes[0].is_alive()) Enemy_hitboxes[0].track(enemy);
			enemy_scared.evade(player_hitbox, 2);
			Enemy_hurtboxes[1].track(enemy_scared);

			
			
			if(enemy_scared.x < 0 || enemy_scared.x > 1000) enemy_scared.x = 500;
			
			
			if(Attack_Pressed == true) {
				
				player.attack();

				Player_hurtboxes.Basic_attack(player);
			}

			
			if(Attack_Pressed == false) Player_hurtboxes = new Hurtbox (1000,1000,50,20);

			
			for(int i =0; i < Enemy_hitboxes.length;i++) 
			{
			if(Enemy_hitboxes[i].overlaps(Player_hurtboxes))
			{
				Enemy_hitboxes[i].Hit();
			}
			if(Enemy_hitboxes[i].is_alive() == false)Enemy_hitboxes[i] = new Hitbox (1000,1000,40,40,0);
			}
			
			
			
			
			
			// this is constantly checking if damge is taken just needs to be written once
			if(Hp.damage_taken() == true)  player_hitbox.Hit();
			
			if(player_hitbox.is_alive() == false) {
			   Game_Over = true;
			}
			repaint();
			
			
			try {
				Thread.sleep(16);
			}catch(Exception x) {};
		}

		
	}
	
	
	public void paint(Graphics pen) {
		//pen.drawImage(animation.nextImage(), 100, 100, 240, 160, null);
		
		map.draw(pen);
		
		if (Game_Over == false) {
		player.draw(pen);
		if(Enemy_hitboxes[0].is_alive())
		enemy.draw(pen);
		enemy_scared.draw(pen);
		Hp.draw(pen);
		}
		
		if(Game_Over==true) {
		deathScreen.draw(pen);
		}
		if(Test_Tool == true) {
		pen.setColor(Color.red);
		for(int i =0;i<Enemy_hurtboxes.length;i++) Enemy_hurtboxes[i].draw(pen);
		pen.setColor(Color.blue);
		for(int i =0;i<Enemy_hitboxes.length;i++) Enemy_hitboxes[i].draw(pen);
		
		pen.setColor(Color.orange);
		Player_hurtboxes.draw(pen);
		
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
		
		
		if (code == e.VK_K)    Attack_Pressed = true;
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

		if (code == e.VK_K)    Attack_Pressed = false;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
