/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-27 下午02:50:22
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;
/**
 * 散点图
 * @author 徐骏
 * @data   2010-7-27
 */
public class ScatterChart extends XChartPanellet
{
	public ScatterChart()
	{
		JFreeChart chart = ChartFactory.createScatterPlot("散点图", "时限", "收益", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)chart.getPlot();
		xyplot.setForegroundAlpha(0.5f);
		xyplot.getDomainAxis().setLowerMargin(0.1);
		xyplot.getRangeAxis().setUpperMargin(0.1);
		//自定义sharp样式
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesShape(0,new Ellipse2D.Double(-5,-5,10,10));//-5是针对坐标的，数值点为(0,0)
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesLinesVisible(0, false);
		
		//sharp样式2	
		renderer.setSeriesShape(1,new Ellipse2D.Double(-5,-5,10,10));//-5是针对坐标的，数值点为(0,0)
		renderer.setSeriesPaint(1, Color.BLUE);
		renderer.setSeriesLinesVisible(1, false);
		xyplot.setRenderer(0, renderer);
		setChart(chart);
	}
	private XYDataset getDataset()
	{
		XYSeries series1=new XYSeries("百事");
		for(int i=1;i<=50;i++)
		{
			series1.add(i,Math.random() * 100);
		}
		XYSeries series2=new XYSeries("可口可乐");
		for(int i=1;i<=50;i++)
		{
			series2.add(i,Math.random() * 80);
		}
		XYSeriesCollection seriesCollection = new XYSeriesCollection();
		seriesCollection.addSeries(series1);
		seriesCollection.addSeries(series2);
		return seriesCollection;
	}
}
