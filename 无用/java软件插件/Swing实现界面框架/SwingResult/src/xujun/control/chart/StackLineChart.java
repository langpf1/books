/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-20 下午03:39:17
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 累积线图
 * @author 徐骏
 * @data   2010-7-20
 */
public class StackLineChart extends XChartPanellet
{
	public StackLineChart()
	{
		JFreeChart chart = ChartFactory.createStackedAreaChart("累积曲线图", "国家", "GDP", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot categoryplot = chart.getCategoryPlot();
		CategoryAxis domainAxis = (CategoryAxis)categoryplot.getDomainAxis();
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.0);
		setChart(chart);
	}
	//简单的分类数据集，如果是XY数据，可使用TableXYDataset
	private CategoryDataset getDataset()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(52D, "China", new Integer(2005));
		dataset.addValue(68D, "China", new Integer(2006));
		dataset.addValue(78D, "China", new Integer(2007));
		dataset.addValue(89D, "China", new Integer(2008));
		
		dataset.addValue(75D, "Americ", new Integer(2005));
		dataset.addValue(82D, "Americ", new Integer(2006));
		dataset.addValue(108D, "Americ", new Integer(2007));
		dataset.addValue(92D, "Americ", new Integer(2008));
		
		dataset.addValue(68D, "Japan", new Integer(2005));
		dataset.addValue(69D, "Japan", new Integer(2006));
		dataset.addValue(59D, "Japan", new Integer(2007));
		dataset.addValue(54D, "Japan", new Integer(2008));
		return dataset;
	}
}
