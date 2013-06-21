import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class AsteroidsGame extends Applet implements Runnable, KeyListener {

	int x, y, xVelocity, yVelocity;
	Thread thread;
	long startTime, endTime, framePeriod;
	Dimension dim; // Stores the size of the back buffer
	Image img; // the back buffer object
	Graphics g; // used to draw on the back buffer.

	public void init() {
		this.resize(500, 500);
		x = 225; // the coordinates of our red circle on
		y = 225; // the screen
		xVelocity = 0;
		yVelocity = 0;
		addKeyListener(this);
		startTime = 0;
		endTime = 0;
		framePeriod = 25; // 25 milliseconds is a good framePeriod
		dim = getSize(); // set dim equal to the dimensions of the applet
		img = createImage(dim.width, dim.height); // create the back buffer
		g = img.getGraphics(); // retrieve the back buffer and place into g;
		thread = new Thread(this); // create the thread
		thread.start(); // start the thread
	}

	public void paint(Graphics gfx) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
		gfx.drawImage(img, 0, 0, this);
	}

	public void update(Graphics gfx) {
		paint(gfx);
	}

	public void run() {
		for (;;) {
			startTime = System.currentTimeMillis();
			x += xVelocity;
			y += yVelocity;
			repaint();
			try {
				endTime = System.currentTimeMillis();
				if (framePeriod - (endTime - startTime) > 0)
					Thread.sleep(framePeriod - (endTime - startTime));
			} catch (InterruptedException e) {
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			yVelocity = -1; // subtracting from y moves it upwards
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			yVelocity = 1;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xVelocity = -1; // subtracting from x moves it left
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xVelocity = 1;

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_DOWN)
			yVelocity = 0;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT)
			xVelocity = 0;
	}

	public void keyTyped(KeyEvent e) {
		// empty method, but still needed to implement the KeyListener
		// interface.
	}

}
