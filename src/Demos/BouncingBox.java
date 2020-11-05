package Demos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilities.GDV5;
/* Pong Game: AP Computer Science A
 * By: Mansi Saini
 * 
 * Directions: Player 1 uses arrow keys. Player 2
 * uses W, A, S, D keys. Win by two. Five points to 
 * win. If you stay still, you can smash!
 */


//Creates pong ball and paddles
public class BouncingBox extends GDV5 {
	Box b1 = new Box(300, 400, 35, 35);
	Paddle u1 = new Paddle(10, 300, 20, 150);
	Paddle u2 = new Paddle(780, 300, 20, 150);
	
	final int SPLASH_state = 0;
	final int PONG_state = 1;
	final int LEADER_state = 2;
	int state = SPLASH_state;
	
	int scoreLeft = 0;
	int scoreRight = 0;
	int winner;
	BufferedImage introScreen = null;
	BufferedImage player1 = null;
	BufferedImage player2 = null;
	BufferedImage pongBackground = null;


	
	public BouncingBox() {
		//loads title/introduction image into memory
		try {
			introScreen = ImageIO.read(new File("src/Images/introScreen.png"));
		} catch (IOException e) {
		  }
		
		//loads Player 1 Winner image into memory
		try {
			player1 = ImageIO.read(new File("src/Images/player1.png"));
		} catch (IOException e) {
		  }
		
		//loads Player 2 Winner image into memory
		try {
			player2 = ImageIO.read(new File("src/Images/player2.png"));
		} catch (IOException e) {			
		  }
		
		//loads pongBackground image into memory
		try {
			pongBackground = ImageIO.read(new File("src/Images/pongBackground.png"));
		} catch (IOException e) {			
		  }
	}
	
	//window 800 x 600
	public void update() { // updates 60 times per second
			
			switch(this.state) {
				case SPLASH_state:
					break;
				case PONG_state:
					b1.update();
					u1.update();
					u2.update();
					
					//Ball bounces off of paddle if in range of paddle on the left
					if ((b1.getY() + b1.height) <= (u1.getY() + u1.height()) && 
						(b1.getY()) >= (u1.getY()) &&
						(u1.getX() + u1.width()/2) >= (b1.getX() - b1.getWidth()/2)) {
						b1.hitsLeftPaddle("leftPaddle");
						if (u1.getCanSmash()) {
							b1.smash();
						}
					}
					
					//Ball bounces off of paddle if in range of paddle on the right 
					if ((b1.getY() + b1.height) <= (u2.getY() + u2.height()) && 
						(b1.getY()) >= (u2.getY()) &&
						(u2.getX() - u2.width()/2) <= (b1.getX() + b1.getWidth()/2)) {
						b1.hitsRightPaddle("rightPaddle");
						if (u2.getCanSmash()) {
							b1.smash();
						}
					}
						
					//If ball hits right wall, add one point to User 1 
					if (b1.getX() >= u2.getX()) {
						scoreLeft++;
						b1.resetBall();
						b1.increaseVX();
						b1.increaseVY();
					}
					
					//If ball hits the left wall, add one point to User 2
					if (b1.getX() <= u1.getX()) {
						scoreRight++;
						b1.resetBall();
						b1.increaseVX();
						b1.increaseVY();	
					}
					
					//If User 1 wins, then move to end page screen
					if (scoreLeft >= 5 && scoreLeft > scoreRight + 1) {
						winner = scoreLeft;
						state = 2;
					}
					
					//If User 2 wins, then move to end page screen
					if (scoreRight >= 5 && scoreRight > scoreLeft + 1) {
						winner = scoreRight;
						state = 2;
					}
					break;
				case LEADER_state:
					break;
	
			}
	}

	//Adds color to paddles and ball
	public void draw(Graphics2D win) {
		switch(this.state) {
			case SPLASH_state:
				//Directions and introduction to the game + typography styling
				win.drawImage(introScreen, 0, 0, 820, 620, Color.black, null);
				break;
			case PONG_state:
				//draws background image for Pong game
				win.drawImage(pongBackground, -5, 0, 820, 620, Color.black, null);
				//Creates font, coloring, and shape for paddles, ball, and score board
				b1.draw(win);
				u1.draw(win);
				u2.draw(win);
				win.setFont(new Font("Century Gothic", Font.BOLD, 50));
				win.setColor(Color.white);
				win.drawString("Score: " + scoreLeft + " - " + scoreRight, 150, 100);
				break;
			case LEADER_state:
				//If User 1 wins, displays Player 1 wins!
				if (scoreLeft > scoreRight){
					win.drawImage(player1, -5, 0, 820, 620, Color.black, null);
				}
				//If User 2 wins, displays Player 2 Wins
				if (scoreRight > scoreLeft) {
					win.drawImage(player2, -5, 0, 820, 620, Color.black, null);
				}
				//If User 1 score = User 2 score, displays Tie!
				if (scoreRight == scoreLeft) {
					win.setFont(new Font("Impact", Font.PLAIN, 50));
					win.setColor(Color.white);
					win.drawString("Tie!", 50, 100);
				}
				win.setFont(new Font("Impact", Font.PLAIN, 30));
				break;	
		}
	}
	
	//Starts moving Pong ball
	public static void main(String [] args) {
		BouncingBox bounce = new BouncingBox();
		bounce.start();
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		switch(this.state) {
			case SPLASH_state:
				//If enter is pressed on intro screen, enter Pong screen with game
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					state = 1;
				}
				break;
			case PONG_state:
				//Allows user to use up and down arrow keys
				if(evt.getKeyCode() == KeyEvent.VK_UP) {
					u2.goUp();
				}
				if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
					u2.goDown();
				}
				if (evt.getKeyCode() == KeyEvent.VK_W) {
					u1.goUp();
				}
				if (evt.getKeyCode() == KeyEvent.VK_S) {
					u1.goDown();
				}
				break;
			case LEADER_state:
				//Allows user to start another round
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					scoreLeft = 0;
					scoreRight = 0;
					state = 1;
				}
				break;
		}	
	}
	
	@Override
	public void keyReleased(KeyEvent evt) {
		//If user releases keys that are pressed, paddle stops moving
		if(evt.getKeyCode() == KeyEvent.VK_UP) {
			u2.stop();
		}
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			u2.stop();
		}
		if (evt.getKeyCode() == KeyEvent.VK_W) {
			u1.stop();
		}
		if (evt.getKeyCode() == KeyEvent.VK_S) {
			u1.stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
}
