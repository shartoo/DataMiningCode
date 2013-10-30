package Genera_decis_tree;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class ShowPieChart extends JFrame{
	public static ArrayList<String> list=new ArrayList<String>(); 
	public int[] v=new int[100];   //如果不初始化，后面的会默认只有一个元素null，然后指向i>2时就会outofindex错误
	int p=0;
	String name=PublicData.getTarvar()+"属性饼状图";               //统计属性
	DefaultPieDataset piedata = new DefaultPieDataset();
	public static void main(String[] args){
		ShowPieChart s=new ShowPieChart();
		s.Look();
	}
	    public JFreeChart createPaiChart() {
	        //DefaultPieDataset data = getDataSet();
	        DefaultPieDataset data = getDataSet();
	        JFreeChart chart = ChartFactory.createPieChart3D(name,  // 图表标题
	        data,
	        true, // 是否显示图例
	        false,
	        false
	        );
	        PiePlot plot = (PiePlot) chart.getPlot();
	        resetPiePlot(plot);
	        return chart;
	    }
	   
	    private  void resetPiePlot(PiePlot plot) {
	    	 /* 
	         * 是标签格式（默认是“{0} = {1}”这样是显示数据）， 
	         * {0}是类别名，{1}是实际数据，后面的{2}是百分比。 
	         */  
	        String unitSytle = "{0}={1}({2})";
	        plot.setNoDataMessage("无对应的数据，请重新查询。");
	        plot.setNoDataMessagePaint(Color.red);
	        //指定 section 轮廓线的厚度(OutlinePaint不能为null)
	        plot.setOutlineStroke(new BasicStroke(0));
	        //设置第一个 section 的开始位置，默认是12点钟方向
	        plot.setStartAngle(90);        
	        plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,
	                NumberFormat.getNumberInstance(),
	                new DecimalFormat("0.00%")));
	       
	        //指定图片的透明度
	        plot.setForegroundAlpha(0.65f);
	       
	        //引出标签显示样式
	        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
	                NumberFormat.getNumberInstance(),
	                new DecimalFormat("0.00%")));
	           
	        //图例显示样式
	        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
	                NumberFormat.getNumberInstance(),
	                new DecimalFormat("0.00%")));
	    }
	 
	    private  DefaultPieDataset getDataSet() {
	        for(int i=0;i<list.size();i++){
	        	piedata.setValue(list.get(i),v[i]);
	        }
	        return piedata;
	    }
	    
	 public void readFile(){
		   String dir="d:\\predata\\";
           String last=".txt";
           String attr=PublicData.getTarvar();
		  String file=dir+attr+last;
		  try{
			  BufferedReader br=new BufferedReader(new FileReader(file));
			  String s;
			  while((s=br.readLine())!=null){
				String[] msg=new String(s).trim().split(",|，");
				for(int i=0;i<msg.length;i++){
					  if(i%2==0){
						  if(list.contains(msg[i])==false){
							  list.add(msg[i]);
							  p=list.indexOf(msg[i]);
							  System.out.print(msg[i]+"/t");
						                  }
						  else if(list.contains(msg[i])==true)
						      {
							   p=list.indexOf(msg[i]);
						       }
					     }
					  else if(i%2==1){
						   if(msg[i].equals("very_recom")&&list.contains(msg[i-1]))
						      {
							 p=list.indexOf(msg[i-1]);
							 System.out.print("\t"+msg[i]);
							 v[p]++;
					    }
					  }
				  }
			  }			  
		  }catch(IOException e){
			  e.printStackTrace();
			  System.out.println("文件不存在！请核对");
		  }
		  for(int e=0;e<list.size();e++)
		  {
			System.out.println(list.get(e)+"有"+v[e]);  
		  }
	 }
//public static void main(String[] args){
public void Look(){
		 System.out.println("Why not show?");
		 ShowPieChart ss=new ShowPieChart();
		 ss.setTitle("饼状图");
		 ss.readFile();	 
		
		 JFreeChart pie=ss.createPaiChart();
		
		 JScrollPane jp=new JScrollPane();
		 //把两个chart变成panel加入到frame中
		
		 ChartPanel pane2=new ChartPanel(pie,false);		
		 jp.setViewportView(pane2);
		 ss.add(jp,"North");
		 ss.setSize(600,800);
		 ss.setVisible(true);
		 //以下是只用一个frame显示的代码
		 //ChartFrame frame=new ChartFrame("饼状图测试",jc); 
		 //frame.pack();
		 //frame.setVisible(true);
	 }		 
}
