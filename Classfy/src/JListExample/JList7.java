package JListExample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//由于ListSelectionEvent是swing的事件，不是awt的事件，因此我们必须import 
//javax.swing.event.*。
/**
 *   在JList类中有addListSelectionListener()方法,可以检测用户是否对JList的选取有任何的改变。ListSelectionListener
interface中只有定义一个方法，那就是valueChanged(ListSelectionEvent e),我们必须实作这个方法，才能在用户改变选取值时
取得用户最后的选取状态。我们来看一下的例子: 这个例子匀们抓取用户所选取的项目，并将所选的项目显示在JLabel上
 * 
 */
public class JList7 implements ListSelectionListener
{
    JList list = null;
    JLabel label = null;
    String[] s = {"美国","日本","大陆","英国","法国","意大利","澳洲","韩国"};
    
    public JList7()
    {
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        label = new JLabel();
        
        list = new JList(s);
        list.setVisibleRowCount(5);
        list.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));
        list.addListSelectionListener(this);
        
        contentPane.add(label,BorderLayout.NORTH);
        contentPane.add(new JScrollPane(list),BorderLayout.CENTER);
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
        });
    }
    
    public static void main(String args[])
    {
        new JList7();
    }
    
    public void valueChanged(ListSelectionEvent e)
    {
        int tmp = 0;
        String stmp = "您目前选取：";
        int[] index = list.getSelectedIndices();//利用JList类所提供的getSelectedIndices()方法可得到用户所选取的所有 
        for(int i=0; i < index.length ; i++)//index值，这些index值由一个int array返回.
        {
            tmp = index[i];
            stmp = stmp+s[tmp]+" ";
        }
        label.setText(stmp);
    }
}