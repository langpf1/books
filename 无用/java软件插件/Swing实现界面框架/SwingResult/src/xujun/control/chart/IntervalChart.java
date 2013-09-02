/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-20 下午01:23:54
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.YIntervalRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

/**
 * 幕间图
 * @author 徐骏
 * @data   2010-7-20
 */
public class IntervalChart extends XChartPanellet
{

	public IntervalChart()
	{
		JFreeChart chart = ChartFactory.createXYLineChart("幕间图", "时间", "温度", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = chart.getXYPlot();
		DeviationRenderer deviationrenderer = new DeviationRenderer(true, false);//line,sharp
		deviationrenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1)); //line stroke
		deviationrenderer.setSeriesFillPaint(0, new Color(255, 200, 200)); //幕间填充的Paint
		xyplot.setRenderer(deviationrenderer);
		//设置Domain坐标是整数，不让jfreechart自己自动计算成小数
		NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setLowerMargin(0.01);//设置图形左右两边留白
		numberaxis.setUpperMargin(0.01);
		//设置Range坐标范围
		NumberAxis rangeAxis = (NumberAxis)xyplot.getRangeAxis();
		rangeAxis.setRange(10, 40);
		
		setChart(chart);
	}
	private XYDataset getDataset()
	{
		YIntervalSeries intervalSeries = new YIntervalSeries("温度");	
		intervalSeries.add(0, 12.5, 15, 30);
		intervalSeries.add(1, 13.5, 15, 30);
		intervalSeries.add(2, 14.5, 15, 30);
		intervalSeries.add(3, 12.5, 15, 30);
		intervalSeries.add(4, 17.5, 15, 30);
		intervalSeries.add(5, 19.5, 15, 30);
		intervalSeries.add(6, 22.5, 15, 30);
		intervalSeries.add(7, 27.5, 15, 30);
		intervalSeries.add(8, 29.5, 15, 30);
		intervalSeries.add(9, 31.5, 15, 30);
		intervalSeries.add(10, 35.5, 15, 30);
		intervalSeries.add(11, 29.5, 15, 30);
		intervalSeries.add(12, 32.5, 15, 30);
		intervalSeries.add(13, 22.5, 15, 30);
		
		YIntervalSeriesCollection seriesCollection = new YIntervalSeriesCollection();
		seriesCollection.addSeries(intervalSeries);
		return seriesCollection;
	}
}
