import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	Image offScreenImg;
	Graphics offScreenPen;
	
	Animation animation = new Animation("runLeft/runLeft", 10, 5);
	
	public void init() {
		
		//Write code to query resolution of screen.
		
		offScreenImg = this.createImage(1920, 1080);
		offScreenPen = offScreenImg.getGraphics();
		
		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		
		while(true) { 
			
			repaint();

			try {
				Thread.sleep(16);
			}catch(Exception x) {};
		}

		
	}
	
	
	public void paint(Graphics pen) {
		pen.drawImage(animation.nextImage(), 100, 100, 240, 160, null);
		
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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}