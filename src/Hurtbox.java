
public class Hurtbox extends Rect {

	public Hurtbox(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	int direction = 0;
	
	public void track(Rect r) 
	
	{
		x = r.x;
		y = r.y;
	}


	
}
