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
	private int hp = 1;
	
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
			// invokeLater는 전달된 Runnable 객체가 event dispatch thread(EDT)에서 실행되도록 큐에 넣고 곧바로 리턴한다(비동기적)
			// 디스패치 스레드: 스윙에 관련된 작업이 이 디스패치 스레드를 통해 이루어짐
			// 비동기: 요청을 보낸 후 응답과 관계 없이 다음 동작을 실행할 수 있는 방식
			
			// 이는 스윙 관련 이벤트들을 일렬로 나열하여 여러개의 이벤트가 동시에 하나의 스레드에 접근해서 데이터 조작을 하는 것을 막아줌
			// 즉, 여러개의 이벤트가 하나의 속성을 동시에 바꿔주는 것을 막아주며 한 순간에 하나의 이벤트가 원하는 작업을 하게끔 함 

			// invokeLater는 해당 블록이 EDT에서 비동기적으로 실행되도록 예약하는 것이지, 다른 스레드를 차단하는 것이 아님.
			// 즉, increaseScore가 실행되는 동안에도 다른 스레드들은 계속해서 실행
			
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
