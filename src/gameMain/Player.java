

package gameMain;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel {
	// 좌측 상단 좌표
	public int x = 100;
	public int y = 500; // 원래 500
	
	// public static int score = 0;

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean hitbox = false;
	public boolean leftWay = false;
	public boolean rightWay = false;

	private boolean leftWallCrash = false;
	private boolean rightWallCrash = false;
	private boolean platform = false;

	private int speed = 2;
	private int fallSpeed = 2;
	private long sleepTime = 15; // 원래 15

	private ImageIcon playerR, playerL, playerA;
	private Attack attack = new Attack();

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();

	}

	private void initObject() 
	{
		playerR = new ImageIcon("Image/playerRR.gif");
		playerL = new ImageIcon("Image/playerLL.gif");
		playerA = new ImageIcon("Image/실험공격.jpg");

	}
	

	private void initSetting() {
		setIcon(playerR);
		// setSize(50, 50);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

	public void left() {
		left = true;
		leftWay = true;
		rightWay = false;

		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - speed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();

	}

	public void right() {
		right = true;
		leftWay = false;
		rightWay = true;

		new Thread(() -> {
			while (right) {
				setIcon(playerR);
				x = x + speed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void jump() {
		if (!platform) {
			return;
		}

		up = true;

		new Thread(() -> {
			for (int i = 0; i < 18; i++) {
				y = y - 4;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			for (int i = 0; i < 13; i++) {
				y = y - 4;
				setLocation(x, y);
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			for (int i = 0; i < 10; i++) {
				y = y - 3;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			for (int i = 0; i < 10; i++) {
				y = y - 2;
				setLocation(x, y);
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			down();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void down() {
		down = true;

		new Thread(() -> {
			int i = 0;
			int count = -1;

			while (!isPlatform()) {
				y = y + fallSpeed;
				setLocation(x, y);

				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (i % 20 == 0 && fallSpeed <= 5) {
					count++;

					if (count % 3 == 1) {
						sleepTime -= 5;
					} else if (count % 3 == 2) {
						sleepTime += 3;
						fallSpeed++;
					} else if (count % 3 == 0 && count != 0) {
						sleepTime += 2;
						fallSpeed++;
					}

				}

				i++;
			}

			fallSpeed = 2;
			sleepTime = 15;

			down = false;
			up = false;

		}).start();

	}

	public boolean checkRGB(Color point, int R, int G, int B) {
		return (R - 10 <= point.getRed()) && (point.getRed() <= R + 5) && (G - 10 <= point.getGreen())
				&& (point.getGreen() <= G + 5) && (B - 10 <= point.getBlue()) && (point.getBlue() <= B + 5);
	}

	public static void main(String[] args) {

	}

}