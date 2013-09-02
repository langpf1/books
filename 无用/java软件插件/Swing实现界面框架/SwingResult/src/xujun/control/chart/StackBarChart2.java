/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-26 下午01:35:55
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/**
 * @author 徐骏
 * @data   2010-7-26
 */
public class StackBarChart2 extends XChartPanellet
{
	public StackBarChart2()
	{
		JFreeChart jfreechart = ChartFactory.createStackedBarChart("累积断开图", "", "GDP", getDataset(), PlotOrientation.HORIZONTAL, false, true, false);
		//jfreechart.getTitle().setMargin(2D, 0.0D, 0.0D, 0.0D);
		/*
		TextTitle texttitle = new TextTitle("Tilte1", new Font("Dialog", 0, 11));
		texttitle.setPosition(RectangleEdge.TOP);
		texttitle.setHorizontalAlignment(HorizontalAlignment.LEFT);
		texttitle.setMargin(0.0D, 0.0D, 4D, 4D);
		jfreechart.addSubtitle(texttitle);
		*/
		
		//定制LegendItem
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		LegendItemCollection legenditemcollection = new LegendItemCollection();
		legenditemcollection.add(new LegendItem("Against all torture", null, null, null, new java.awt.geom.Rectangle2D.Double(-6D, -3D, 12D, 6D), Color.green));
		legenditemcollection.add(new LegendItem("Some degree permissible", null, null, null, new java.awt.geom.Rectangle2D.Double(-6D, -3D, 12D, 6D), Color.red));
		categoryplot.setFixedLegendItems(legenditemcollection);
		categoryplot.setInsets(new RectangleInsets(5D, 5D, 5D, 20D));
		LegendTitle legendtitle = new LegendTitle(categoryplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(legendtitle);
		
		categoryplot.setDomainGridlinesVisible(true);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setUpperMargin(0.0D);
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		Color color = new Color(0, 0, 0, 0);
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, color);
		barrenderer.setSeriesPaint(2, gradientpaint1);
		
		setChart(jfreechart);
	}
	private static CategoryDataset getDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(81D, "Against all torture", "Italy");
		defaultcategorydataset.addValue(72D, "Against all torture", "Britain");
		defaultcategorydataset.addValue(58D, "Against all torture", "USA");
		defaultcategorydataset.addValue(48D, "Against all torture", "Israel");
		defaultcategorydataset.addValue(43D, "Against all torture", "Russia");
		defaultcategorydataset.addValue(23D, "Against all torture", "India");
		defaultcategorydataset.addValue(59D, "Against all torture", "Average (*)");
		
        defaultcategorydataset.addValue(5D, "-", "Italy");
		defaultcategorydataset.addValue(4D, "-", "Britain");
		defaultcategorydataset.addValue(6D, "-", "USA");
		defaultcategorydataset.addValue(9D, "-", "Israel");
		defaultcategorydataset.addValue(20D, "-", "Russia");
		defaultcategorydataset.addValue(45D, "-", "India");
		defaultcategorydataset.addValue(12D, "-", "Average (*)");
               
		defaultcategorydataset.addValue(14D, "Some degree permissible", "Italy");
		defaultcategorydataset.addValue(24D, "Some degree permissible", "Britain");
		defaultcategorydataset.addValue(36D, "Some degree permissible", "USA");
		defaultcategorydataset.addValue(43D, "Some degree permissible", "Israel");
		defaultcategorydataset.addValue(37D, "Some degree permissible", "Russia");
		defaultcategorydataset.addValue(32D, "Some degree permissible", "India");
		defaultcategorydataset.addValue(29D, "Some degree permissible", "Average (*)");
		return defaultcategorydataset;
	}

}
