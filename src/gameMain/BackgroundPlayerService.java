package gameMain;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;


public class BackgroundPlayerService implements Runnable
{
	
	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) 
	{
		this.player = player;
		
		try 
		{
			image = ImageIO.read(new File("Image/bg_test2.png"));
			// image = ImageIO.read(new File("image/backgroundMapService.png"));
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run()
	{
		while(true)
		{
			Color Color1 = new Color(image.getRGB(player.getX() + 53, player.getY())); // 우측상단
			Color Color2 = new Color(image.getRGB(player.getX() - 5 , player.getY())); // 좌측상단
			Color Color3 = new Color(image.getRGB(player.getX(), player.getY() + 50)); // 좌측하단
			Color Color4 = new Color(image.getRGB(player.getX() + 50, player.getY() + 50)); // 우측하단
			
			Color ColorHit1 = new Color(image.getRGB(player.getX() + 50, player.getY() + 10)); // 우측상단
			Color ColorHit2 = new Color(image.getRGB(player.getX() + 30, player.getY() + 10)); // 좌측상단
			Color ColorHit3 = new Color(image.getRGB(player.getX() + 30, player.getY() + 30)); // 좌측하단
			Color ColorHit4 = new Color(image.getRGB(player.getX() + 50, player.getY() + 30)); // 우측하단	
			
			//몬스터 좌표 구하기

			
			if(player.checkRGB(Color2, 255, 0, 0))
			{
				player.setLeft(false);;
				player.setLeftWallCrash(true);
			}
			else if(player.checkRGB(Color1, 255,  0, 0))
			{
				player.setRight(false);;
				player.setRightWallCrash(true);
			}
			else	
			{
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			if((player.checkRGB(Color3, 255, 0, 0) || player.checkRGB(Color4, 255, 0, 0) || player.checkRGB(Color3, 0, 0, 255) || player.checkRGB(Color4, 0, 0, 255)))
			{
				player.setPlatform(true);
			}
			else if(!player.isUp() && !player.isDown() && !player.checkRGB(Color3, 255, 0, 0) && !player.checkRGB(Color4, 255, 0, 0) && !player.checkRGB(Color3, 0, 0, 255) && !player.checkRGB(Color4, 0, 0, 255))
			{
				player.setPlatform(false);
				player.down();
			}
			
			if(player.isHitbox())
			{
				while(player.isHitbox()) 
				{
					//닿았을 시 해당 몬스터 hp 깎기
				}
			}
			
			
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}
	

}