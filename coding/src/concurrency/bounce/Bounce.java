/**
 * 
 */
package concurrency.bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author bowen
 *	2018-08-27
 */
public class Bounce {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame=new BounceFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class BounceFrame extends JFrame{
	private BallComponent com;
	private static final int STEPS=1;
	private static final int DELAY=1;
	
	public BounceFrame(){
		setTitle("Bounce");
		com=new BallComponent();
		add(com,BorderLayout.CENTER);
		JPanel buttonPanel=new JPanel();
		addButton(buttonPanel,"start",event->addBall());
		addButton(buttonPanel,"close",event->System.exit(0));
		add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}
	
	public void addButton(Container c,String title,ActionListener listener){
		JButton button=new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * 
	 * 在结束1000次弹跳之前，无法终止程序
	 */
	public void addBall(){
		try {
			Ball ball=new Ball();
			com.add(ball);
			for (int i = 0; i <= STEPS; i++) {
				ball.move(com.getBounds());
				com.paint(com.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public void addBall1(){
		Ball ball=new Ball();
		com.add(ball);
		Runnable r=()->{
			try {
				for (int i = 0; i <= STEPS; i++) {
					ball.move(com.getBounds());
					com.repaint();
					Thread.sleep(DELAY);
				}
				
			} catch (InterruptedException e) {
			}
		};
		Runnable r1=new Runnable(){

			@Override
			public void run() {
				
			}
			
		};
		Thread t=new Thread(r);
		t.start();
	}
}