
import java.awt.Frame;
import java.awt.Label;
public class TestFrame extends Frame {
 
    Label l1 = new Label("Test11");
    Label l2 = new Label("Test22");
    public TestFrame(){
       //不使用Layout
       this.setLayout(null);
       //设置窗口位置与大小
       this.setBounds(100,100,200,200);
       //添加Label
       this.add(l1);
       //添加Label
       this.add(l2);
       //因为不使用Layout,所以必需设置控件的位置与大小
       l1.setBounds(10, 40, 60, 20);
       //因为不使用Layout,所以必需设置控件的位置与大小
       l2.setBounds(15, 60, 60, 20);
       //显示窗口
       this.setVisible(true);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       // TODO Auto-generated method stub
       TestFrame r = new TestFrame();
    }
}