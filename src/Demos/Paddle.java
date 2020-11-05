package Demos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import utilities.GDV5;

public class Paddle{
	int vx;
	int vy;
	int width;
	int height;
	Rectangle u1;
	int counter;
	boolean canSmash;
	
	public Paddle(int x1, int y1, int width, int height) {
		u1 = new Rectangle(x1, y1, width, height);
		this.width = width;
		this.height = height;
		this.vx = 0;
		this.vy = 0;
	}
	
	public double width() {
		return this.width;
	}
	
	public double height() {
		return this.height;
	}
	//Determines if can smash or not
	public boolean getCanSmash() {
		return canSmash;
	}
	
	//Fills in u1 and u2 paddle
	public void draw(Graphics2D win) {
		win.setColor(Color.white);
		if (canSmash) {
			win.setColor(Color.red);
		}
		win.fill(u1);
	}
	//Function updates every frame
	public void update() {
		if (this.u1.getY() >=0 && this.u1.getY() <= 450) {
			u1.translate(vx, vy);
		}
		//if paddle hits top can only move down. 
		if (this.u1.getY() <= 0 && this.vy >= 0) {
			u1.translate(vx, vy);
		}
		//If hits bottom, only moves up.
		if (this.u1.getY() >= 450 && this.vy <= 0) {
			u1.translate(vx, vy);
		}
		counter++;
		if (counter >= 150) {
			canSmash = true;
		}
		else {
			canSmash = false;
		}
	}
	
	//Function that's called when user clicks up arrow or w for player 2
	public void goUp() {
		this.vy = -5;
		counter = 0;
	}
	//Function that's called when user clicks down arrow or s for player 2
	public void goDown() {
		this.vy = 5;
		counter = 0;
	}
	//Function that's called when user releases key
	public void stop() {
		this.vy = 0;
	}
	
	public  double getX() {
		return this.u1.getX();
	}
	public double getY() {
		return this.u1.getY();
	}
	
		
		
		
}
