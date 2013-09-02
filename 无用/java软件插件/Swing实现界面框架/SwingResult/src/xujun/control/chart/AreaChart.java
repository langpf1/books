/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-26 下午02:49:55
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

/**
 * @author 徐骏
 * @data   2010-7-26
 */
public class AreaChart extends XChartPanellet
{
	public AreaChart()
	{
		JFreeChart jfreechart = ChartFactory.createXYAreaChart("区域填充线图", "城市", "绿化覆盖率", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		
		//xyplot.setForegroundAlpha(0.65F);//设置前景透明度
		ValueAxis domainAxis = xyplot.getDomainAxis();
		domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//整型标注
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.0);
		
		XYTextAnnotation textAnnotation = new XYTextAnnotation("一个文本标注",2005, 2256);	//x和y分别是domain和range坐标的值	
		textAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(textAnnotation);
		
		setChart(jfreechart);
	}
	private XYDataset getDataset()
	{
		XYSeries series1 = new XYSeries("上海");
		series1.add(2000, 568);
		series1.add(2001, 889);
		series1.add(2002, 423);
		series1.add(2003, 1256);
		series1.add(2004, 1356);
		series1.add(2005, 2256);
		series1.add(2006, 2896);
		series1.add(2007, 789);
		series1.add(2008, 2610);
		series1.add(2009, 1542);
		series1.add(2010, 1366);
		
		XYSeries series2 = new XYSeries("北京");
		series2.add(2000, 1230);
		series2.add(2001, 524);
		series2.add(2002, 687);
		series2.add(2003, 1542);
		series2.add(2004, 1362);
		series2.add(2005, 2260);
		series2.add(2006, 1456);
		series2.add(2007, 789);
		series2.add(2008, 1439);
		series2.add(2009, 897);
		series2.add(2010, 1006);
		
		XYSeriesCollection seriesCollection = new XYSeriesCollection();
		seriesCollection.addSeries(series1);
		seriesCollection.addSeries(series2);
		return seriesCollection;
	}
}
