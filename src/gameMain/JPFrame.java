package gameMain;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPFrame extends JFrame {
	
	private JLabel backgroundMap;
	private Player player;
	private Attack attack;
	private JLabel timerLabel;
	private JLabel scoreLabel;
	private Timer timer;
	
	private Monster monster1;
	private Monster monster2;
	private Monster monster3;
	private Monster monster4;
	private Monster monster5;
	private Monster monster6;
	private Monster monster7;
	private Monster monster8;
	private Monster monster9;
	private Monster monster10;
	private Monster monster11;
	private Monster monster12;
	
	private int totalTime = 30000;
	private int score = 0;
	
	
	public JPFrame() {
		initObject();
		initSetting();
		initListener();
		initMonster();
		initTimer();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("Image/background2.png"));
		// backgroundMap = new JLabel(new ImageIcon("Image/bg_test1.png"));
		setContentPane(backgroundMap);
		
		this.attack = new Attack();
		this.player = new Player();
		
		this.add(player);
		this.add(attack);
		
		timerLabel = new JLabel("Time : 30");
		timerLabel.setFont(new Font("", Font.BOLD, 30));
		timerLabel.setForeground(Color.BLUE);
        timerLabel.setBounds(600, 10, 150, 20);
        add(timerLabel);

        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setFont(new Font("", Font.BOLD, 30));
        scoreLabel.setForeground(Color.BLUE);
        scoreLabel.setBounds(600, 30, 150, 20);
        add(scoreLabel);
	}
	
	public void increaseScore() {
		score++;
		scoreLabel.setText("Score : " + score);
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void initMonster() {
		this.monster1 = new Monster(200, 140, this);
		this.monster2 = new Monster(480, 140, this);
		this.monster3 = new Monster(728, 140, this);
		
		this.monster4 = new Monster(235, 257, this);
		this.monster5 = new Monster(633, 257, this);
		
		this.monster6 = new Monster(165, 387, this);
		this.monster7 = new Monster(323, 387, this);
		this.monster8 = new Monster(512, 387, this);
		this.monster9 = new Monster(724, 387, this);
		
		this.monster10 = new Monster(236, 525, this);
		this.monster11 = new Monster(424, 525, this);
		this.monster12 = new Monster(824, 525, this);
		
		this.add(monster1);
		this.add(monster2);
		this.add(monster3);
		this.add(monster4);
		this.add(monster5);
		this.add(monster6);
		this.add(monster7);
		this.add(monster8);
		this.add(monster9);
		this.add(monster10);
		this.add(monster11);
		this.add(monster12);
	}
	
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if(!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}			
						break;
					case KeyEvent.VK_RIGHT:
						if(!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case KeyEvent.VK_UP:
						if(!player.isUp()) {
							player.jump();
						}
						break;
					case KeyEvent.VK_SPACE:
						if(!player.isUp()) {
							player.jump();
						}
						break;
					case KeyEvent.VK_A:
						if (!attack.isAttack() && player.leftWay == true) {
							attack.attackL(player.x, player.y, monster1);
							attack.attackL(player.x, player.y, monster2);
							attack.attackL(player.x, player.y, monster3);
							attack.attackL(player.x, player.y, monster4);
							attack.attackL(player.x, player.y, monster5);
							attack.attackL(player.x, player.y, monster6);
							attack.attackL(player.x, player.y, monster7);
							attack.attackL(player.x, player.y, monster8);
							attack.attackL(player.x, player.y, monster9);
							attack.attackL(player.x, player.y, monster10);
							attack.attackL(player.x, player.y, monster11);
							attack.attackL(player.x, player.y, monster12);
						}
						else if(!attack.isAttack() && player.rightWay == true) {
							attack.attackR(player.x, player.y, monster1);
							attack.attackR(player.x, player.y, monster2);
							attack.attackR(player.x, player.y, monster3);
							attack.attackR(player.x, player.y, monster4);
							attack.attackR(player.x, player.y, monster5);
							attack.attackR(player.x, player.y, monster6);
							attack.attackR(player.x, player.y, monster7);
							attack.attackR(player.x, player.y, monster8);
							attack.attackR(player.x, player.y, monster9);
							attack.attackR(player.x, player.y, monster10);
							attack.attackR(player.x, player.y, monster11);
							attack.attackR(player.x, player.y, monster12);
						}
						break;
					case KeyEvent.VK_CONTROL:
						if (!attack.isAttack() && player.leftWay == true) {
							attack.attackL(player.x, player.y, monster1);
							attack.attackL(player.x, player.y, monster2);
							attack.attackL(player.x, player.y, monster3);
							attack.attackL(player.x, player.y, monster4);
							attack.attackL(player.x, player.y, monster5);
							attack.attackL(player.x, player.y, monster6);
							attack.attackL(player.x, player.y, monster7);
							attack.attackL(player.x, player.y, monster8);
							attack.attackL(player.x, player.y, monster9);
							attack.attackL(player.x, player.y, monster10);
							attack.attackL(player.x, player.y, monster11);
							attack.attackL(player.x, player.y, monster12);
						}
						else if(!attack.isAttack() && player.rightWay == true) {
							attack.attackR(player.x, player.y, monster1);
							attack.attackR(player.x, player.y, monster2);
							attack.attackR(player.x, player.y, monster3);
							attack.attackR(player.x, player.y, monster4);
							attack.attackR(player.x, player.y, monster5);
							attack.attackR(player.x, player.y, monster6);
							attack.attackR(player.x, player.y, monster7);
							attack.attackR(player.x, player.y, monster8);
							attack.attackR(player.x, player.y, monster9);
							attack.attackR(player.x, player.y, monster10);
							attack.attackR(player.x, player.y, monster11);
							attack.attackR(player.x, player.y, monster12);
						}
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						player.setLeft(false);		
						break;
					case KeyEvent.VK_RIGHT:
						player.setRight(false);
						break; 
					case KeyEvent.VK_A:
						attack.isAttack = false;
				}
			}
		});
	}
	
	private void initTimer() {
		int sec = 1000;
		
		timer = new Timer(sec, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				totalTime -= 1000;
				int seconds = totalTime / 1000;
				
				if (totalTime >= 0) {
					timerLabel.setText("Time : "+seconds);
				}
				else {
					timer.stop();
					timerLabel.setText("Game Over");
				}
			}
		});
		
		timer.start();
	}
	
	public static void main(String[] args) 
	{
		new JPFrame();
	}

}