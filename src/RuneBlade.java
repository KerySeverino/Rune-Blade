import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	ImageLayer background = new ImageLayer("Castle.png", 0, 0 , 1);
	
	Image offScreenImg;
	Graphics offScreenPen;
	
	boolean ENTER_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean DN_Pressed = false;
	boolean Attack_Pressed = false;
	boolean Test_Tool = false;
	boolean Game_Over = false;
	boolean is_crouching = false;
	boolean gameStarted = false;

	TitleScreen startGame = new TitleScreen();
	
	//PLAYER
	int [] count = {10, 10, 10, 10, 3, 3, 8, 8, 4, 4}; // number of frames in the animations above.
	int [] duration = {5, 5, 10, 10, 5, 5, 10, 10, 7, 7}; // higher the duration, slower the animation.
	
	String [] player_pose = {"LTrun", "RTrun", "LTidle", "RTidle", "LTcrouch","RTcrouch", 
			          "LTcrouchwalk" ,"RTcrouchwalk", "LTattack","RTattack"}; // title of each animation.
	
	
	Hitbox player_hitbox = new Hitbox(705,530, 44,85,4);
	Hurtbox player_hurtboxes = new Hurtbox (1000,1000,50,20);
	Sprite player = new Sprite("player", player_pose, 600, 450, count, duration);
	
	//AI
	String [] blueSlime_pose = {"idle", "LTmove", "RTmove", "LTattack", "RTattack"};
	int [] blueSlime_count = {4, 4, 4, 4, 4};
	int [] blueSlime_duration = {15, 7, 7, 7, 7};
	
	String [] flyingEye_pose = {"Null", "LTmove", "RTmove", "LTattack", "RTattack", "LThurt", "RTHurt", "LTdeath", "RTdeath"};
	int [] flyingEye_count = {0, 8, 8, 8, 8, 4, 4, 4, 4};
	int [] flyingEye_duration = {0, 10, 10, 10, 10, 7, 7, 7 , 7};
	
	AI_control blueSlime = new AI_control("blueSlime", blueSlime_pose,600, 560, 50, 50, blueSlime_count, blueSlime_duration);
	AI_control blueSlime_scared = new AI_control("blueSlime", blueSlime_pose,600, 560, 50, 50, blueSlime_count, blueSlime_duration);
	
	//AI_control flyingEye = new AI_control("flyingEye", flyingEye_pose, 600, 450, 256,256, flyingEye_count, flyingEye_duration);
	
	//TileMap map = new TileMap();


	Hurtbox[] Enemy_hurtboxes =
	{ 
		new Hurtbox(0,500,50,50),
		new Hurtbox(800,500,50,50),
	};
	Hitbox[] Enemy_hitboxes = 
	{
			new Hitbox(0,500,50,50,1),
			new Hitbox(800,500,50,50,1),
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

			//Title Screen
			if(ENTER_Pressed) {
				gameStarted = true;
			}

			//Player Movement
			player.moving = false;
			player.crouching = false;
			player.attacking = false;
			
			player_hitbox.track_Player(player, Attack_Pressed);
			player_hitbox.crouch(is_crouching);
			
			if(DN_Pressed == false) is_crouching = false;
			if(DN_Pressed) {
				player.crouch();
				is_crouching = true;
				if(LT_Pressed) 
				{
				player.moveLT(2);
				Camera.moveLT(2);
				}
				if(RT_Pressed) 
				{
				player.moveRT(2);
				Camera.moveRT(2);
				}
				
			}else {
				if(LT_Pressed) 
				{
				player.moveLT(4);
				player_hitbox.moveLT(4);
				Camera.moveLT(4);
				}
				if(RT_Pressed) 
				{
				player.moveRT(4);
				player_hitbox.moveRT(4);
				Camera.moveRT(4);
				}
			}
			
			// AI CHASE

			blueSlime.chase(player_hitbox, Enemy_hitboxes[0], 2);
			
			//flyingEye.chase(player_hitbox, 3);
			
			
			if(Enemy_hitboxes[0].is_alive()) Enemy_hitboxes[0].track(blueSlime);
			
			blueSlime_scared.evade(player_hitbox, Enemy_hitboxes[1], 3);
			
			Enemy_hurtboxes[1].track(blueSlime_scared);

			
			if(blueSlime_scared.x < 0 || blueSlime_scared.x > 5000) blueSlime_scared.x = 800;
			
			
			if(Attack_Pressed == true) {
				
				player.attack();

				player_hurtboxes.Basic_attack(player);
			}

			
			if(Attack_Pressed == false) player_hurtboxes = new Hurtbox (1000,1000,50,20);

			
			for(int i =0; i < Enemy_hitboxes.length;i++) 
			{
			if(Enemy_hitboxes[i].overlaps(player_hurtboxes))
			{
				Enemy_hitboxes[i].Hit();
			}
			if(Enemy_hitboxes[i].is_alive() == false)Enemy_hitboxes[i] = new Hitbox (1000,1000,40,40,0);
			}
			
			
			
			
			
			// this is constantly checking if damage is taken just needs to be written once
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
		
		//map.draw(pen);
		 background.draw(pen);
		
		if (Game_Over == false) {
		player.draw(pen);
		if(Enemy_hitboxes[0].is_alive())
		blueSlime.draw(pen, player_hitbox); // Draws blueSlime and checks player hitbox overlap
		blueSlime_scared.draw(pen, player_hitbox); 
		//flyingEye.draw(pen, player_hitbox); // Draws flyingEye and checks player hitbox overlap
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
		player_hurtboxes.draw(pen);
		
		pen.setColor(Color.green);
		player_hitbox.draw(pen);
		pen.setColor(Color.black);
		}

		//Draw TitleScreen
		if(!gameStarted) {
			startGame.draw(pen);
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

		if(code == e.VK_ENTER) ENTER_Pressed = true;
		
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

		if(code == e.VK_ENTER) ENTER_Pressed = false;
		
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
