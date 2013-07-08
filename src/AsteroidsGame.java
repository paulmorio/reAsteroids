import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class AsteroidsGame extends Applet implements Runnable, KeyListener {

	Thread thread;
	long startTime, endTime, framePeriod;
	Dimension dim; // Stores the size of the back buffer
	Image img; // the back buffer object
	Graphics g; // used to draw on the back buffer.
	Ship ship;
	boolean paused;

	public void init() {
		this.resize(500, 500);

		ship = new Ship(250, 250, 0, 0.35, .98, .1, 12);
		paused = false;

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
		ship.draw(g);
		gfx.drawImage(img, 0, 0, this);
	}

	public void update(Graphics gfx) {
		paint(gfx);
	}

	public void run() {
		for (;;) {
			startTime = System.currentTimeMillis();
			if (!paused) {
				ship.move(dim.width, dim.height);
			}
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!ship.isActive() && !paused) {
				ship.setActive(true);
			} else {
				paused = !paused;
				if (paused)
					ship.setActive(false);
				else
					ship.setActive(true);
			}
		} else if (paused || !ship.isActive()) {
			return;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			ship.setAccelerating(true);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			ship.setTurningLeft(true);

		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			ship.setTurningRight(true);

		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			ship.setAccelerating(false);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			ship.setTurningLeft(false);

		}

		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			ship.setTurningRight(false);

		}
	}

	public void keyTyped(KeyEvent e) {
		// empty method, but still needed to implement the KeyListener
		// interface. And useful for things when inputting text is used.
	}

}
