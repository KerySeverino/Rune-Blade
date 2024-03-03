import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class RuneBlade extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	

	
	Animation animation = new Animation("runLeft/runLeft", 10);
	
	public void init() {
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
		pen.drawImage(animation.nextImage(), 0, 0, null);
		
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