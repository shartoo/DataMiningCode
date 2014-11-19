

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ChangeJavaIcon extends JFrame {

 public void UI(){
  JFrame jf = new JFrame();
  Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
  //jf.setIconImage(new Image("D:\\workspace\\QqClient\\image\\qq.gif"));
  jf.setIconImage(icon);
  jf.setSize(400,200);
  jf.setVisible(true);
  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 public static void main(String[] args) {
  ChangeJavaIcon cj=new ChangeJavaIcon();
  cj.UI();
 }

}
