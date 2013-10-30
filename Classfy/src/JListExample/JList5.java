package JListExample;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 * DefaultListModel类，可发现此类提供不少好用的方法，例如你可以随意的增加一个项目(
addElement())、或是删除一个项目(removeElement)、甚至你可以很方便地做到查询(getElementAt())与汇出(copyInto())项目的
操作。你可以发现，利用DefaultListModel可以直接动态地更改JList的项目值，而不需要自行产生一个Vecotr对象;相对于JList(
Vector v)这个构造函数，可说更方便且实用许多.
至于利用ListModel或AbstractListModel来构造JList有什么好处？读者只要这么想，ListModel中文就是“列出模式”，那么每
个老师都会有自己开课的学生成绩，老师应该可以看到每个同学的成绩，而深长应该只能看到自己的成绩，所以我们就会有两种不
同的“列出模式”。我们只需要去改写getElementAt()方法，就会有不同的列出模式产生
 * 
 * 
 * @author Administrator
 *
 */
public class JList5
{
    public JList5()
    {
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));
        ListModel mode = new DataModel(1);
        JList list1 = new JList(mode);
        list1.setVisibleRowCount(5);
        list1.setBorder(BorderFactory.createTitledBorder("您玩过哪些软件？"));
        
        mode = new DataModel(2);
        JList list2 = new JList(mode);
        list2.setBorder(BorderFactory.createTitledBorder("您玩过哪些数据库软件？"));
        
        contentPane.add(new JScrollPane(list1));
        contentPane.add(new JScrollPane(list2));
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
        new JList5();
    }
}
class DataModel extends AbstractListModel
{
    String[] s = {"MS SQL","MySQL","IBM DB2","ORACLE","Windows 2000","Linux","UNix","OS2"};
    int flag;
    
    DataModel(int flag)
    {
        this.flag = flag;
    }
    public Object getElementAt(int index)
    {
        String tmp = null;
        
        if (flag == 1)
            tmp = (index+1)+"."+s[index++];
        if (flag == 2)
        {
            if(index < 4)
                tmp = (index+1)+"."+s[index++];     
        }
        
        return tmp;
    }
    
    public int getSize()
    {
        return s.length;
    }
}