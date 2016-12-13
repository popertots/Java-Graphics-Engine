package Graphics;
	
import java.util.Random;
	
public class Screen {
	
	// setup location handling variables
	public int width;
	public  int height;
	public int xOffset;
	public int yOffset;
	public static int i=0;
	public int o=0;
	public int[] pixels;
	public final int MAP_SIZE = 128, MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random rnd = new Random();
	public boolean Debug = false;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = rnd.nextInt(0xffffff);
		}
	}
	
	// clear the screen to black
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x666;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
		if(!fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				if(sprite.pixels[x+y*sprite.getWidth()] != 0xffff00ff){
					pixels[xa+ya*width]=sprite.pixels[x+y*sprite.getWidth()];
				}
			}
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed){
		if(!fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				if(sheet.pixels[x+y*sheet.WIDTH] != 0xffff00ff){
					pixels[xa+ya*width]=sheet.pixels[x+y*sheet.WIDTH];
				}
			}
		}
	}
	
	
	
	public void renderMob(int xp, int yp, Sprite sprite, int flip){
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2){
				ys = 31-y;
			}
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1){
					xs = 31-x;
				}
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) {
					break;
				}
				if(xa < 0){
					xa=0;
				}
				int pColor = sprite.pixels[xs + ys * sprite.width];
				if (pColor != 0xffff00ff){
					pixels[xa + ya * width] = pColor;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}