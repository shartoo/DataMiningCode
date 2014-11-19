package testJfreeChart;

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Image;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import org.jfree.chart.JFreeChart;  
  
/** */  
/** 
 * @author xum @ 2006/09/14 neusoft * TODO To change the template for this 
 *         generated type comment go to Window - Preferences - Java - Code Style 
 *         - Code Templates 
 */  
public class ChartFrame extends JFrame {  
  
    /** 
     *  
     */  
    public ChartFrame() {  
        this.setBounds((int) ((1024 - 800) / 2), (int) ((768 - 600) / 2), 800,  
                600);  
        this.setTitle("JFreeChart Demo");  
  
        JFreeChart Chart = null;  
  
         //Chart = ChartCreater.createCategoryChart();  
        // Chart = ChartCreater.createCategoryChart3D();  
        // Chart = ChartCreater.createBarChart();  
        // Chart = ChartCreater.createBarChart3D();  
        // Chart = ChartCreater.createAreaChart();  
        // Chart = ChartCreater.createHistogram();  
        // Chart = ChartCreater.createLintChart3D();  
        // Chart = ChartCreater.createMultiplePieChart();  
         //Chart = ChartCreater.createMultiplePieChart3D();  
        // Chart = ChartCreater.createPieChart();  
        //Chart = ChartCreater.createPieChart3D();  
        // Chart = ChartCreater.createRingChart();  
         Chart = ChartCreater.createScatterPlot();  
        // Chart = ChartCreater.createBubbleChart();  //youcuo  
        // Chart = ChartCreater.createCandlestickChart(); //youcuo  
        // Chart = ChartCreater.createCombinedChart();  
        // Chart = ChartCreater.createGanttChart();  
        // Chart = ChartCreater.createHighLowChart();  //youcuo  
        // Chart = ChartCreater.createStackedAreaChart();  
        // Chart = ChartCreater.createStackedBarChart();  
        // Chart = ChartCreater.createStackedBarChart3D();  
        // Chart = ChartCreater.createStackedCategoryChart();  
        // Chart = ChartCreater.createStackedCategoryChart3D();  
        // Chart = ChartCreater.createStackedXYAreaChart();  //youcuo  
        // Chart = ChartCreater.createWaterfallChart();  
        // Chart = ChartCreater.createXYAreaChart();  
        // Chart = ChartCreater.createXYBarChart();  
        // Chart = ChartCreater.createXYLineChart();  
        // Chart = ChartCreater.createXYStepAreaChart();  
        // Chart = ChartCreater.createXYStepChart();  
  
        Chart.getPlot().setBackgroundAlpha(1.0f);  
        Chart.getPlot().setNoDataMessage("当前没有有效的数据");  
  
        Image chart = Chart.createBufferedImage(700, 500);  
  
        JLabel label = new JLabel();  
  
        label.setIcon(new ImageIcon(chart));  
  
        this.getContentPane().add(label, BorderLayout.CENTER);  
  
    }  
  
    public static void main(String[] args) {  
        new ChartFrame().setVisible(true);  
    }  
}  