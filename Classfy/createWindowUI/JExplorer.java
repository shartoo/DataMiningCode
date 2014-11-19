package createWindowUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
 
public class JExplorer {
    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        frame.getContentPane().add(new UI(frame));
 
        frame.pack();
        //在全部显示子组件的前提下尽可能的缩小窗口，也就是尽可能的去掉多余的空间。
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
       int left = (screen.width - frame.getWidth()) / 2;
       int top = (screen.height - frame.getHeight()) / 2;
 
        frame.setLocation(left, top);
 
        frame.setVisible(true);
    }
}
 
class UI extends JPanel {
    //implements I_menuHandler{
    static final long serialVersionUID = 0l;
 
    static int LEFT_WIDTH = 200;
 
    static int RIGHT_WIDTH = 300;
 
    static int WINDOW_HEIGHT = 300;
 
    JFrame frame = null;
 
    public UI(JFrame frame) {
        //EmptyBorder eb = new EmptyBorder(1,1,1,1);
        frame.setTitle("文件浏览");
        this.frame = frame;
        setPreferredSize(new Dimension(800, 600));
 
        setBorder(new BevelBorder(BevelBorder.LOWERED));
 
        setLayout(new BorderLayout());
 
        FileList list = new FileList();
        FileTree tree = new FileTree(list);
        tree.setDoubleBuffered(true);
        list.setDoubleBuffered(true);
 
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(
new Dimension(LEFT_WIDTH, WINDOW_HEIGHT));
        JScrollPane listView = new JScrollPane(list);
        listView.setPreferredSize(
new Dimension(RIGHT_WIDTH, WINDOW_HEIGHT));
 
        JSplitPane pane =
new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView,
               listView);
        pane.setDividerLocation(300);
        pane.setDividerSize(4);
        //pane.setDoubleBuffered(true);
 
        add(pane);
    }
}