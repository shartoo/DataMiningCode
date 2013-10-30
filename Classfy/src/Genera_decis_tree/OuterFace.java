package Genera_decis_tree;
import bayes.ShowBayesResult;
import NeuralNetwork.Test_Classfy;
import K_meanCluster.SetSelAttr;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class OuterFace implements ActionListener,MouseListener,MouseMotionListener{
	public static int i=0;
	//为了响应我们在打开文件时的操作
	  PublicData pd=new PublicData();
	  private Frame frame=new Frame("文件操作");
	  private FileDialog fd_load=new FileDialog(frame,"打开文件",FileDialog.LOAD);
	  private FileDialog fd_save=new FileDialog(frame,"保存文件",FileDialog.SAVE);
	  String file="";
	  DataScanPane dsp=new DataScanPane();
	  FileAndFirstLine ffl=new FileAndFirstLine();  
	  ShowPieChart spc=new ShowPieChart();
	  
    JScrollPane treepane=new JScrollPane();
    JScrollPane scrollpane3=new JScrollPane();
    JScrollPane datascan=new JScrollPane();
	JTree tree;
    //利用DefaultTreeCellRenderer对象设置树中各结点的图标
    JScrollPane scrollpane1=new JScrollPane();
    ImageIcon image1=new ImageIcon("D:\\images\1.jpg");
	ImageIcon image2=new ImageIcon("D:\\images\2.jpg");
	ImageIcon image3=new ImageIcon("D:\\images\3.jpg");
	String[][] data={{"表格数据","文件2","文件3","文件4"},{"数据库数据","文件6","文件7","文件8"},
	           {"文件数据","文件10","文11"},{"视频数据","文件12","文件13"},{"其他数据","文件14","文件15"}};
	JList list1=new JList();
	JList list2=new JList();
	
	//调用其他包里的类
	Test_Classfy bp=new Test_Classfy();
	Desion_Tree_SetAttr dts=new Desion_Tree_SetAttr();
    ShowBayesResult bayesresult=new ShowBayesResult();
    SetSelAttr cluster=new SetSelAttr(); 
	DefaultListModel lim=new DefaultListModel();   //定义列表的模型  
	DefaultListModel lim2=new DefaultListModel();
	public static JFrame f=new JFrame("X-DM");
    public void CreatUI(JTable jt){	
    	  Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
    	  f.setIconImage(icon);
    	  f.addWindowListener(new WindowAdapter(){
    		   public void windowClosing(WindowEvent e){
    			   System.exit(0);
    		   }
    	    });
    	     		
    	    //负责构造左边的树结点类的可视化图
    	    DefaultMutableTreeNode rootNode=creatNodes();
    	    tree=new JTree(rootNode);
    	    tree.setRootVisible(false);
    	    treepane.setViewportView(tree);

    	    MenuBar menubar=creatMenu();   
    	    f.add(treepane,"West");
    	    f.setMenuBar(menubar);    //将菜单栏放入面板中
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
	   DefaultTreeCellRenderer tr=new DefaultTreeCellRenderer();//创建树结点渲染器，   

	   root=new DefaultMutableTreeNode("root");//根结点初始化
	   JTree tree=new JTree(root);   //树进行初始化，其数据来源是root对象
	   tr.setClosedIcon(image1);   //设置结点闭合图标
	   tr.setOpenIcon(image2);     //设置结点打开图标
	   tr.setLeafIcon(image3);   //设置叶子结点图标
	   tr.setBorder(null);
	   tr.setVerifyInputWhenFocusTarget(false);
	   tr.setDisplayedMnemonic('3');
	   tr.setLabelFor(tree);
	   tr.setBorderSelectionColor(Color.red);
	   tr.setTextNonSelectionColor(Color.DARK_GRAY);
	   tr.setTextSelectionColor(Color.green);
	   tree.setCellRenderer(tr);     //把渲染器添加到树中
	   //treepane.add(tree);
	   model1=(DefaultTreeModel)tree.getModel();   //创建并设置完树后，
	                                               //利用DefaultTreeModel来操作树，此句来获得数据对象
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
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
  
   public  MenuBar creatMenu(){ 
	  // MyMonitor mm=new MyMonitor();   
		
	    MenuBar mb=new MenuBar();
	    Menu m1=new Menu("文件");
	    Menu m2=new Menu("数据源");
	    Menu m3=new Menu("数据处理");
	    Menu m4=new Menu("基础统计");
	    Menu m5=new Menu("高级统计");
	    Menu m6=new Menu("数据挖掘");
	    Menu m7=new Menu("数据制图");
	    Menu m8=new Menu("帮助");	    
	    
	    
	   // MenuItem[] mi1=new MenuItem[5];
	    Menu mi11=new Menu("新建");  //二级菜单和一级菜单一样
	    
	    MenuItem mi12=new MenuItem("打开");
	    MenuItem mi13=new MenuItem("保存");
	    MenuItem mi14=new MenuItem("另存为");
	    MenuItem mi15=new MenuItem("首选项");
	    MenuItem mi16=new MenuItem("关闭");
	    
	    
	    MenuItem mi111=new MenuItem("数据流");    //只有最后一项需要用MenuItem
	    MenuItem mi7=new MenuItem("退出");
	    
	    MenuItem m21=new MenuItem("马克威文件");
	    MenuItem m22=new MenuItem("数据库");
	    MenuItem m23=new MenuItem("txt文件");
	    MenuItem m24=new MenuItem("Excel");
	    MenuItem m25=new MenuItem("DBF文件");
	    MenuItem m26=new MenuItem("用户输入");
	    MenuItem m27=new MenuItem("通用数据源");
	    MenuItem m28=new MenuItem("数据字典");
	    
	    MenuItem m31=new MenuItem("多维查询");
	    MenuItem m32=new MenuItem("记录选择");
	    MenuItem m33=new MenuItem("变量计算");
	    MenuItem m34=new MenuItem("记录排序");
	    MenuItem m35=new MenuItem("缺失值填充");
	    MenuItem m36=new MenuItem("数据抽样");
	    MenuItem m37=new MenuItem("重新编码");
	    Menu m3d=new Menu("变量处理");
	    Menu m3e=new Menu("文件合并");
	    MenuItem m3d1=new MenuItem("插入变量");
	    MenuItem m3d2=new MenuItem("删除变量");
	    MenuItem m3d3=new MenuItem("变量类型修改");
	    m3d.add(m3d1);
	    m3d.add(m3d2);
	    m3d.add(m3d3);
	    MenuItem m3e1=new MenuItem("记录合并");
	    MenuItem m3e2=new MenuItem("变量合并");
	    m3e.add(m3e1);
	    m3e.add(m3e2);
	    MenuItem m38=new MenuItem("行列变换");
	    MenuItem m39=new MenuItem("数据合并");
	    MenuItem m3a=new MenuItem("数据重构");
	    MenuItem m3b=new MenuItem("分类汇总");
	    MenuItem m3c=new MenuItem("随机数生成");
	    
	    Menu m41=new Menu("相关分析");
	    Menu m42=new Menu("参数检验");
	    Menu m43=new Menu("非参数检验");
	    m41.add(new MenuItem("变量相关分析"));
	    m41.add(new MenuItem("偏相关分析"));
	    m41.add(new MenuItem("典型相关分析"));
	    m42.add(new MenuItem("单样本均值检验"));
	    m42.add(new MenuItem("单样本比例检验"));
	    m42.add(new MenuItem("单样本方差检验"));
	    m42.add(new MenuItem("双样本均值检验"));
	    m42.add(new MenuItem("双样本检验"));
	    m42.add(new MenuItem("双样本方差检验"));
	    m42.add(new MenuItem("配对双样本均值检验"));
	    m43.add(new MenuItem("卡方检验"));
	    m43.add(new MenuItem("二项分布检验"));
	    m43.add(new MenuItem("游程检验"));
	    m43.add(new MenuItem("单样本K-S检验"));
	    m43.add(new MenuItem("两个独立样本检验"));
	    m43.add(new MenuItem("多个独立样本检验"));
	    m43.add(new MenuItem("两个相关样本检验"));
	    m43.add(new MenuItem("多个相关样本检验"));
	    
	    
	    Menu m51=new Menu("回归分析");
	    m51.add(new MenuItem("线性回归"));
	    m51.add(new MenuItem("广义回归"));
	    m51.add(new MenuItem("有序回归"));
	    m51.add(new MenuItem("概率单位回归"));
	    m51.add(new MenuItem("二值逻辑回归"));
	    m51.add(new MenuItem("多值逻辑回归"));
	    m51.add(new MenuItem("曲线回归"));
	    m51.add(new MenuItem("岭回归"));
	    m51.add(new MenuItem("主成分回归"));
	    
	    Menu m52=new Menu("聚类分析");
	    m52.add(new MenuItem("分层聚类"));
	    m52.add(new MenuItem("快速聚类"));
	    
	    Menu m53=new Menu("时间序列");
	    m53.add(new MenuItem("移动平均模型"));
	    m53.add(new MenuItem("ARIMA模型"));
	    m53.add(new MenuItem("季节解构模型"));
	    m53.add(new MenuItem("指数平滑模型"));
	    m53.add(new MenuItem("X11模型"));
	    m53.add(new MenuItem("X-12-ARIMA模型"));
	    
	    Menu m54=new Menu("生存分析");
	    m54.add(new MenuItem("寿命表"));
	    m54.add(new MenuItem("KM过程"));
	    m54.add(new MenuItem("比率风险模型"));
	    Menu m55=new Menu("协整分析");
	    m55.add(new MenuItem("单位根检验"));
	    m55.add(new MenuItem("协整检验"));
	    m55.add(new MenuItem("误差修正模型"));
	    Menu m56=new Menu("指标分析");
	    m56.add(new MenuItem("时差相关分析"));
	    m56.add(new MenuItem("K-L信息量"));

	    
	    Menu m61=new Menu("神经网络");
	    m61.add(new MenuItem("HopField神经网络"));
	    m61.add(new MenuItem("Kohonon神经网络"));
	    m61.add(new MenuItem("ART1神经网络"));
	    m61.add(new MenuItem("BP神经网络"));
	    m61.add(new MenuItem("RBF神经网络"));
	    Menu m62=new Menu("关联规则");
	    m62.add(new MenuItem("单维关联规则"));
	    m62.add(new MenuItem("多维关联规则"));

	    mi11.add(mi111);
 
	    m1.add(mi11);
	    m1.add(mi12);
	    m1.add(mi13);
	    m1.add(mi14);
	    m1.add(mi15);
	    m1.add(mi16);
	    m1.add(mi7);
	    m1.addActionListener(this);
	    
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
	    
	    
	    
	    m4.add(new MenuItem("均值分析"));
	    m4.add(new MenuItem("频率分析"));
	    m4.add(new MenuItem("描述统计"));
	    m4.add(new MenuItem("交叉表"));
	    m4.add(m41);
	    m4.add(m42);
	    m4.add(m43);
	   
	    m5.add(m51);
	    m5.add(m52);
	    m5.add(m53);
	    m5.add(m54);
	    m5.add(m55);
	    m5.add(m56);
	    m5.add(new MenuItem("判别分析"));
	    m5.add(new MenuItem("主成分分析"));
	    m5.add(new MenuItem("因子分析"));
	    m5.add(new MenuItem("方差分析"));
	    m5.add(new MenuItem("向量自回归模型"));
	    m5.add(new MenuItem("Granger因果检验"));
	    m5.add(new MenuItem("联立方程"));
	    m5.add(new MenuItem("面板数据模型"));
	    
	    m6.add(m61);
	    m6.add(m62);
	    m6.add(new MenuItem("决策树"));
	    m6.add(new MenuItem("支持向量机"));
	    m6.add(new MenuItem("模糊聚类"));
	    m6.add(new MenuItem("粗糙集"));
	    m6.add(new MenuItem("孤立点分析"));
	    MenuItem m631=new MenuItem("载入训练数据");
	    MenuItem m632=new MenuItem("载入预测数据");
	    Menu m63=new Menu("贝叶斯预测");
	    m63.add(m631);
	    m63.add(m632);
	    m6.add(m63);
         
	    
        m7.add(new MenuItem("直线图"));
        m7.add(new MenuItem("条状图"));
        m7.add(new MenuItem("圆饼图"));
        m7.add(new MenuItem("面积图"));
        m7.add(new MenuItem("盒状图"));
        m7.add(new MenuItem("直方图"));
        m7.add(new MenuItem("排列图"));
        m7.add(new MenuItem("误差图"));
        m7.add(new MenuItem("序列图"));
        m7.add(new MenuItem("散点图"));
        m7.add(new MenuItem("自相关图"));
        m7.add(new MenuItem("互相关图"));
        m7.add(new MenuItem("控制图"));
        m7.add(new MenuItem("ROC曲线"));
        m7.add(new MenuItem("高低图"));
        m7.add(new MenuItem("P-P图"));
        m7.add(new MenuItem("Q-Q图"));
        m7.add(new MenuItem("经验图"));
       
	    m8.add(new MenuItem("帮助"));
	    m8.add(new MenuItem("马克威产品系列"));
	    m8.add(new MenuItem("关于马克威"));
	    
	    m61.addActionListener(this);
	    m52.addActionListener(this);
	    m63.addActionListener(this);
	    m6.addActionListener(this);
	    mi7.addActionListener(this);
	    m1.addActionListener(this);
	    m2.addActionListener(this);
	    m3.addActionListener(this);
	    m4.addActionListener(this);
	    m5.addActionListener(this);
	    m6.addActionListener(this);
	    m7.addActionListener(this);
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
				 if(file.endsWith(".txt")){
					 ffl.creatUI();             //如果操作的是txt文件，需要手动指定属性名称
					 PublicData.setFile(file);    //同时将文件名设置成当前的，不过此时文件名是txt文件，
					                            //将会在ffl的窗口界面点击提交再次被重命名为xls文件
				 System.out.println(PublicData.getFile());
				 }
				 else if(file.endsWith(".xls")){            //如果是Excel文件，更新当前的数据浏览窗口
					 PublicData.setFile(file);
					 ffl.UpdateAttr();                          //为应对Excel文件的为FileAndFirstLine多加的更新当前文件属性名的方法
					 f.dispose();
					 this.CreatUI(dsp.getTable());
					 f.invalidate();
					 f.repaint();
					 f.setVisible(true);		      
				 }
			   }
		   }
		//在开发初期，没有定义数据格式，无法处理保存和另存为数据，所以基本没用
		 else if(s.equals("另存为"))
		 {		   
		   fd_save.setVisible(true);
		   String d=fd_save.getDirectory();
		   String f=fd_save.getFile();
		   if((d!=null)&&(f!=null))
		         {
				   file=d+f;					
				   frame.setTitle("文件另存为"+file);
				   }
		  }
		 else if(s.equals("保存")){
			   try{
				   PrintWriter pw=new PrintWriter(new FileWriter(file));
				   pw.close();
			   }catch(IOException e1){
				   e1.printStackTrace();
			   }
	 
		 }
		 else if(s.equals("载入训练数据")){           //载入训练数据等同于刚开始的时候用户选择数据
			System.out.println(s);
			 fd_load.setVisible(true);
			  String d=fd_load.getDirectory();
			  String fl=fd_load.getFile();
			  if((d!=null)&&(fl!=null)){
					 file=d+fl;				 	
					 if(file.endsWith(".txt")){
						 ffl.creatUI();             //如果操作的是txt文件，需要手动指定属性名称
						 PublicData.setFile(file);    //同时将文件名设置成当前的，不过此时文件名是txt文件，
						                            //将会在ffl的窗口界面点击提交再次被重命名为xls文件
					 System.out.println(PublicData.getFile());
					 }
					 else if(file.endsWith(".xls")){            //如果是Excel文件，更新当前的数据浏览窗口
						 PublicData.setFile(file);
						 ffl.UpdateAttr();                          //为应对Excel文件的为FileAndFirstLine多加的更新当前文件属性名的方法
						 f.dispose();
						 this.CreatUI(dsp.getTable());
						 f.invalidate();
						 f.repaint();
						 f.setVisible(true);		      
					 }
				   }			
		}
		 else if(s.equals("载入预测数据")){		
			 fd_load.setVisible(true);
			  String d=fd_load.getDirectory();
			  String fl=fd_load.getFile();
			  if((d!=null)&&(fl!=null))
			  {
			    file=d+fl;		 
			    PublicData.setFile(file);
			    ffl.UpdateAttr();                          //为应对Excel文件的为FileAndFirstLine多加的更新当前文件属性名的方法
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