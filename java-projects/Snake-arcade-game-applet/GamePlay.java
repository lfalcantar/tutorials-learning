package snake.appet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

@SuppressWarnings("serial")
public class GamePlay extends Applet implements Runnable, ActionListener,
		KeyListener, MouseListener {

	public Snake snake;
	public boolean move;
	public Font font = new Font("sansserif", Font.BOLD, 30);
	public Graphics bufferGraphics;
	public Image offscreen;
	final int WIDTH = 500, HEIGHT = 500;
	public int score;
	public int prevMove;
	public Thread graphics_thread;
	public Thread snake_thread;
	public Image start_image;
	public Boolean start;
	public URL base;
	public int mouseX;
	public int mouseY;
	public URL moveSound;
	public boolean pause;
	public boolean playSound;
	public URL eatSound;
	public boolean repead;
	

	public void init() {
		prevMove = 3;
		setSize(500, 500);
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);

		/*Get a parameter from the HTML*/
//		String param = getParameter("color");
//		if (param.equals("yellow"))
//			setBackground(Color.yellow);
//		else
//			setBackground(new Color(171, 215, 130));
		
		setBackground(new Color(171, 215, 130));
		offscreen = createImage(WIDTH, HEIGHT);
		bufferGraphics = offscreen.getGraphics();

		start_image = getImage(getDocumentBase(), "data/startImage.png");
		eatSound = getClass().getResource("chomp.wav");

	}

	@Override
	public void start() {
		/* initialize variables */
		pause = playSound = repead = false;
		snake = new Snake();
		score = 0;
		start = true;

		/* Create and start threads */
		graphics_thread = new Thread(this);
		snake_thread = new Thread(snake);
		graphics_thread.start();
		snake_thread.start();
		mouseX = mouseY = 0;
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		System.exit(0);
	}

	@Override
	public void paint(Graphics g) {
		/* If the snake reach a food it make a sound */
		if (snake.eatSoundb) {
			play(eatSound);
			snake.eatSoundb = false;
		}
		/* clear,set colors and font */
		bufferGraphics.clearRect(0, 0, WIDTH, HEIGHT);
		bufferGraphics.setColor(Color.BLACK);
		bufferGraphics.setFont(font);
		/*
		 * If the user have not click in the level keep displaying the start
		 * screen
		 */
		if (start) {
			bufferGraphics.drawImage(start_image, 70, 70, this);
			bufferGraphics.setFont(new Font("sansserif", Font.BOLD, 15));
			bufferGraphics.drawString("-> Click on the desired level", 10, 400);
			bufferGraphics.drawString(
					"-> Use the Arrows to play and spaceBar to pause", 10, 430);
			/* Check where did the user click */
			if (mouseX != 0 && snake.selecLevel(new Point(mouseX, mouseY))) {
				start = false;
			}
		}
		/*
		 * if the user hit space the boolean space will be true causing the game
		 * to stop
		 */
		else if (pause) {
			bufferGraphics.drawString("Pause", 200, 250);
		}
		/* If the snake have not hit a wall or itself continue */
		else if (!snake.crash) {
			for (int i = 0; i < snake.index; i++) {
				Point p = snake.coordinates[i];
				if (p != null)
					bufferGraphics.fillRect(p.x, p.y, 10, 10);
			}

			bufferGraphics.fillRect(snake.food.x, snake.food.y, 10, 10);
		}
		/* if the program gets to the else means the game is over */
		else {
			snake.loose();
			bufferGraphics.drawString("Game Over-score : " + (snake.index - 3),
					60, 125);
			bufferGraphics.drawString("Press r to restar ", 80, 200);

			if (repead) {
				start();
			}
		}
		/* Draw line */
		bufferGraphics.drawLine(0, 449, 500, 449);
		bufferGraphics.drawLine(0, 448, 500, 448);
		bufferGraphics.setFont(font);
		/* write stats */
		bufferGraphics.drawString("LEVEL : " + snake.level, 0, 500);
		bufferGraphics.drawString("Score : " + (snake.index - 3), 350, 500);
		/* double buffer the image is ready to be draw */
		g.drawImage(offscreen, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_RIGHT && prevMove != 4) {
			snake.moveR();
			prevMove = 3;
		} else if (code == KeyEvent.VK_LEFT && prevMove != 3) {
			snake.moveL();
			prevMove = 4;
		} else if (code == KeyEvent.VK_UP && prevMove != 0) {
			snake.moveT();
			prevMove = 1;

		} else if (code == KeyEvent.VK_DOWN && prevMove != 1) {
			snake.moveB();
			prevMove = 0;
		} else if (code == KeyEvent.VK_SPACE) {
			if (!pause) {
				pause = true;
				snake.loose();
			} else
				pause = false;

		} else if (code == KeyEvent.VK_R) {
			repead = true;
		}

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
