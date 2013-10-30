package Genera_decis_tree;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ShowBarChart extends JFrame{

	
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
	public void Look(){
		ShowBarChart sbc=new ShowBarChart();
		sbc.setTitle("直方图");
		 JFreeChart jc=sbc.createBarChart();
		 ChartPanel pane1=new ChartPanel(jc,false);
		 JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(pane1);
		sbc.add(jsp,"Center");
		sbc.setSize(400,600);
		sbc.setVisible(true);
		
	}
	public static void main(String[] args){
		ShowBarChart sb=new ShowBarChart();
		sb.Look();
	}
	
}
