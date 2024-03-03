import java.awt.*;

public class Animation {
	private Image[] image;
	private int next;
	
	public Animation(String name, int count) {
		
		image = new Image[count];
		
		for (int i = 0; i < count; i ++) {
			image[i] = Toolkit.getDefaultToolkit().getImage(name + "00" + i + ".png");
			
		}
		
	}
	
	public Image nextImage() {
		next++;
		
		if (next == image.length) {
			next = 0;
		}
		
		return image[next];
	}
}
