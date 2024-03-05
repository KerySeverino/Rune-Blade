import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	Image offScreenImg;
	Graphics offScreenPen;
	
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean Test_Tool = false;
	
	String [] pose = {"RunLeft", "RunRight", "IdleLeft", "IdleRight"};
	
	Sprite player = new Sprite("player", pose, 100, 100, 10, 5);
	
	Rect hitbox = new Rect(200,180, 50,90);
	Rect[] hurtboxes =
	{ 
		new Rect(500,100,160,160),
		new Rect(0,100,160,160),
	};
	
	Health_UI Hp = new Health_UI(hitbox,hurtboxes,100);
	
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
			Hp.damage_taken();

			if(LT_Pressed) 
			{
			player.moveLT(5);
			hitbox.moveLT(5);
			}
			if(RT_Pressed) 
			{
			player.moveRT(5);
			hitbox.moveRT(5);
			}
			Hp.damage_taken();
			repaint();
			
			
			try {
				Thread.sleep(16);
			}catch(Exception x) {};
		}

		
	}
	
	
	public void paint(Graphics pen) {
		//pen.drawImage(animation.nextImage(), 100, 100, 240, 160, null);
		
		player.draw(pen);
		Hp.draw(pen);
		
		if(Test_Tool == true) {
		pen.setColor(Color.red);
		for(int i =0;i<hurtboxes.length;i++)
		hurtboxes[i].draw(pen);
		pen.setColor(Color.green);
		hitbox.draw(pen);
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
		if (code == e.VK_D)   RT_Pressed = true; 
		

		if (code == e.VK_T )  Test_Tool = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false;  

		if (code == e.VK_T )  Test_Tool = false; 		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}