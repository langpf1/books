/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-20 上午10:09:57
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 * 动态增加或删除数据集
 * @author 徐骏
 * @data   2010-7-20
 */
public class DynamicDatasetChart extends XChartPanellet
{
	private int seriesIndex = -1;
	private XYPlot xyPlot;
	public DynamicDatasetChart()
	{
		JFreeChart chart = ChartFactory.createTimeSeriesChart("增加或删除数据集", "日期", "产量", null, true, true, false);
		xyPlot = chart.getXYPlot();
		xyPlot.setNoDataMessage("没有数据");
		((DateAxis)xyPlot.getDomainAxis()).setDateFormatOverride(new SimpleDateFormat("MM-dd"));
		
		setChart(chart);
	}
	private XYDataset getDataset(String seriesKey)
	{
		TimeSeries series = new TimeSeries(seriesKey);
		Object day = new Day(1,2,2007);
		double value = 300;
		for(int i=0;i<40;i++)
		{
			series.add((RegularTimePeriod)day,value);
			day = ((RegularTimePeriod)day).next();
			value *= 1.0D + Math.random() / 100D;
		}
		return new TimeSeriesCollection(series);
	}

	@Override
	public JToolBar getCharToolBar()
	{
		JToolBar toolBar = super.getCharToolBar();
		JButton addBtn = new JButton("增加TimeSeries");
		addBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				seriesIndex++;
				xyPlot.setDataset(seriesIndex, getDataset("TimeSeries"+seriesIndex));
				xyPlot.setRenderer(seriesIndex,new StandardXYItemRenderer());
				
			}
		});
		toolBar.add(addBtn);
		JButton delBtn = new JButton("删除TimeSeries");
		delBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				xyPlot.setDataset(seriesIndex, null);
				xyPlot.setRenderer(seriesIndex,null);
				seriesIndex --;
			}
		});
		toolBar.add(delBtn);
		return toolBar;
	}
}
