import java.awt.*;

public class TitleScreen {
	
	Image image = Toolkit.getDefaultToolkit().getImage("TSBackground.png");
	Image image2 = Toolkit.getDefaultToolkit().getImage("Rune-Blade_Logo.png");
	Image image3 = Toolkit.getDefaultToolkit().getImage("Enter_icon.png");

	
	public void draw(Graphics g) {
		
		g.drawImage(image, 0, 0, 1400, 700, null);
		g.drawImage(image2, 190, -60, 1000, 500, null);
		g.drawImage(image3, 450, 430, 450, 200, null);
		
	}
}
