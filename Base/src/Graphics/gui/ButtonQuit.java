package Graphics.gui;

public class ButtonQuit extends Button {

	public ButtonQuit(int width, int x, int y) {
		super(width, x, y, "Quit");
	}
	
	public void update(){
		if(isActive() && mouse.getB()==1){
			System.exit(0);
		}
	}
}