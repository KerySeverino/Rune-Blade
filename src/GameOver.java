import java.awt.*;

public class GameOver {
	
	//NOTE: Need to implement ability to click on the buttons

	Image image = Toolkit.getDefaultToolkit().getImage("game_over.png");
	
	public void draw(Graphics g) 
	{
		//Background Outline
		g.drawRect(300,25,750,600);
		g.setColor(Color.RED);
		g.fillRect(300,25,750,600);
		
		//Background Fill
		g.drawRect(304,28,742,594);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(304,28,742,594);
		
		//GAME OVER!
		g.drawImage(image, 425, 60, 500, 200, null);
		
		//Button Outline
		g.drawRect(350, 320, 650, 60);
		g.drawRect(350, 450, 650, 60);
		g.setColor(Color.YELLOW);
		g.fillRect(350, 320, 650, 60);
		g.fillRect(350, 450, 650, 60);
		
		//Button Fill
		g.drawRect(352, 322, 646, 56);
		g.drawRect(352, 452, 646, 56);
		g.setColor(Color.BLUE);
		g.fillRect(352, 322, 646, 56);
		g.fillRect(352, 452, 646, 56);
		
		//Button Labels
		Font font = new Font(null, Font.BOLD, 30);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("TRY AGAIN", 600, 360);
		g.drawString("QUIT", 640, 490);
	
		
	}
}