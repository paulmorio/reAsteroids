import java.awt.*;

public class Ship {
	// define the shape of the ship and its flame
	final double[] origXPts = { 14, -10, -6, .10 }, origYPts = { 0, -8, 0, 8 },
			origFlameXPts = { -6, -23, -6 }, origFlameYPts = { -3, 0, 3 };
	final int radius = 6;

	double x, y, angle, xVelocity, yVelocity, acceleration, velocityDecay,
			rotationalSpeed; // variables used in movement

	boolean turningLeft, turningRight, accelerating, active;
	int[] xPts, yPts, flameXPts, flameYPts; // store the current locations of
											// the points used to draw the ship
											// and its flame
	int shotDelay, shotDelayLeft; //used to determine the rate of firing
	public Ship(double x, double y, double angle, double acceleration, double velocityDecay, double rotationalSpeed, int shotDelay) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.acceleration = acceleration;
		this.velocityDecay = velocityDecay;
		this.rotationalSpeed = rotationalSpeed;
		xVelocity = 0;
		yVelocity = 0;
		turningLeft = false;
		turningRight = false;
		accelerating = false; // not accelerating
		active = false;    // start off paused
		xPts = new int[4]; // allocate space for the arrays
		yPts = new int[4];
		flameXPts = new int [3];
		flameYPts = new int [3];
		this.shotDelay = shotDelay;// # of frames between shots
		shotDelayLeft = 0; // ready to shoot
	}
	
	public void draw(Graphics g) {
		if(accelerating && active) {
			for (int i = 0; i < 3; i++) {
				flameXPts[i] = (int) (origFlameXPts[i]*Math.cos(angle)-origFlameYPts[i]*Math.sin(angle) + x + 0.5);
				flameYPts[i] = (int) (origFlameXPts[i] * Math.sin(angle) + origFlameYPts[i]*Math.cos(angle) + y + 0.5);
			}
			g.setColor(Color.red);
			g.fillPolygon(flameXPts, flameYPts, 3);
		}
		for (int i = 0; i < 4; i++) {
			xPts[i] = (int) (origXPts[i] * Math.cos(angle) - origYPts[i]*Math.sin(angle) + x + 0.5);
			yPts[i] = (int) (origXPts[i] * Math.sin(angle) + origYPts[i] * Math.cos(angle) + y + 0.5);
		}
		if (active) g.setColor(Color.white);
		else g.setColor(Color.darkGray);
		g.fillPolygon(xPts, yPts, 4);
	}
	
	
	
	
	
	
	
	
	
}
