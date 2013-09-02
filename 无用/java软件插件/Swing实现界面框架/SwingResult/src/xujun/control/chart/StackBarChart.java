/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-26 上午10:38:38
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author 徐骏
 * @data   2010-7-26
 */
public class StackBarChart extends XChartPanellet
{
	private JFreeChart chart;
	public StackBarChart()
	{
		chart = ChartFactory.createStackedBarChart("累积柱图", "季度", "降雨量", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
			
		StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();
		stackedbarrenderer.setDrawBarOutline(false);
		stackedbarrenderer.setShadowVisible(false);
		stackedbarrenderer.setBaseItemLabelsVisible(true);//以下两行负责在bar上显示数值
		stackedbarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
		setChart(chart);
	}

	@Override
	public JToolBar getCharToolBar()
	{
		JToolBar toolBar = super.getCharToolBar();
		final JCheckBox chkPercent = new JCheckBox("百分比累积图");
		chkPercent.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setPercentAxis(chkPercent.isSelected());
			}
		});
		toolBar.add(chkPercent);
		return toolBar;
	}
	private void setPercentAxis(boolean isPercent)
	{
		CategoryPlot plot = chart.getCategoryPlot();
		StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)plot.getRenderer();
		stackedbarrenderer.setRenderAsPercentages(isPercent);
		NumberAxis numberaxis = (NumberAxis)plot.getRangeAxis();
		if(isPercent)
		{
			numberaxis.setLabel("降雨比例");
			numberaxis.setNumberFormatOverride(new DecimalFormat("0.0%"));
		}
		else
		{
			numberaxis.setLabel("降雨量");
			numberaxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
		}
	}
	private static CategoryDataset getDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(10, "1", "一季度");
		defaultcategorydataset.addValue(15, "2", "一季度");
		defaultcategorydataset.addValue(15, "3", "一季度");
		defaultcategorydataset.addValue(43, "1", "二季度");
		defaultcategorydataset.addValue(15, "2", "二季度");
		defaultcategorydataset.addValue(18, "3", "二季度");
		defaultcategorydataset.addValue(23, "1", "三季度");
		defaultcategorydataset.addValue(11, "2", "三季度");
		defaultcategorydataset.addValue(25, "3", "三季度");
		defaultcategorydataset.addValue(13, "1", "四季度");
		defaultcategorydataset.addValue(11, "2", "四季度");
		defaultcategorydataset.addValue(29, "3", "四季度");
		return defaultcategorydataset;
	}
}
