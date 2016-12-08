package Main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import Entity.User;
import Graphics.Screen;
import Graphics.gui.GuiController;
import Graphics.gui.GuiObject;
import Graphics.gui.TextBox;
import Input.Keyboard;
import Input.Mouse;
import Level.world;

public class Main extends Canvas implements Runnable{

	private static int width = 720;
	private static int height = width/16*9;
	private static int scale = 4;
	private static boolean fLimit = true;
	private boolean running;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	private world world;
	private User User; 
	private Screen screen;
	private GuiController gui;
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);	
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	int pos = 0;
	static int fps=0;
	
	private Main(){
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		screen = new Screen(width,height);
		world = new world(width, height, screen);
		User = new User(10,10,screen, key);
		gui = new GuiController(screen, key, mouse, new GuiObject[]{
				new TextBox(256,20,screen.width/2-256/2,screen.height-50,0x333333),
				new TextBox(256,20,screen.width/2-256/2,screen.height-100,0x333333),
				new TextBox(256,20,screen.width/2-256/2,screen.height-150,0x333333)
				});
	}
	
	public static void main(String[] args){
		Main Main = new Main();
		Main.frame.setResizable(false);
		Main.frame.setTitle("Popertots Engine");
		//Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//Main.frame.setUndecorated(true);
		Main.frame.add(Main);
		Main.frame.pack();
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setLocationRelativeTo(null);
		Main.frame.setVisible(true);
		Main.start();
	}

	private synchronized void start(){
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public void run() {
		long lt = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running){
			long now = System.nanoTime();
			delta += (now - lt) / ns;
			lt = now;
			while (delta >= 1){
				update();
				updates++;
				if(fLimit){
					render();
					frames++;
				}
				delta--;
			}
			if(!fLimit){
				render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Popertots Engine | " + frames + " | " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public synchronized void stop() {
		running = false;
		try{
		thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	private void render() {
		screen.clear();
		world.render();
		
		//temp. put in world class
		User.render();
		gui.render();
		
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void update() {
		key.update();
		world.update();
		//temp. put in world class
		User.update();
		gui.update();
	}
}
