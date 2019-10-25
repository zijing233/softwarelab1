package cn.zijing.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

/**
 * ������
 * @author zijing
 *
 */

public class MyGameFrame extends Frame{
	Image planeImg = GameUtil.getImage("Images/plane.png");
	Image bg = GameUtil.getImage("Images/bg.jpg");
	
	Plane plane = new Plane(planeImg,250,250 );
	Shell[] shells = new Shell[25];
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period;//��Ϸʱ��
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);//���ɻ�
		for(int i = 0;i<shells.length;i++) {
		shells[i].draw(g);//���ڵ�
	//�ɻ��ڵ���ײ���
		boolean peng = shells[i].getRect().intersects(plane.getRect());
		if(peng) {
			plane.live = false;
			if(bao == null) {
			bao = new Explode(plane.x,plane.y);
			endTime = new Date();
			period = (int)((endTime.getTime() - startTime.getTime())/1000);
			
			
			}
			g.setColor(Color.WHITE);
			g.drawString("ʱ�䣺"+period+"��", (int)plane.x, (int)plane.y);
			bao.draw(g);
		}
		}
	}	
	
class PaintThread extends Thread{
	@Override
	public void run() {
	while(true) {
		repaint();  //�ػ�
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
//��������ڲ���
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}
	/**
	 * ��ʼ������
	 */
	public void lauchJFame() {
		this.setTitle("�Ӿ���Ŀ");
		this.setVisible(true);
		this.setSize(Constant.GAME_WITH,Constant.GAME_HEIGHT);
		this.setLocation(300,300);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});
		new PaintThread().start();//�����ػ������߳�
		addKeyListener(new KeyMonitor());//�������Ӽ��̼���
		
		//��ʼ��50���ڵ�
		for(int i=0;i<shells.length;i++) {
			shells[i] =new Shell();
		}
		}

	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.lauchJFame();
	}
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WITH,Constant.GAME_HEIGHT);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
}

