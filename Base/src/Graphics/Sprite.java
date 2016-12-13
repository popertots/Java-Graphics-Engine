package Graphics;



public class Sprite {

	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	protected int width;
	protected int height;
	
	//public static Sprite Background = new Sprite(/*width*/, /*height*/, SpriteSheet.Background);	
	
	
	

    public Sprite() {
    	//idek
    }
    
	public Sprite(int width, int height, int x, int y , SpriteSheet sheet){
		this.width=width;
		this.height=height;
		pixels = new int[width*height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}
	
	
	public Sprite(int width, int height, SpriteSheet sheet){
		this.width=width;
		this.height=height;
		pixels = new int[width*height];
		this.sheet = sheet;
		load();
	}
		
	public Sprite(int size, int color){
		this.width=size;
		this.height=size;
		pixels = new int[size*size];
		setColor(color);
	}
	
	public Sprite(int width, int height, int color){
		this.width=width;
		this.height = height;
		pixels = new int[width*height];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		this.width=width;
		this.height=height;
		this.pixels=pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width*height; i++){
			pixels[i] = color;
		}
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	

	private void load (){
		for(int y = 0; y < height; y++){
			for(int x =0; x < width; x++){
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
