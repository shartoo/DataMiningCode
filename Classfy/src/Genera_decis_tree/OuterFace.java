package Genera_decis_tree;
import NeuralNetwork.Test_Classfy;
import K_meanCluster.SetSelAttr;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

import com.datamining.bayes.ShowBayesResult;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class OuterFace implements ActionListener,MouseListener,MouseMotionListener{
	public static int i=0;
	//Ϊ响应对文件的操作
	  PublicData pd=new PublicData();
	  private Frame frame=new Frame("文件操作");
	  private FileDialog fd_load=new FileDialog(frame,"打开",FileDialog.LOAD);
	  private FileDialog fd_save=new FileDialog(frame,"保存",FileDialog.SAVE);
	  String file="";
	  DataScanPane dsp=new DataScanPane();
	  FileAndFirstLine ffl=new FileAndFirstLine();  
	  ShowPieChart spc=new ShowPieChart();
	  
    JScrollPane treepane=new JScrollPane();
    JScrollPane scrollpane3=new JScrollPane();
    JScrollPane datascan=new JScrollPane();
	JTree tree;
    //利用DefaultTreeCellRenderer对象设置树中各节点的图标
    JScrollPane scrollpane1=new JScrollPane();
    ImageIcon image1=new ImageIcon("D:\\images\1.jpg");
	ImageIcon image2=new ImageIcon("D:\\images\2.jpg");
	ImageIcon image3=new ImageIcon("D:\\images\3.jpg");
	String[][] data={{"表格数据","文件2","文件3","文件4"},{"数据库数据","文件6","文件7","文件8"},
	           {"文件数据","文件10","文件11"},{"视频文件","文件12","文件13"},{"其他数据","文件14","文件15"}};
	JList list1=new JList();
	JList list2=new JList();
	
	//调用其他包里的类
	Test_Classfy bp=new Test_Classfy();
	Desion_Tree_SetAttr dts=new Desion_Tree_SetAttr();
    ShowBayesResult bayesresult=new ShowBayesResult();
    SetSelAttr cluster=new SetSelAttr(); 
	DefaultListModel lim=new DefaultListModel();   //定义列表模型  
	DefaultListModel lim2=new DefaultListModel();
	public static JFrame f=new JFrame("X-DM");
    public void CreatUI(JTable jt){	
    	  Image icon = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
    	  f.setIconImage(icon);
    	  f.addWindowListener(new WindowAdapter(){
    		   public void windowClosing(WindowEvent e){
    			   System.exit(0);
    		   }
    	    });
    	     		
    	    //负责构造左边的树结点的可视化图
    	    DefaultMutableTreeNode rootNode=creatNodes();
    	    tree=new JTree(rootNode);
    	    tree.setRootVisible(false);
    	    treepane.setViewportView(tree);

    	    JMenuBar menubar=creatJMenu();   
    	    f.add(treepane,"West");
    	    f.add(menubar,BorderLayout.NORTH);
    	    f.add("Center",this.getDataScanPane(jt));
    	    
    	    f.setSize(1300,750);
    	    f.setLocation(10,10);
    	    f.setVisible(true);      	
    }
    public static void main(String[] args){
     OuterFace ot=new OuterFace();
     ot.CreatUI(ot.getTable());
   }
    

    public  DefaultMutableTreeNode creatNodes(){
	  DefaultMutableTreeNode root;
		DefaultTreeModel model1;
	   DefaultTreeCellRenderer tr=new DefaultTreeCellRenderer();//�����������Ⱦ����   

	   root=new DefaultMutableTreeNode("root");//创建树节点渲染器
	   JTree tree=new JTree(root);   //树进行初始化，其数据来源是root对象
	   tr.setClosedIcon(image1);   //设置结点闭合图标
	   tr.setOpenIcon(image2);     //设置结点打开图标
	   tr.setLeafIcon(image3);   //设置叶子节点图标
	   tr.setBorder(null);
	   tr.setVerifyInputWhenFocusTarget(false);
	   tr.setDisplayedMnemonic('3');
	   tr.setLabelFor(tree);
	   tr.setBorderSelectionColor(Color.red);
	   tr.setTextNonSelectionColor(Color.DARK_GRAY);
	   tr.setTextSelectionColor(Color.green);
	   tree.setCellRenderer(tr);     //把渲染器添加到树中
	   //treepane.add(tree);
	   model1=(DefaultTreeModel)tree.getModel();   //创建并设置完树后
	                                               //利用DefaultTreeModel类来操作树，此句来获取数据对象
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	   for(int i=0;i<data.length;i++)
	   {
		   DefaultMutableTreeNode r=new DefaultMutableTreeNode(data[i][0]);
		   for(int j=0;j<data[i].length;j++)
		   {
			   r.add(new DefaultMutableTreeNode(data[i][j]));
			   
		   }
		   root.add(r);
	   }
	   
	   return root;
    }
  
   public  JMenuBar creatJMenu(){ 
	    //MyMonitor mm=new MyMonitor();   		
	    JMenuBar mb=new JMenuBar();
	    JMenu m1=new JMenu("文件");
	    JMenu m2=new JMenu("数据源");
	    JMenu m3=new JMenu("数据处理");
	    JMenu m4=new JMenu("基础统计");
	    JMenu m5=new JMenu("高级统计");
	    JMenu m6=new JMenu("数据挖掘");
	    JMenu m7=new JMenu("数据制图");
	    JMenu m8=new JMenu("帮助");	    

	    JMenu mi11=new JMenu("新建");  //二级菜单和一级菜单一样	    
	    JMenuItem mi12=new JMenuItem("打开");
	    mi12.addActionListener(this);
	    JMenuItem mi13=new JMenuItem("保存");
	    mi13.addActionListener(this);
	    JMenuItem mi14=new JMenuItem("另存为");
	    mi14.addActionListener(this);
	    JMenuItem mi15=new JMenuItem("首选项");
	    mi15.addActionListener(this);
	    JMenuItem mi16=new JMenuItem("关闭");
	    mi16.addActionListener(this);
    
	    JMenuItem mi111=new JMenuItem("数据流");    //只有最后一项需要用JMenuItem
	    JMenuItem mi17=new JMenuItem("退出");
	    mi17.addActionListener(this);
	    
	    JMenuItem m21=new JMenuItem("马克威文件");
	    m21.addActionListener(this);
	    JMenuItem m22=new JMenuItem("数据库");
	    m22.addActionListener(this);
	    JMenuItem m23=new JMenuItem("txt文件");
	    m23.addActionListener(this);
	    JMenuItem m24=new JMenuItem("Excel");
	    m24.addActionListener(this);
	    JMenuItem m25=new JMenuItem("DBF文件");
	    m25.addActionListener(this);
	    JMenuItem m26=new JMenuItem("用户输入");
	    m26.addActionListener(this);
	    JMenuItem m27=new JMenuItem("通用数据源");
	    m27.addActionListener(this);
	    JMenuItem m28=new JMenuItem("数据字典");
	    m28.addActionListener(this);
	    
	    JMenuItem m31=new JMenuItem("多维查询");
	    m31.addActionListener(this);
	    JMenuItem m32=new JMenuItem("记录选择");
	    m32.addActionListener(this);
	    JMenuItem m33=new JMenuItem("变量计算");
	    m33.addActionListener(this);
	    JMenuItem m34=new JMenuItem("记录排序");
	    m34.addActionListener(this);
	    JMenuItem m35=new JMenuItem("缺失值填充");
	    m35.addActionListener(this);
	    JMenuItem m36=new JMenuItem("数据抽取");
	    m36.addActionListener(this);
	    JMenuItem m37=new JMenuItem("重新编码");
	    m37.addActionListener(this);
	    JMenu m3d=new JMenu("变量处理");
	    JMenu m3e=new JMenu("文件合并");
	    JMenuItem m3d1=new JMenuItem("插入变量");
	    m3d1.addActionListener(this);
	    JMenuItem m3d2=new JMenuItem("删除变量");
	    m3d2.addActionListener(this);
	    JMenuItem m3d3=new JMenuItem("变量类型修改");
	    m3d3.addActionListener(this);
	    m3d.add(m3d1);
	    m3d.add(m3d2);
	    m3d.add(m3d3);
	    JMenuItem m3e1=new JMenuItem("变量合并");
	    m3e1.addActionListener(this);
	    JMenuItem m3e2=new JMenuItem("记录合并");
	    m3e2.addActionListener(this);
	    m3e.add(m3e1);
	    m3e.add(m3e2);
	    JMenuItem m38=new JMenuItem("行列转换");
	    m38.addActionListener(this);
	    JMenuItem m39=new JMenuItem("数据合并");
	    m39.addActionListener(this);
	    JMenuItem m3a=new JMenuItem("数据重构");
	    m3a.addActionListener(this);
	    JMenuItem m3b=new JMenuItem("分类汇总");
	    m3b.addActionListener(this);
	    JMenuItem m3c=new JMenuItem("随机数生成");
	    m3c.addActionListener(this);
	    
	    JMenu m41=new JMenu("相关分析");
	    JMenu m42=new JMenu("参数检验");
	    JMenu m43=new JMenu("非参数检验");
	    JMenuItem m411=new JMenuItem("变量相关分析");
	    m411.addActionListener(this);
	    JMenuItem m412=new JMenuItem("偏相关分析");
	    m412.addActionListener(this);
	    JMenuItem m413=new JMenuItem("典型相关分析");
	    m413.addActionListener(this);
	    JMenuItem m421=new JMenuItem("单样本均值检验");
	    m421.addActionListener(this);
	    JMenuItem m422=new JMenuItem("单样本比例检验");
	    m422.addActionListener(this);
	    JMenuItem m423=new JMenuItem("单样本方差检验");
	    m423.addActionListener(this);
	    JMenuItem m424=new JMenuItem("双样本均值检验");
	    m424.addActionListener(this);
	    JMenuItem m425=new JMenuItem("双样本比例检验");
	    m425.addActionListener(this);
	    JMenuItem m426=new JMenuItem("双样本方差检验");
	    m426.addActionListener(this);
	    JMenuItem m427=new JMenuItem("配对双样本均值检验");
	    m427.addActionListener(this);
	    JMenuItem m431=new JMenuItem("卡方检验");
	    m431.addActionListener(this);
	    JMenuItem m432=new JMenuItem("二项分布检验");
	    m432.addActionListener(this);
	    JMenuItem m433=new JMenuItem("游程检验");
	    m433.addActionListener(this);
	    JMenuItem m434=new JMenuItem("单样本K-S检验");
	    m434.addActionListener(this);
	    JMenuItem m435=new JMenuItem("两个独立样本检验");
	    m435.addActionListener(this);
	    JMenuItem m436=new JMenuItem("多个独立样本检验");
	    m436.addActionListener(this);
	    JMenuItem m437=new JMenuItem("两个相关样本检验");
	    m437.addActionListener(this);
	    JMenuItem m438=new JMenuItem("多个相关样本检验");
	    m438.addActionListener(this);
	    
	    m41.add(m411);
	    m41.add(m412);
	    m41.add(m413);
	    m42.add(m421);
	    m42.add(m422);
	    m42.add(m423);
	    m42.add(m424);
	    m42.add(m425);
	    m42.add(m426);
	    m42.add(m427);
	    m43.add(m431);
	    m43.add(m432);
	    m43.add(m433);
	    m43.add(m434);
	    m43.add(m435);
	    m43.add(m436);
	    m43.add(m437);
	    m43.add(m438);
	    
	    JMenu m51=new JMenu("回归分析");
	    JMenuItem m511=new JMenuItem("线性回归");
	    m511.addActionListener(this);
	    JMenuItem m512=new JMenuItem("广义回归");
	    m512.addActionListener(this);
	    JMenuItem m513=new JMenuItem("有序回归");
	    m513.addActionListener(this);
	    JMenuItem m514=new JMenuItem("概率单位回归");
	    m514.addActionListener(this);
	    JMenuItem m515=new JMenuItem("二值逻辑回归");
	    m515.addActionListener(this);
	    JMenuItem m516=new JMenuItem("多值逻辑回归");
	    m516.addActionListener(this);
	    JMenuItem m517=new JMenuItem("曲线回归");
	    m517.addActionListener(this);
	    JMenuItem m518=new JMenuItem("岭回归");
	    m518.addActionListener(this);
	    JMenuItem m519=new JMenuItem("主成分回归");
	    m519.addActionListener(this);
	    
	    m51.add(m511);
	    m51.add(m512);
	    m51.add(m513);
	    m51.add(m514);
	    m51.add(m515);
	    m51.add(m516);
	    m51.add(m517);
	    m51.add(m518);
	    m51.add(m519);
	    
	    JMenu m52=new JMenu("聚类分析");
	    JMenuItem m521=new JMenuItem("分层聚类");
	    m521.addActionListener(this);
	    JMenuItem m522=new JMenuItem("快速聚类");
	    m522.addActionListener(this);
	    m52.add(m521);
	    m52.add(m522);
	    
	    JMenu m53=new JMenu("时间序列");
	    JMenuItem m531=new JMenuItem("移动平衡模型");
	    m531.addActionListener(this);
	    JMenuItem m532=new JMenuItem("ARIMA模型");
	    m532.addActionListener(this);
	    JMenuItem m533=new JMenuItem("季节解构模型");
	    m533.addActionListener(this);
	    JMenuItem m534=new JMenuItem("指数平滑模型");
	    m534.addActionListener(this);
	    JMenuItem m535=new JMenuItem("X11模型");
	    m535.addActionListener(this);
	    JMenuItem m536=new JMenuItem("X-12-ARIMA模型");
	    m536.addActionListener(this);
	    m53.add(m531);
	    m53.add(m532);
	    m53.add(m533);
	    m53.add(m534);
	    m53.add(m535);
	    m53.add(m536);
	    
	    JMenu m54=new JMenu("生存分析");
	    JMenuItem m541=new JMenuItem("寿命表");
	    m541.addActionListener(this);
	    JMenuItem m542=new JMenuItem("KM过程");
	    m542.addActionListener(this);
	    JMenuItem m543=new JMenuItem("比例风险模型");
	    m543.addActionListener(this);
	    m54.add(m541);
	    m54.add(m542);
	    m54.add(m543);
	    
	    JMenu m55=new JMenu("协整分析");
	    JMenuItem m551=new JMenuItem("单位根检验");
	    m551.addActionListener(this);
	    JMenuItem m552=new JMenuItem("协整检验");
	    m552.addActionListener(this);
	    JMenuItem m553=new JMenuItem("误差修正模型");
	    m553.addActionListener(this);
	    m55.add(m551);
	    m55.add(m552);
	    m55.add(m553);
	    
	    
	    JMenu m56=new JMenu("指标分析");
	    JMenuItem m561=new JMenuItem("时差相关分析");
	    m561.addActionListener(this);
	    JMenuItem m562=new JMenuItem("K-L信息量");
	    m562.addActionListener(this);
	    m56.add(m561);
	    m56.add(m562);

	    JMenu m61=new JMenu("神经网络");
	    JMenuItem m611=new JMenuItem("HopField神经网络");
	    m611.addActionListener(this);
	    JMenuItem m612=new JMenuItem("Kohonon神经网络");
	    m612.addActionListener(this);
	    JMenuItem m613=new JMenuItem("ART1神经网络");
	    m613.addActionListener(this);
	    JMenuItem m614=new JMenuItem("BP神经网络");
	    m614.addActionListener(this);
	    JMenuItem m615=new JMenuItem("RBF神经网络");
	    m615.addActionListener(this);
	    JMenuItem m616=new JMenuItem();
	    m616.addActionListener(this);
	    JMenuItem m617=new JMenuItem();
	    m617.addActionListener(this);
	    m61.add(m611);
	    m61.add(m612);
	    m61.add(m613);
	    m61.add(m614);
	    m61.add(m615);
	    
	    JMenu m62=new JMenu("关联规则");
	    JMenuItem m621=new JMenuItem("单纬关联规则");
	    m621.addActionListener(this);
	    JMenuItem m622=new JMenuItem("多维关联规则");
	    m622.addActionListener(this);
	    m62.add(m621);
	    m62.add(m622);

	    mi11.add(mi111);
	    m1.add(mi11);
	    m1.add(mi12);
	    m1.add(mi13);
	    m1.add(mi14);
	    m1.add(mi15);
	    m1.add(mi16);
	    m1.add(mi17);
	    
	    m2.add(m21);
	    m2.add(m22);
	    m2.add(m23);
	    m2.add(m24);
	    m2.add(m25);
	    m2.add(m26);
	    m2.add(m27);
	    m2.add(m27);
	    m2.add(m28);
	    
	    m3.add(m31);
	    m3.add(m32);
	    m3.add(m33);
	    m3.add(m34);
	    m3.add(m35);
	    m3.add(m36);
	    m3.add(m37);
	    m3.add(m38);
	    m3.add(m39);
	    m3.add(m3a);
	    m3.add(m3b);
	    m3.add(m3c);
	    m3.add(m3d);
	    m3.add(m3e);
	    
	    
	    JMenuItem m401=new JMenuItem("均值分析");
	    m401.addActionListener(this);
	    JMenuItem m402=new JMenuItem("频率分析");
	    m402.addActionListener(this);
	    JMenuItem m403=new JMenuItem("描述统计");
	    m403.addActionListener(this);
	    JMenuItem m404=new JMenuItem("交叉表");
	    m404.addActionListener(this);
	    
	    m4.add(m401);
	    m4.add(m402);
	    m4.add(m403);
	    m4.add(m404);
	    m4.add(m41);
	    m4.add(m42);
	    m4.add(m43);
	   
	    m5.add(m51);
	    m5.add(m52);
	    m5.add(m53);
	    m5.add(m54);
	    m5.add(m55);
	    m5.add(m56);
	    JMenuItem m571=new JMenuItem("判别分析");
	    m571.addActionListener(this);
	    JMenuItem m572=new JMenuItem("主成分分析");
	    m572.addActionListener(this);
	    JMenuItem m573=new JMenuItem("因子分析");
	    m573.addActionListener(this);
	    JMenuItem m574=new JMenuItem("方差分析");
	    m574.addActionListener(this);
	    JMenuItem m575=new JMenuItem("向量自回归逻辑");
	    m575.addActionListener(this);
	    JMenuItem m576=new JMenuItem("Granger因果检验");
	    m576.addActionListener(this);
	    JMenuItem m577=new JMenuItem("联立方程");
	    m577.addActionListener(this);
	    JMenuItem m578=new JMenuItem("面板数据模型");
	    m578.addActionListener(this);
	    
	    m5.add(m571);
	    m5.add(m572);
	    m5.add(m573);
	    m5.add(m574);
	    m5.add(m575);
	    m5.add(m576);
	    m5.add(m577);
	    m5.add(m578);
	    
	    m6.add(m61);
	    m6.add(m62);
	    JMenuItem m631=new JMenuItem("决策树");
	    m631.addActionListener(this);
	    JMenuItem m632=new JMenuItem("支持向量机");
	    m632.addActionListener(this);
	    JMenuItem m633=new JMenuItem("模糊聚类");
	    m633.addActionListener(this);
	    JMenuItem m634=new JMenuItem("粗糙集");
	    m634.addActionListener(this);
	    JMenuItem m635=new JMenuItem("孤立点分析");
	    m635.addActionListener(this);
	    JMenuItem m636=new JMenuItem("贝叶斯网络");
	    m636.addActionListener(this);
	    
	    m6.add(m631);
	    m6.add(m632);
	    m6.add(m633);
	    m6.add(m634);
	    m6.add(m635);
	    m6.add(m636);   
	    
	    JMenuItem m71=new JMenuItem("直线图");
	    m71.addActionListener(this);
	    JMenuItem m72=new JMenuItem("条状图");
	    m72.addActionListener(this);
	    JMenuItem m73=new JMenuItem("圆饼图");
	    m73.addActionListener(this);
	    JMenuItem m74=new JMenuItem("面积图");
	    m74.addActionListener(this);
	    JMenuItem m75=new JMenuItem("盒状图");
	    m75.addActionListener(this);
	    JMenuItem m76=new JMenuItem("直方图");
	    m76.addActionListener(this);
	    JMenuItem m77=new JMenuItem("排列图");
	    m77.addActionListener(this);
	    JMenuItem m78=new JMenuItem("误差图");
	    m78.addActionListener(this);
	    JMenuItem m79=new JMenuItem("序列图");
	    m79.addActionListener(this);
	    JMenuItem m7a=new JMenuItem("散点图");
	    m7a.addActionListener(this);
	    JMenuItem m7b=new JMenuItem("自相关图");
	    m7b.addActionListener(this);
	    JMenuItem m7c=new JMenuItem("互相关图");
	    m7c.addActionListener(this);
	    JMenuItem m7d=new JMenuItem("控制图");
	    m7d.addActionListener(this);
	    JMenuItem m7e=new JMenuItem("ROC曲线");
	    m7e.addActionListener(this);
	    JMenuItem m7f=new JMenuItem("高低图");
	    m7f.addActionListener(this);
	    JMenuItem m7g=new JMenuItem("P-P图");
	    m7g.addActionListener(this);
	    JMenuItem m7h=new JMenuItem("Q-Q图");
	    m7h.addActionListener(this);
	    JMenuItem m7i=new JMenuItem("经验图");
	    m7i.addActionListener(this);
	    
        m7.add(m71);
        m7.add(m72);
        m7.add(m73);
        m7.add(m74);
        m7.add(m75);
        m7.add(m76);
        m7.add(m77);
        m7.add(m78);
        m7.add(m79);
        m7.add(m7a);
        m7.add(m7b);
        m7.add(m7c);
        m7.add(m7d);
        m7.add(m7e);
        m7.add(m7f);
        m7.add(m7g);
        m7.add(m7h);
        m7.add(m7i);
       
        JMenuItem m81=new JMenuItem("帮助图");
        m81.addActionListener(this);
        JMenuItem m82=new JMenuItem("关于本软件");
        m82.addActionListener(this);
        JMenuItem m83=new JMenuItem("关于作者");
        m83.addActionListener(this);
	    m8.add(m81);
	    m8.add(m82);
	    m8.add(m83);
	    
	    m1.addActionListener(this);    
	    m2.addActionListener(this);
	    m3.addActionListener(this);
	    m4.addActionListener(this);
	    m5.addActionListener(this);
	    m52.addActionListener(this);
	    m6.addActionListener(this);
	    m61.addActionListener(this);
	    m7.addActionListener(this);
	  
	    mi17.addActionListener(this);
	    m8.addActionListener(this);
	    mb.add(m1);
	    mb.add(m2);
	    mb.add(m3);
	    mb.add(m4);
	    mb.add(m5);
	    mb.add(m6);
	    mb.add(m7);
	    mb.add(m8);
	    return mb;
   }
   public JScrollPane getDataScanPane(JTable jt){
	   datascan.setViewportView(jt);
	   return datascan;
   }
   public JTable getTable(){
	   JTable jt= jt=new JTable(100,20);
	   for(int i=0;i<20;i++){
		   jt.setValueAt("属性"+i,0,i);
	   }
	   return jt;
   }
   public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		System.out.println("点击了---"+s);
		if(s.equals("退出")){
			System.exit(0);
		}
	
		if(s.equals("决策树"))
		{
			dts.creatUI();
			
		}
		
		if(s.equals("圆饼图")){
			spc.Look();
		}
		
		if(s.equals("打开"))
		{
		  fd_load.setVisible(true);
		  String d=fd_load.getDirectory();
		  String fl=fd_load.getFile();
		  if((d!=null)&&(fl!=null)){
				 file=d+fl;				 	
				 if(file.endsWith(".txt"))
				 {
					 ffl.creatUI();             //如果用户选择的文件时txt格式的，需要手动设置列名和分隔符号
					 PublicData.setFile(file);  //将选择的文件写入到存储文件中保存
				     System.out.println(PublicData.getFile());
				 }
				 //如果用户选择的文件时excel文件 可以直接取第一行作为属性名称
				 else if(file.endsWith(".xls"))
				 {            
					 PublicData.setFile(file);
					 ffl.UpdateAttr();      //可以直接根据Excel文件第一行得到数据属性名称列表
					 f.dispose();          //从而 直接让用户继续选择哪些作为选择属性，哪些作为决策属性
					 this.CreatUI(dsp.getTable());
					 f.invalidate();
					 f.repaint();
					 f.setVisible(true);		      
				 }
			   }
		   }
		//�ڿ������ڣ�û�ж�����ݸ�ʽ���޷����?������Ϊ��ݣ����Ի�û��
		 else if(s.equals("保存"))
		 {		   
		   fd_save.setVisible(true);
		   String d=fd_save.getDirectory();
		   String f=fd_save.getFile();
		   if((d!=null)&&(f!=null))
		         {
				   file=d+f;					
				   frame.setTitle("保存文件"+file);
				   }
		  }
		 else if(s.equals("����")){
			   try{
				   PrintWriter pw=new PrintWriter(new FileWriter(file));
				   pw.close();
			   }catch(IOException e1){
				   e1.printStackTrace();
			   }
		 }
		 else if(s.equals("����ѵ�����")){           //����ѵ����ݵ�ͬ�ڸտ�ʼ��ʱ���û�ѡ�����
			System.out.println(s+"==========");
			 fd_load.setVisible(true);
			  String d=fd_load.getDirectory();
			  String fl=fd_load.getFile();
			  if((d!=null)&&(fl!=null)){
					 file=d+fl;				 	
					 if(file.endsWith(".txt"))
					 {
						 ffl.creatUI();             //����������txt�ļ�����Ҫ�ֶ�ָ���������
						 PublicData.setFile(file);    //ͬʱ���ļ������óɵ�ǰ�ģ������ʱ�ļ�����txt�ļ���
						                            //������ffl�Ĵ��ڽ������ύ�ٴα�������Ϊxls�ļ�
					     System.out.println(PublicData.getFile());
					 }
					 else if(file.endsWith(".xls")){            //�����Excel�ļ������µ�ǰ������������
						 PublicData.setFile(file);
						 ffl.UpdateAttr();                          //ΪӦ��Excel�ļ���ΪFileAndFirstLine��ӵĸ��µ�ǰ�ļ�������ķ���
						 f.dispose();
						 this.CreatUI(dsp.getTable());
						 f.invalidate();
						 f.repaint();
						 f.setVisible(true);		      
					 }
				   }			
		}
		 else if(s.equals("贝叶斯网络")){		
			 fd_load.setVisible(true);
			  String d=fd_load.getDirectory();
			  String fl=fd_load.getFile();
			  if((d!=null)&&(fl!=null))
			  {
			    file=d+fl;		 
			    PublicData.setFile(file);
			    ffl.UpdateAttr();                          //ΪӦ��Excel�ļ���ΪFileAndFirstLine��ӵĸ��µ�ǰ�ļ�������ķ���
			    f.dispose();
			    this.CreatUI(dsp.getTable());
			    f.invalidate();
			    f.repaint();
			    
			    f.setVisible(true);
			  }
			 bayesresult.show();
		 }
		 else if(s.equals("快速聚类")){
			 cluster.CreateUI();
		 }
		 else if(s.equals("BP神经网络")){
				bp.test_bp();
		 }
	}
   
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent e) {	 
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}