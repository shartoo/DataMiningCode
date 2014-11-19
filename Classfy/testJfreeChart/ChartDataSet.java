package testJfreeChart;

import java.util.Calendar;  
import java.util.Date;  
import org.jfree.chart.*;  
import org.jfree.data.CategoryDataset;  
import org.jfree.data.DefaultCategoryDataset;  
import org.jfree.data.DefaultHighLowDataset;  
import org.jfree.data.DefaultPieDataset;  
import org.jfree.data.DefaultTableXYDataset;  
import org.jfree.data.HighLowDataset;  
import org.jfree.data.IntervalCategoryDataset;  
import org.jfree.data.OHLCDataItem;  
import org.jfree.data.PieDataset;  
import org.jfree.data.XYSeries;  
import org.jfree.data.XYSeriesCollection;  
import org.jfree.data.gantt.Task;  
import org.jfree.data.gantt.TaskSeries;  
import org.jfree.data.gantt.TaskSeriesCollection;  
import org.jfree.data.time.SimpleTimePeriod;  
  
/** */  
/** 
 * @author xum @ 2006/09/14 JFreeChart 数据集样例 
 *  
 *         TODO To change the template for this generated type comment go to 
 *         Window - Preferences - Java - Code Style - Code Templates 
 */  
public class ChartDataSet {  
  
    private static final String series1 = "series1";  
    private static final String series2 = "series2";  
    private static final String series3 = "series3";  
  
    private static final String category1 = "category1";  
    private static final String category2 = "category2";  
    private static final String category3 = "category3";  
    private static final String category4 = "category4";  
    private static final String category5 = "category5";  
  
    /** */  
    /** 
     * CategoryDataset 支持图表类型 BarChart BarChart3D LineChart LintChart3D 
     * AreaChart WaterfallChart MultiplePieChart MultiplePieChart3D 
     * StackedBarChart StackedBarChart3D StackedAreaChart 
     *  
     * @return CategoryDataset 
     */  
    public static CategoryDataset createCategoryDataset() {  
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();  
  
        categoryDataset.addValue(2.0, series1, category1);  
        categoryDataset.addValue(4.0, series1, category2);  
        categoryDataset.addValue(3.0, series1, category3);  
        categoryDataset.addValue(7.0, series1, category4);  
        categoryDataset.addValue(5.0, series1, category5);  
  
        categoryDataset.addValue(5.0, series2, category1);  
        categoryDataset.addValue(9.0, series2, category2);  
        categoryDataset.addValue(6.0, series2, category3);  
        categoryDataset.addValue(5.0, series2, category4);  
        categoryDataset.addValue(2.0, series2, category5);  
  
        categoryDataset.addValue(6.5, series3, category1);  
        categoryDataset.addValue(7.5, series3, category2);  
        categoryDataset.addValue(4.0, series3, category3);  
        categoryDataset.addValue(8.0, series3, category4);  
        categoryDataset.addValue(9.0, series3, category5);  
  
        return categoryDataset;  
    }  
  
    /** */  
    /** 
     * XYSeriesCollection 支持图表类型 PolarChart XYBarChart XYLineChart 
     * XYStepAreaChart XYAreaChart XYStepChart Histogram ScatterPlot 
     *  
     * @return XYSeriesCollection 
     */  
    public static XYSeriesCollection createXYSeriesCollection() {  
        XYSeriesCollection dataset = new XYSeriesCollection();  
  
        XYSeries xyseries1 = new XYSeries(series1);  
        XYSeries xyseries2 = new XYSeries(series2);  
        XYSeries xyseries3 = new XYSeries(series3);  
  
        xyseries1.add(2.5, 6.0);  
        xyseries1.add(4.0, 3.5);  
        xyseries1.add(7.5, 5.5);  
        xyseries1.add(12.0, 7.0);  
        xyseries1.add(9.5, 10.0);  
  
        xyseries2.add(4.5, 11.0);  
        xyseries2.add(6.0, 3.5);  
        xyseries2.add(2.5, 5.0);  
        xyseries2.add(3.5, 6.5);  
        xyseries2.add(5.0, 7.0);  
  
        xyseries3.add(7.0, 12.0);  
        xyseries3.add(4.5, 8.0);  
        xyseries3.add(6.0, 4.5);  
        xyseries3.add(9.5, 11.0);  
        xyseries3.add(3.0, 6.0);  
  
        dataset.addSeries(xyseries1);  
        dataset.addSeries(xyseries2);  
        dataset.addSeries(xyseries3);  
  
        return dataset;  
    }  
  
    /** */  
    /** 
     * DefaultTableXYDataset 支持图表类型 StackedXYAreaChart 
     *  
     * @return DefaultTableXYDataset 
     */  
    public static DefaultTableXYDataset createDefaultTableXYDataset() {  
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();  
  
        XYSeries xyseries1 = new XYSeries(series1);  
        XYSeries xyseries2 = new XYSeries(series2);  
        XYSeries xyseries3 = new XYSeries(series3);  
  
        xyseries1.add(2.5, 6.0);  
        xyseries1.add(4.0, 3.5);  
        xyseries1.add(7.5, 5.5);  
        xyseries1.add(12.0, 7.0);  
        xyseries1.add(9.5, 10.0);  
  
        xyseries2.add(4.5, 11.0);  
        xyseries2.add(6.0, 3.5);  
        xyseries2.add(2.5, 5.0);  
        xyseries2.add(3.5, 6.5);  
        xyseries2.add(5.0, 7.0);  
  
        xyseries3.add(7.0, 12.0);  
        xyseries3.add(4.5, 8.0);  
        xyseries3.add(6.0, 4.5);  
        xyseries3.add(9.5, 11.0);  
        xyseries3.add(3.0, 6.0);  
  
        dataset.addSeries(xyseries1);  
        dataset.addSeries(xyseries2);  
        dataset.addSeries(xyseries3);  
  
        return dataset;  
    }  
  
    /** */  
    /** 
     * PieDataset 支持图表类型 PieChart PieChart3D RingChart 
     *  
     * @return PieDataset 
     */  
    public static PieDataset createPieDataset() {  
        DefaultPieDataset dataset = new DefaultPieDataset();  
  
        dataset.setValue(category1, 12.0);  
        dataset.setValue(category2, 2.0);  
        dataset.setValue(category3, 4.0);  
        dataset.setValue(category4, 20.0);  
        dataset.setValue(category5, 7.0);  
  
        return dataset;  
    }  
  
    /** */  
    /** 
     * IntervalCategoryDataset 支持图表类型 GanttChart 
     *  
     * @return IntervalCategoryDataset 
     */  
    public static IntervalCategoryDataset createGanttDataset() {  
        TaskSeriesCollection taskSeriesCollection = new TaskSeriesCollection();  
  
        TaskSeries s1 = new TaskSeries("Scheduled");  
        s1.add(new Task("Write Proposal", new SimpleTimePeriod(date(1,  
                Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001))));  
        s1.add(new Task("Obtain Approval", new SimpleTimePeriod(date(9,  
                Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001))));  
        s1.add(new Task("Requirements Analysis", new SimpleTimePeriod(date(10,  
                Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001))));  
        s1.add(new Task("Design Phase", new SimpleTimePeriod(date(6,  
                Calendar.MAY, 2001), date(30, Calendar.MAY, 2001))));  
        s1.add(new Task("Design Signoff", new SimpleTimePeriod(date(2,  
                Calendar.JUNE, 2001), date(2, Calendar.JUNE, 2001))));  
        s1.add(new Task("Alpha Implementation", new SimpleTimePeriod(date(3,  
                Calendar.JUNE, 2001), date(31, Calendar.JULY, 2001))));  
        s1.add(new Task("Design Review", new SimpleTimePeriod(date(1,  
                Calendar.AUGUST, 2001), date(8, Calendar.AUGUST, 2001))));  
        s1.add(new Task("Revised Design Signoff", new SimpleTimePeriod(date(10,  
                Calendar.AUGUST, 2001), date(10, Calendar.AUGUST, 2001))));  
        s1.add(new Task("Beta Implementation", new SimpleTimePeriod(date(12,  
                Calendar.AUGUST, 2001), date(12, Calendar.SEPTEMBER, 2001))));  
        s1.add(new Task("Testing", new SimpleTimePeriod(date(13,  
                Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001))));  
        s1.add(new Task("Final Implementation", new SimpleTimePeriod(date(1,  
                Calendar.NOVEMBER, 2001), date(15, Calendar.NOVEMBER, 2001))));  
        s1.add(new Task("Signoff", new SimpleTimePeriod(date(28,  
                Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001))));  
        TaskSeries s2 = new TaskSeries("Actual");  
        s2.add(new Task("Write Proposal", new SimpleTimePeriod(date(1,  
                Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001))));  
        s2.add(new Task("Obtain Approval", new SimpleTimePeriod(date(9,  
                Calendar.APRIL, 2001), date(9, Calendar.APRIL, 2001))));  
        s2.add(new Task("Requirements Analysis", new SimpleTimePeriod(date(10,  
                Calendar.APRIL, 2001), date(15, Calendar.MAY, 2001))));  
        s2.add(new Task("Design Phase", new SimpleTimePeriod(date(15,  
                Calendar.MAY, 2001), date(17, Calendar.JUNE, 2001))));  
        s2.add(new Task("Design Signoff", new SimpleTimePeriod(date(30,  
                Calendar.JUNE, 2001), date(30, Calendar.JUNE, 2001))));  
        s2.add(new Task("Alpha Implementation", new SimpleTimePeriod(date(1,  
                Calendar.JULY, 2001), date(12, Calendar.SEPTEMBER, 2001))));  
        s2  
                .add(new Task("Design Review", new SimpleTimePeriod(date(12,  
                        Calendar.SEPTEMBER, 2001), date(22, Calendar.SEPTEMBER,  
                        2001))));  
        s2  
                .add(new Task("Revised Design Signoff", new SimpleTimePeriod(  
                        date(25, Calendar.SEPTEMBER, 2001), date(27,  
                                Calendar.SEPTEMBER, 2001))));  
        s2.add(new Task("Beta Implementation", new SimpleTimePeriod(date(27,  
                Calendar.SEPTEMBER, 2001), date(30, Calendar.OCTOBER, 2001))));  
        s2.add(new Task("Testing", new SimpleTimePeriod(date(31,  
                Calendar.OCTOBER, 2001), date(17, Calendar.NOVEMBER, 2001))));  
        s2.add(new Task("Final Implementation", new SimpleTimePeriod(date(18,  
                Calendar.NOVEMBER, 2001), date(5, Calendar.DECEMBER, 2001))));  
        s2.add(new Task("Signoff", new SimpleTimePeriod(date(10,  
                Calendar.DECEMBER, 2001), date(11, Calendar.DECEMBER, 2001))));  
        taskSeriesCollection.add(s1);  
        taskSeriesCollection.add(s2);  
  
        return taskSeriesCollection;  
    }  
  
    /** */  
    /** 
     * DefaultOHLCDataset 支持的图表类型 HighLowChart CandlestickChart 
     *  
     * @return DefaultOHLCDataset 
     */  
    public static DefaultHighLowDataset createDefaultOHLCDataset() {  
  
        OHLCDataItem[] data = new OHLCDataItem[10];  
  
        data[0] = new OHLCDataItem(new Date(11L), 2.0, 4.0, 1.0, 3.0, 100.0);  
        data[1] = new OHLCDataItem(new Date(22L), 4.0, 9.0, 2.0, 5.0, 120.0);  
        data[2] = new OHLCDataItem(new Date(33L), 3.0, 7.0, 3.0, 6.0, 140.0);  
        data[3] = new OHLCDataItem(new Date(25L), 2.0, 4.0, 1.0, 3.0, 100.0);  
        data[4] = new OHLCDataItem(new Date(47L), 4.0, 9.0, 2.0, 5.0, 120.0);  
        data[5] = new OHLCDataItem(new Date(58L), 3.0, 7.0, 3.0, 6.0, 140.0);  
        data[6] = new OHLCDataItem(new Date(60L), 2.0, 4.0, 1.0, 3.0, 100.0);  
        data[7] = new OHLCDataItem(new Date(44L), 4.0, 9.0, 2.0, 5.0, 120.0);  
        data[8] = new OHLCDataItem(new Date(38L), 3.0, 7.0, 3.0, 6.0, 140.0);  
        data[9] = new OHLCDataItem(new Date(40L), 3.0, 7.0, 3.0, 6.0, 140.0);  
  
        HighLowDataset defaultHighLowDataset = new DefaultHighLowDataset(null, null, null, null, null, null, null);  
  
        return (DefaultHighLowDataset) defaultHighLowDataset;  
    }  
  
    /** */  
    /** 
     * DefaultXYZDataset 支持图表类型 BubbleChart 
     *  
     * @return DefaultXYZDataset 
     */  
    public static DefaultCategoryDataset createDefaultXYZDataset() {  
        DefaultCategoryDataset defaultXYZDataset = new DefaultCategoryDataset();  
  
        double[] x1 = new double[] { 1.0, 2.0, 3.0 };  
        double[] y1 = new double[] { 4.0, 5.0, 6.0 };  
        double[] z1 = new double[] { 7.0, 8.0, 9.0 };  
        double[][] data1 = new double[][] { x1, y1, z1 };  
        //defaultXYZDataset.addSeries(series1, data1);  
  
        double[] x2 = new double[] { 1.0, 2.0, 3.0 };  
        double[] y2 = new double[] { 4.0, 5.0, 6.0 };  
        double[] z2 = new double[] { 7.0, 8.0, 9.0 };  
        double[][] data2 = new double[][] { x2, y2, z2 };  
        //defaultXYZDataset.addSeries(series1, data2);  
  
        return defaultXYZDataset;  
    }  
  
    private static Date date(int day, int month, int year) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(year, month, day);  
        Date result = calendar.getTime();  
        return result;  
    }  
}  