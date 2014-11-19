package Genera_decis_tree;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class FruitService extends JPanel{
    public static JFreeChart createBarChart() {
        CategoryDataset dataset = getDataSet2();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "水果产量图", // 图表标题
                "水果", // 目录轴的显示标签
                "产量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,   // 是否显示图例(对于简单的柱状图必须是false)
                true,   // 是否生成工具
                true    // 是否生成URL链接
                );
        return chart;
    }
 
    private static CategoryDataset getDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "北京", "苹果");
        dataset.addValue(100, "上海", "苹果");
        dataset.addValue(100, "广州", "苹果");
        dataset.addValue(200, "北京", "梨子");
        dataset.addValue(200, "上海", "梨子");
        dataset.addValue(200, "广州", "梨子");
       // dataset.addValue(300, "北京", "葡萄");
        dataset.addValue(300, "上海", "葡萄");
        dataset.addValue(300, "广州", "葡萄");
        dataset.addValue(400, "北京", "香蕉");
        dataset.addValue(400, "上海", "香蕉");
        dataset.addValue(400, "广州", "香蕉");
        dataset.addValue(500, "北京", "荔枝");
        dataset.addValue(500, "上海", "荔枝");
        dataset.addValue(500, "广州", "荔枝");
        return dataset;
    }
    public static JFreeChart createPaiChart() {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("水果产量图",  // 图表标题
        data,
        true, // 是否显示图例
        false,
        false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        resetPiePlot(plot);
        return chart;
    }
   
    private static void resetPiePlot(PiePlot plot) {
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
 
    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("苹果",100);
        dataset.setValue("梨子",200);
        dataset.setValue("葡萄",300);
        dataset.setValue("香蕉",400);
        dataset.setValue("荔枝",500);
        return dataset;
    }
 public static void main(String[] args){
	 JFrame f=new JFrame("图标实例");
	 FruitService fruit=new FruitService();
	 JFreeChart jc=fruit.createBarChart();
	 JFreeChart pie=fruit.createPaiChart();
	 JScrollPane jsp=new JScrollPane();
	 JScrollPane jp=new JScrollPane();
	 //把两个chart变成panel加入到frame中
	 ChartPanel pane1=new ChartPanel(jc,false);
	 ChartPanel pane2=new ChartPanel(pie,false);
	 jsp.setViewportView(pane1);
	 jp.setViewportView(pane2);
	 f.add(jsp,"South");
	 f.add(jp,"North");
	 f.setSize(600,800);
	 f.setVisible(true);
	 //以下是只用一个frame显示的代码
	 //ChartFrame frame=new ChartFrame("饼状图测试",jc); 
	 //frame.pack();
	 //frame.setVisible(true);
 }
}