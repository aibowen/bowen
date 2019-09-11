/**
 * 
 */
package chapter10.compiler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author bowen
 *	2018-08-27
 */
public abstract class ButtonFrame extends JFrame {
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	protected JPanel panel;
	protected JButton yellowButton;
	protected JButton blueButton;
	protected JButton redButton;
	
	protected abstract void addEventHandlers();
	
	public ButtonFrame(){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		panel=new JPanel();
		add(panel);
		
		yellowButton=new JButton("Yellow");
		blueButton=new JButton("Blue");
		redButton=new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		addEventHandlers();
	}
}
