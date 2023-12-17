package gameMain;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Attack extends JLabel {
	// 좌측 상단 좌표
	private int x = 0;
	private int y = 0;

	public boolean isAttack;
	
	private ImageIcon attackL, attackR;

	public Attack() {
		initObject();
		initSetting();
	}

	private void initObject() {
		isAttack = false;
		
		attackL = new ImageIcon("Image/crack1.png");
		Image tempL = attackL.getImage();
		Image changetempL = tempL.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		attackL = new ImageIcon(changetempL);
		
		attackR = new ImageIcon("Image/crack2.png");
		Image tempR = attackR.getImage();
		Image changetempR = tempR.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		attackR = new ImageIcon(changetempR);
	}

	private void initSetting() {
		setIcon(attackR);
		setSize(30, 30);
		setLocation(x, y);
	}
	
	public void attackL(int playerX, int playerY, Monster monster) {
		isAttack = true;

		new Thread(() -> {
			while (isAttack) {
				try {
					setVisible(true);
					setIcon(attackL);
					x = playerX - 30;
					y = playerY;
					setLocation(x, y);
					Thread.sleep(300);
					//setVisible(false);
					
					if (checkCollision(monster)) {
                        monster.decreaseHP();
                        isAttack = false;
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			setVisible(false);
		}).start();
	}

	public void attackR(int playerX, int playerY, Monster monster) {
		isAttack = true;

		new Thread(() -> {
			while (isAttack) {
				try {
					setVisible(true);
					setIcon(attackR);
					x = playerX + 50;
					y = playerY;
					setLocation(x, y);
					Thread.sleep(300);
					//setVisible(false);
					
					if (checkCollision(monster)) {
                        monster.decreaseHP();
                        isAttack = false;
                        
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			setVisible(false);
		}).start();
	}
	
	 private boolean checkCollision(Monster monster) {
	        int monsterX = monster.getX();
	        int monsterY = monster.getY();
	        int monsterWidth = monster.getWidth();
	        int monsterHeight = monster.getHeight();

	        Rectangle monsterRect = new Rectangle(monsterX, monsterY, monsterWidth, monsterHeight);
	        Rectangle attackRect = new Rectangle(x, y, getWidth(), getHeight());

	        return monsterRect.intersects(attackRect);
	    }
	
	public static void main(String[] args) {

	}

}