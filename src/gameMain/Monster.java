package gameMain;

import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Monster extends JLabel 
{
	private JPFrame jpFrame;
	private Player player;
	
	private boolean standing = true;
	private boolean right = false;
	private boolean left = false;
	private boolean attack = false;
	private boolean survive = true;
	
	private int x;
	private int y;

	private int speed = 1;
	private int hp = 5;
	
	private ImageIcon playerR, playerL;
	
	public Monster(int posX, int posY, JPFrame jpFrame) 
	{
		
		this.jpFrame = jpFrame;
		
		x = posX;
		y = posY;
		initObject();
		initSetting(posX, posY);
		initWandering();
		checkCollision();
	}
	
	private void initObject() 
	{
		playerR = new ImageIcon("Image/botRR.png");
		playerL = new ImageIcon("Image/botLL.png");

	}
	
	private void initSetting(int posX, int posY) 
	{
		setIcon(playerR);
		setSize(50, 50);
		setLocation(posX, posY);
		

	}
	
	private void initWandering()
	{
		new Thread(()-> {
			while(survive)
			{
				left();
				try {
					Thread.sleep(1000 + (int)(Math.random()*100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				right();
				try {
					Thread.sleep(1000 + (int)(Math.random()*100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	public void left() 
	{	
		for(int i = 0; i < 30; i++)
		{
			setIcon(playerL);
			x = x - speed;
			setLocation(x, y);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void right() 
	{
		for(int i = 0; i < 30; i++)
		{
			setIcon(playerR);
			x = x + speed;
			setLocation(x, y);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void decreaseHP() {
		hp--;
		if (hp <= 0) {
			survive = false;
			setVisible(false);
			
			SwingUtilities.invokeLater(() -> jpFrame.increaseScore());
			
			 new Thread(() -> {
	                try {
	                    Thread.sleep(5000); // 5초 대기
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                // 새로운 몬스터 생성
	                hp = 5; // HP 초기화
	                survive = true;
	                setVisible(true); // 몬스터를 다시 보이도록 설정
	            }).start();
		}
	}
	
	public void attack() 
	{
		new Thread(()-> {
			while(attack)
			{
				setIcon(playerR);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void checkCollision() 
	{
		new Thread(() -> {
			while(survive)
			{
				int playerX = player.getX();
		        int playerY = player.getY();
		        int playerWidth = player.getWidth();
		        int playerHeight = player.getHeight();

		        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
		        Rectangle monsterRect = new Rectangle(x, y, getWidth(), getHeight());
		        
		        if(playerRect.intersects(monsterRect))
		        {
		        	player.decreaseHP();
		        	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }
		        
	        	try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start(); 
	}

	
	
	public boolean checkRGB(Color point, int R, int G, int B)
	{
		return (R - 10 <= point.getRed()) && (point.getRed() <= R + 5) && (G - 10 <= point.getGreen()) && (point.getGreen() <= G + 5) && (B - 10 <= point.getBlue()) && (point.getBlue() <= B + 5);
	}
	

	public static void main(String[] args) 
	{
		
	}

}