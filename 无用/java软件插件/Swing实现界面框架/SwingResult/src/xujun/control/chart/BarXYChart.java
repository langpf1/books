/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-15 上午10:41:39
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;

/**
 * @author 徐骏
 * @data   2010-7-15
 */
public class BarXYChart extends XChartPanellet
{
	public BarXYChart()
	{
		//标题，X Lable，启用时间轴，Y Lable，xy数据集，柱子的方向：垂直,启用图例，启用tooltip，启用URL
		JFreeChart chart = ChartFactory.createXYBarChart("时间柱状图", "Date", true, "rian", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		//组群的柱状图渲染器,参数1：每组间的间距    参数2：是否对DomainGridLine居中
		ClusteredXYBarRenderer clusteredXYBarRenderer = new ClusteredXYBarRenderer(0.1f,false);
		//不显示阴影
		clusteredXYBarRenderer.setShadowVisible(false);
		//设置bar的paint
		clusteredXYBarRenderer.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(0, 0, 64)));
		clusteredXYBarRenderer.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 0, 64)));
		//设置渲染器
		XYPlot xyPlot = chart.getXYPlot();
		xyPlot.setRenderer(clusteredXYBarRenderer);
		//设置日期格式
        DateAxis dateaxis = (DateAxis) xyPlot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        //Domain Label的位置，默认是靠左 
        //dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		dateaxis.setLowerMargin(0.01D);
		dateaxis.setUpperMargin(0.01D);
		//显示DomainGridLine
		chart.getXYPlot().setDomainGridlinePaint(Color.GRAY);
		setChart(chart);
	}
	public JToolBar getCharToolBar() 
	{
		JToolBar toolBar = super.getCharToolBar();
		toolBar.add(new JLabel("按住Ctrl键+鼠标，可以移动图形"));
		return toolBar;
	}
	private IntervalXYDataset getDataset()
	{
		TimeSeries timeseries = new TimeSeries("xujun");
		timeseries.add(new Day(1, 1, 2010), 50.6D);
		timeseries.add(new Day(2, 1, 2010), 20.3D);
		timeseries.add(new Day(3, 1, 2010), 43.3D);
		timeseries.add(new Day(4, 1, 2010), -12D);
		timeseries.add(new Day(5, 1, 2010), 87.6D);
		timeseries.add(new Day(6, 1, 2010), 56.3D);
		timeseries.add(new Day(7, 1, 2010), 48.3D);
		timeseries.add(new Day(8, 1, 2010), 17.0D);
		timeseries.add(new Day(9, 1, 2010), 58.6D);
		timeseries.add(new Day(10, 1, 2010), 22.3D);
		timeseries.add(new Day(11, 1, 2010), 47.3D);
		timeseries.add(new Day(12, 1, 2010), 14.5D);
		
		TimeSeries timeseries1 = new TimeSeries("jack");
		timeseries1.add(new Day(1, 1, 2010), 80.6D);
		timeseries1.add(new Day(2, 1, 2010), 15.3D);
		timeseries1.add(new Day(3, 1, 2010), 75.3D);
		timeseries1.add(new Day(4, 1, 2010), 23.6D);
		timeseries1.add(new Day(5, 1, 2010), 48.6D);
		timeseries1.add(new Day(6, 1, 2010), 16.3D);
		timeseries1.add(new Day(7, 1, 2010), 25.3D);
		timeseries1.add(new Day(8, 1, 2010), 86.0D);
		timeseries1.add(new Day(9, 1, 2010), 18.6D);
		timeseries1.add(new Day(10, 1, 2010), 32.3D);
		timeseries1.add(new Day(11, 1, 2010), 27.3D);
		timeseries1.add(new Day(12, 1, 2010), 64.5D);
		
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}
}
