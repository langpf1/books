/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-27 下午02:13:18
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.GradientPaint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 * 气泡图
 * @author 徐骏
 * @data   2010-7-27
 */
public class BubbleChart extends XChartPanellet
{
	public BubbleChart()
	{
		JFreeChart chart = ChartFactory.createBubbleChart("气泡图", "经度", "纬度", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)chart.getPlot();
		xyplot.setForegroundAlpha(0.65F);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.BLUE);
		
		//气泡图的X和Y方向都留白，否则边上的气泡就不完整了
		NumberAxis domainAxis = (NumberAxis)xyplot.getDomainAxis();
		domainAxis.setLowerMargin(0.1);
		domainAxis.setUpperMargin(0.1);
		
		NumberAxis rangeAxis = (NumberAxis)xyplot.getRangeAxis();
		rangeAxis.setLowerMargin(0.1);
		rangeAxis.setUpperMargin(0.1);
		
		setChart(chart);
	}
	private DefaultXYZDataset getDataset()
	{
		DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
		double x[] = {
			118.46,129.65, 135.98, 128.96, 148.96, 178.69, 168.9, 192.63, 202.35, 186.5
		};
		double y[] = {
			32.03, 35.8, 28.96, 45.89, 31.88, 51.62,43.65,51.66, 65.32, 59.3
		};
		double z[] = {
			2,3,2, 6, 8, 4, 3, 7, 5, 4
		};
		double earthquake[][] = {
			x, y, z
		};
		defaultxyzdataset.addSeries("震级", earthquake);
		return defaultxyzdataset;
	}
}
