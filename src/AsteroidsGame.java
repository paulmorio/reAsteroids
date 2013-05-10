import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class AsteroidsGame extends Applet implements Runnable {

	int x, y;
	Thread thread;
	long startTime, endTime, framePeriod;

	public void init() {
		this.resize(500, 500);
		x = 0; // the coordinates of our red circle on
		y = 0; // the screen
		startTime = 0;
		endTime = 0;
		framePeriod = 25; // 25 milliseconds is a good framePeriod (because the
							// humans can't go beyond this
		thread = new Thread(this); // create the thread
		thread.start(); // start the thread
	}

	public void paint(Graphics gfx) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 500, 500);
		gfx.setColor(Color.red);
		gfx.fillOval(0, 0, 50, 50);
	}

	public void update(Graphics gfx) {
		paint(gfx);
	}

	public void run() {
		for (y = 0; y < 450; y += 50) {
			for (x = 0; x < 450; x += 2) {
				// mark start time
				startTime = System.currentTimeMillis();
				repaint();
				try {
					// mark end time
					endTime = System.currentTimeMillis();
					if (framePeriod - (endTime - startTime) > 0)
						Thread.sleep(framePeriod - (endTime - startTime));
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
