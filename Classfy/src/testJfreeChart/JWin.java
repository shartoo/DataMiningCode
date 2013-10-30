package testJfreeChart;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
public class JWin {
 public static void main(String[] args) {
  JWindow w = new JWindow();
  w.setAlwaysOnTop(true);
  w.setLayout(new FlowLayout(FlowLayout.LEFT));
  final JPopupMenu pm = new JPopupMenu();
  pm.add(" a   ");
  pm.add(" b   ");
  pm.add(" c   ");
  JButton b = new JButton("exit");
  w.add(b);
  JLabel l = new JLabel("<html><br/>µ¯³öÊ½²Ëµ¥<br/><br/>");
  l.setOpaque(true);
  l.setBackground(Color.red);
  w.add(l);
  
//  w.getContentPane().addMouseListener(new MouseListener(){
//   public void mouseClicked(MouseEvent e) {}
//   public void mouseEntered(MouseEvent e) {}
//   public void mouseExited(MouseEvent e) {}
//   public void mousePressed(MouseEvent e) {if(e.isPopupTrigger())pop(e);}
//   public void mouseReleased(MouseEvent e) {if(e.isPopupTrigger())pop(e);}
//   void pop(MouseEvent e){
//    pm.show((JComponent)e.getSource(),e.getX(),e.getY());
//   }
//  });
  
  l.addMouseListener(new MouseListener(){
   public void mouseClicked(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {if(e.isPopupTrigger())pop(e);}
   public void mouseReleased(MouseEvent e) {if(e.isPopupTrigger())pop(e);}
   void pop(MouseEvent e){
    pm.show((JComponent)e.getSource(),e.getX(),e.getY());
   }
  });
  
  b.addMouseListener(new MouseListener(){
   public void mouseClicked(MouseEvent e) {System.exit(0);}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
  });
  
  w.setSize(200,100);
  w.setVisible(true);
 }
}