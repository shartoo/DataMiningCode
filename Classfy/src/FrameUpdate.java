import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameUpdate implements ActionListener{
	
	JFrame frame;
	JButton one;
	JButton two;
	
	public FrameUpdate(){
	    frame = new JFrame();
       one = new JButton("下一个");
       one.addActionListener(this);
       two = new JButton("上一个");
       two.addActionListener(this);
       frame.add(one);
       frame.pack();
       frame.setVisible(true);
	}
  
	public void actionPerformed(ActionEvent actionEvent){
		String actionName = actionEvent.getActionCommand();
		if(actionName.equals("下一个")){
			frame.remove(one);
		    frame.add(two);
		    frame.invalidate();
		    frame.repaint();
		    frame.setVisible(true);
		}
		else if(actionName.equals("上一个")){
			frame.remove(two);
		    frame.add(one);
		    frame.invalidate();
		    frame.repaint();
		    frame.setVisible(true);
		}
	}
	public static void main(String[] args) {
		new FrameUpdate();
	}

}