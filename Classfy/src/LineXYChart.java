

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

/**
* @author hong 曲线图的绘制
*/
public class LineXYChart
{
    /**
     * 返回生成图片的文件名
     * @param session
     * @param pw
     * @return 生成图片的文件名
     */
    public String getLineXYChart(HttpSession session, PrintWriter pw)
    {
        XYDataset dataset = this.createDateSet();//建立数据集
        String fileName = null;
        //建立JFreeChart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "JFreeChart时间曲线序列图", // title
                "Date", // x-axis label
                "Price", // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false // generate URLs?
                );
        //设置JFreeChart的显示属性,对图形外部部分进行调整
        chart.setBackgroundPaint(Color.red);//设置曲线图背景色
        //设置字体大小，形状
        Font font = new Font("宋体", Font.BOLD, 16);
        TextTitle title = new TextTitle("JFreeChart时间曲线序列图", font);
        chart.setTitle(title);

        XYPlot plot = (XYPlot) chart.getPlot();//获取图形的画布
        plot.setBackgroundPaint(Color.lightGray);//设置网格背景色
        plot.setDomainGridlinePaint(Color.green);//设置网格竖线(Domain轴)颜色
        plot.setRangeGridlinePaint(Color.white);//设置网格横线颜色
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));//设置曲线图与xy轴的距离
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer)
        {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setShapesVisible(true);//设置曲线是否显示数据点
        }

        //设置Y轴
        NumberAxis numAxis = (NumberAxis) plot.getRangeAxis();
        NumberFormat numFormater = NumberFormat.getNumberInstance();
        numFormater.setMinimumFractionDigits(2);
        numAxis.setNumberFormatOverride(numFormater);

        //设置提示信息
        StandardXYToolTipGenerator tipGenerator = new StandardXYToolTipGenerator(
                "历史信息{1} 16:00,{2})", new SimpleDateFormat("MM-dd",
                numFormater);
        r.setToolTipGenerator(tipGenerator);

        //设置X轴（日期轴）
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM-dd");

        ChartRenderingInfo info = new ChartRenderingInfo(
                new StandardEntityCollection());
        try
        {
            fileName = ServletUtilities.saveChartAsPNG(chart, 500, 300, info,
                    session);//生成图片
//          Write the image map to the PrintWriter
            ChartUtilities.writeImageMap(pw, fileName, info, false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        pw.flush();
        return fileName;//返回生成图片的文件名
    }

    /**
     * 建立生成图形所需的数据集
     * @return 返回数据集
     */
    private XYDataset createDateSet()
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();//时间曲线数据集合
        TimeSeries s1 = new TimeSeries("历史曲线", Day.class);//创建时间数据源，每一个//TimeSeries在图上是一条曲线

        //s1.add(new Day(day,month,year),value),添加数据点信息
        s1.add(new Day(1, 2, 2006), 123.51);
        s1.add(new Day(2, 2, 2006), 122.1);
        s1.add(new Day(3, 2, 2006), 120.86);
        s1.add(new Day(4, 2, 2006), 122.50);
        s1.add(new Day(5, 2, 2006), 123.12);
        s1.add(new Day(6, 2, 2006), 123.9);
        s1.add(new Day(7, 2, 2006), 124.47);
        s1.add(new Day(8, 2, 2006), 124.08);
        s1.add(new Day(9, 2, 2006), 123.55);
        s1.add(new Day(10, 2, 2006), 122.53);

        dataset.addSeries(s1);
        dataset.setDomainIsPointsInTime(true);
        return dataset;
    }
}