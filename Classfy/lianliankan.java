
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class lianliankan extends JFrame implements ActionListener{
 
 JButton about;
 
 public final static int ROW = 8;
 public final static int COLUMN = 8;
 
 public lianliankan(){
  
 JPanel north = new JPanel();
        north.setBackground(Color.white);
        north.setPreferredSize(new Dimension(780, 48));
        about = new JButton("关于");
        north.add(about);
        about.addActionListener(this);
          
        JPanel east = new JPanel();
        east.setBackground(Color.yellow);
        east.setPreferredSize(new Dimension(160, 380));
        JLabel score = new JLabel("307"); 
        Font font = new Font("宋体", Font.BOLD, 48);
        score.setForeground(Color.black);
        score.setFont(font);
        east.add(score);
       
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(ROW,COLUMN,5,5));
        center.setBackground(Color.blue);
        center.setPreferredSize(new Dimension(620, 380));
        JButton[] dots = new JButton[ROW * COLUMN];
        for (int i = 0; i < ROW * COLUMN; i++) {
         dots[i] = new JButton("Kyodai");
         dots[i].setActionCommand("" + i);
         center.add(dots[i]);
         dots[i].addActionListener(this);
        }       
        JPanel beijing = new JPanel(new BorderLayout());
        this.getContentPane().add(north,BorderLayout.NORTH);
        this.getContentPane().add(east,BorderLayout.EAST);
        this.getContentPane().add(center,BorderLayout.CENTER);
        
     }
 
 public static void main (String[] args) { 
     lianliankan frame = new lianliankan();    
     frame.setTitle("连连看");
     frame.setSize(780,500);
     frame.setLocationRelativeTo(null);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
     frame.setResizable(false);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == about) {
             new AboutDialog(this); 
             return ;
            }
        if (e.getSource() instanceof JButton) {
             JButton button = (JButton) e.getSource();
             int offset = Integer.parseInt(button.getActionCommand());
             int row, col;
             row = Math.round(offset / COLUMN);
             col = offset - row * COLUMN;
             JOptionPane.showMessageDialog(this,"ROW="+row+",COL="
              + col, "按钮", JOptionPane.INFORMATION_MESSAGE);
            }

    }
 
}

class AboutDialog extends JDialog {
    /**
  * 
  */
 private static final long serialVersionUID = 6342002066239546826L;
 JLabel about = new JLabel("关于:");

   public AboutDialog(JFrame frame) {
    this.setTitle("About");
    this.setSize(320, 200);
    about.setHorizontalAlignment(SwingConstants.CENTER);
    this.getContentPane().add(about, BorderLayout.CENTER);
    this.show();
    }
}