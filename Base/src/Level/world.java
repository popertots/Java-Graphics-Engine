package Level;

import java.util.Random;

import Graphics.Screen;
import Graphics.Sprite;

public class world {
	
	public int width, height, num=0;
	private Screen Screen;
	protected Random rnd = new Random();
	public int color = 0x000000;

	
	public world(int width, int height, Screen screen){
		this.width=width;
		this.height=height;
		this.Screen = screen;
	}
	
	public void update(){
		color+=1000;
		
		//lists
	}
	
	public void render(){
		//lists
	}
}
