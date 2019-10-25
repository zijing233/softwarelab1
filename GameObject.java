package cn.zijing.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * ��Ϸ����ĸ���
 * @author �Ӿ�
 *
 *
 */

public class GameObject {
	 Image img;
	 double x,y;
	 int width,height;
	 int speed;
	
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
		
	}

	public GameObject(Image img, double x, double y, int width, int height, int speed) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}

	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
public GameObject() {
	
}	
/**
 * �����������ھ���
 * @return
 */

	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
