import java.awt.Dimension;
import java.awt.*;

import javax.swing.*;

public class TransparentBackground extends JComponent {
private JFrame frame;

private Image background;

public TransparentBackground(JFrame frame) {
  this.frame = frame;
  updateBackground();
}

public void updateBackground() {
  try {
   Robot rbt = new Robot();
   Toolkit tk = Toolkit.getDefaultToolkit();
   Dimension dim = tk.getScreenSize();
   background = rbt.createScreenCapture(new Rectangle(0, 0, (int) dim
     .getWidth(), (int) dim.getHeight()));
  } catch (Exception ex) {
   //p(ex.toString( ));
   ex.printStackTrace();
  }
}

public void paintComponent(Graphics g) {
  Point pos = this.getLocationOnScreen();
  Point offset = new Point(-pos.x, -pos.y);
  g.drawImage(background, offset.x, offset.y, null);
}

public static void main(String[] args) {
  JFrame frame = new JFrame(" Transparent Window ");
  TransparentBackground bg = new TransparentBackground(frame);
  bg.setLayout(new BorderLayout());
  JButton button = new JButton(" This is a button ");
  bg.add(BorderLayout.NORTH, button);
  JLabel label = new JLabel(" This is a label ");
  bg.add(BorderLayout.SOUTH, label);
  frame.getContentPane().add(BorderLayout.CENTER, bg);
  frame.pack();
  frame.setSize(150, 100);
  frame.show();
}
}