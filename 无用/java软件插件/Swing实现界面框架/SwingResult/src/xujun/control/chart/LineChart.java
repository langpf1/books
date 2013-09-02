/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-14 下午04:14:15
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CyclicXYItemRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * 过程线图例
 * @author 徐骏
 * @data   2010-7-14
 */
public class LineChart extends XChartPanellet
{
	private XYSplineRenderer splineProfitRenderer = new XYSplineRenderer();
	private XYLineAndShapeRenderer profitRenderer = new XYLineAndShapeRenderer();
	private XYPlot xyPlot;
	public LineChart()
	{
		//jfreechart在多坐标时有一个bug，只有第一个dataset中的line会显示tooltip
		//多坐标图需要为每个Axis指定一个Dataset。
		JFreeChart chart = ChartFactory.createXYLineChart("多坐标曲线图", "年度", "销售额(亿元)", getSaleDataset(), PlotOrientation.VERTICAL, true, false, false);
		xyPlot  = chart.getXYPlot();
		//设置DomainAxis的Lable格式，默认会在千位加上‘，’号,设置成Integer单位。
		xyPlot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		//增加利润坐标
		NumberAxis profitAxis = new NumberAxis("利润(万元)");
		xyPlot.setRangeAxis(1, profitAxis);//index从0开始，销售额坐标为0，利润坐标就为1
		xyPlot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);//坐标位置 
		xyPlot.setDataset(1, getProfitDataset());//将Dataset增加到Plot中
		xyPlot.mapDatasetToRangeAxis(1, 1);//数据index和AXIS的index
		xyPlot.setDomainCrosshairVisible(true);//显示十字线
		xyPlot.setRangeCrosshairVisible(true);//显示十字线
		//因为是多个Dataset，要给第二个Dateset设置Renderer，注意一个Renderer对应一个Dataset
		createLineRenderer();
		createSplineRenderer();
		xyPlot.setRenderer(1, profitRenderer);
		setChart(chart);
	}
	private void createLineRenderer()
	{
		profitRenderer.setSeriesPaint(0, Color.BLUE);//为这个Renderer的第一个线设置颜色
		profitRenderer.setBaseShapesVisible(true);//显示默认的数值sharp(实心圆)
		profitRenderer.setUseFillPaint(true);
		profitRenderer.setUseOutlinePaint(true);
		profitRenderer.setSeriesOutlinePaint(0, Color.gray);	
		profitRenderer.setBaseFillPaint(Color.YELLOW);
		/*
		profitRenderer.setUseFillPaint(true);
		profitRenderer.setBaseFillPaint(Color.YELLOW);
		
		profitRenderer.setSeriesOutlinePaint(0, Color.BLACK);
		*/
	}
	private void createSplineRenderer()
	{
		
		splineProfitRenderer.setSeriesPaint(0, Color.BLUE);//为这个Renderer的第一个线设置颜色
		splineProfitRenderer.setBaseShapesVisible(true);//显示默认的数值sharp(实心圆)
		splineProfitRenderer.setUseFillPaint(true);
		splineProfitRenderer.setUseOutlinePaint(true);
		splineProfitRenderer.setSeriesOutlinePaint(0, Color.gray);	
		splineProfitRenderer.setBaseFillPaint(Color.YELLOW);
	}
	
	@Override
	public JToolBar getCharToolBar()
	{
		JToolBar toolBar = super.getCharToolBar();
		final JCheckBox splineCheckBox = new JCheckBox("曲线");
		splineCheckBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(splineCheckBox.isSelected())
				{
					xyPlot.setRenderer(1, splineProfitRenderer);
				}
				else {
					xyPlot.setRenderer(1,profitRenderer);
				}
			}
		});
		toolBar.add(splineCheckBox);
		return toolBar;
	}
	private XYDataset getSaleDataset()
	{
		//销售额数据
		XYSeries saleSeries = new XYSeries("销售额");
		saleSeries.add(2005, 0.98D);
		saleSeries.add(2006,1.23D);
		saleSeries.add(2007,1.56D);
		saleSeries.add(2008,1.11D);
		saleSeries.add(2009,2.12D);
		saleSeries.add(2010,2.15D);
		
		
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(saleSeries);
		return xySeriesCollection;
	}
	private XYDataset getProfitDataset()
	{
		//利润数据
		XYSeries profitSeries = new XYSeries("利润");
		profitSeries.add(2005, 2210);
		profitSeries.add(2006,2350);
		profitSeries.add(2007,2845);
		profitSeries.add(2008,1987);
		profitSeries.add(2009,2760);
		profitSeries.add(2010,2430);
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(profitSeries);
		return xySeriesCollection;
	}
}
