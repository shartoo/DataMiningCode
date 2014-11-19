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
	public int[] v=new int[100];   //����ʼ��������Ļ�Ĭ��ֻ��һ��Ԫ��null��Ȼ��ָ��i>2ʱ�ͻ�outofindex����
	int p=0;
	String name=PublicData.getTarvar()+"���Ա�״ͼ";               //ͳ������
	DefaultPieDataset piedata = new DefaultPieDataset();
	public static void main(String[] args){
		ShowPieChart s=new ShowPieChart();
		s.Look();
	}
	    public JFreeChart createPaiChart() {
	        //DefaultPieDataset data = getDataSet();
	        DefaultPieDataset data = getDataSet();
	        JFreeChart chart = ChartFactory.createPieChart3D(name,  // ͼ�����
	        data,
	        true, // �Ƿ���ʾͼ��
	        false,
	        false
	        );
	        PiePlot plot = (PiePlot) chart.getPlot();
	        resetPiePlot(plot);
	        return chart;
	    }
	   
	    private  void resetPiePlot(PiePlot plot) {
	    	 /* 
	         * �Ǳ�ǩ��ʽ��Ĭ���ǡ�{0} = {1}����������ʾ��ݣ��� 
	         * {0}�������{1}��ʵ����ݣ������{2}�ǰٷֱȡ� 
	         */  
	        String unitSytle = "{0}={1}({2})";
	        plot.setNoDataMessage("�޶�Ӧ����ݣ������²�ѯ��");
	        plot.setNoDataMessagePaint(Color.red);
	        //ָ�� section �����ߵĺ��(OutlinePaint����Ϊnull)
	        plot.setOutlineStroke(new BasicStroke(0));
	        //���õ�һ�� section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���
	        plot.setStartAngle(90);        
	        plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,
	                NumberFormat.getNumberInstance(),
	                new DecimalFormat("0.00%")));
	       
	        //ָ��ͼƬ��͸����
	        plot.setForegroundAlpha(0.65f);
	       
	        //�����ǩ��ʾ��ʽ
	        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
	                NumberFormat.getNumberInstance(),
	                new DecimalFormat("0.00%")));
	           
	        //ͼ����ʾ��ʽ
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
				String[] msg=new String(s).trim().split(",|��");
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
			  System.out.println("�ļ������ڣ���˶�");
		  }
		  for(int e=0;e<list.size();e++)
		  {
			System.out.println(list.get(e)+"��"+v[e]);  
		  }
	 }
//public static void main(String[] args){
public void Look(){
		 System.out.println("Why not show?");
		 ShowPieChart ss=new ShowPieChart();
		 ss.setTitle("圆饼图");
		 ss.readFile();	 
		
		 JFreeChart pie=ss.createPaiChart();
		
		 JScrollPane jp=new JScrollPane();		
		 ChartPanel pane2=new ChartPanel(pie,false);		
		 jp.setViewportView(pane2);
		 ss.add(jp,"North");
		 ss.setSize(600,800);
		 ss.setVisible(true);
	 }		 
}
