package Demos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window;

public class Box {
	int vx;
	int vy;
	int width;
	int height;
	Rectangle r1;
	int score1;
	int score2;
	private Color color = new Color(20, 30, 40);
	
	//Sets parameters for ball and sets width, height, vx, and vy
	public Box(int x1, int y1, int width, int height) {
		r1 = new Rectangle(x1, y1, width, height);
		this.width = width;
		this.height = height;
		this.vx = 4;
		this.vy = 4;
	}
	
	//Draws oval and fills it in
	public void draw(Graphics2D win) {
		win.setColor(this.color);
		win.fillOval(r1.x, r1.y, r1.height, r1.width);
	}
	
	// updates 60 times per second
	public void update() { 
		r1.translate(vx, vy);
			
		if (r1.getY() <= 0) {
			this.onCollide("up");
			this.vy = 4;
		} else if (r1.getY() >= 570) {
			this.onCollide("down");
			this.vy = -4;
		}
	}
	
	//Only calls when doesn't hit paddle
	public void resetBall () {
		r1.x = 400;
		r1.y = 300;
		this.vx = 2;
		this.vy = 2;
	}
	
	//Only calls when hits left paddle
	public void hitsLeftPaddle (String dir) {
		this.vx = -vx;
	}
	
	//Only called when hits right paddle
	public void hitsRightPaddle (String dir) {
		this.vx = -vx;
	}
	
	//Called when score reaches certain number (extra feature); increases velocity of ball
	public void increaseVX () {
		this.vx += 2;
	}
	
	//Called when score reaches certain number (extra feature); increase velocity of ball
	public void increaseVY () {
		this.vy += 2;
	}
	
	//On collision, ball changes to random color
	public void onCollide(String dir) {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		this.color  = new Color(r,g,b);	
	}
	
	//Called for collision detection and when need x-coordinate of ball
	public double getX() {
		return r1.getX() + this.vx;
	}
	
	//Called for Collision detection and when need y coordinate of ball
	public double getY() {
		return r1.getY();
	}
	
	//Called for collision detection
	public double height() {
		return this.height;
	}
	
	//Called when need width of ball
	public double getWidth() {
		return this.width;
	}
	
	//Multiplies velocity by 2 when player has power to smash
	public void smash() {
		this.vx *= 2;
		this.vy *= 2;
		
	}
	
}

