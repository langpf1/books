/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-15 下午03:54:05
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.TextAnchor;

/**
 * @author 徐骏
 * @data   2010-7-15
 */
public class Bar3DChart extends XChartPanellet
{
	public Bar3DChart()
	{
		//因为这里bar的颜色会根据警戒值变化，所以Legend不显示，否则会混乱
		JFreeChart jfreechart = ChartFactory.createBarChart3D("降雨量", "省份", "雨量", getDataset(), PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		CustomBarRenderer3D custombarrenderer3d = new CustomBarRenderer3D();
		custombarrenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		custombarrenderer3d.setBaseItemLabelsVisible(true);
		custombarrenderer3d.setItemLabelAnchorOffset(10D);
		custombarrenderer3d.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		categoryplot.setRenderer(custombarrenderer3d);
		//警戒线
		ValueMarker valuemarker = new ValueMarker(180.00D, new Color(200, 200, 255), new BasicStroke(1.0F),new Color(200, 200, 255), new BasicStroke(1.0F), 1.0F);
		categoryplot.addRangeMarker(valuemarker, Layer.BACKGROUND);
		custombarrenderer3d.setBaseItemLabelsVisible(true);
		custombarrenderer3d.setMaximumBarWidth(0.050000000000000003D);
		//标记 (名称，在哪个domain区域内，对应的rangAxis坐标的值是多少)
		CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation("警戒线", "江西", 181.0D);
		categorytextannotation.setCategoryAnchor(CategoryAnchor.START);
		categorytextannotation.setFont(new Font("宋体", 0, 12));
		//标记的对齐方式
		categorytextannotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);
		categoryplot.addAnnotation(categorytextannotation);
		
		//NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		//numberaxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
		//numberaxis.setUpperMargin(0.10000000000000001D);
		
		setChart(jfreechart);
	}
	private CategoryDataset getDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(255.77D, "雨量", "江苏");
		defaultcategorydataset.addValue(156.93D, "雨量", "广东");
		defaultcategorydataset.addValue(87.58D, "雨量", "湖北");
		defaultcategorydataset.addValue(123.75D, "雨量", "山东");
		defaultcategorydataset.addValue(98.63D, "雨量", "江西");
		defaultcategorydataset.addValue(45.94D, "雨量", "云南");
		defaultcategorydataset.addValue(53.40D, "雨量", "北京");
		defaultcategorydataset.addValue(210.65D, "雨量", "上海");
		return defaultcategorydataset;
	}
	class CustomBarRenderer3D extends BarRenderer3D
	{

		public Paint getItemPaint(int i, int j)
		{
			CategoryDataset categorydataset = getPlot().getDataset();
			double d = categorydataset.getValue(i, j).doubleValue();
			if (d >= 180.00D)
				return Color.RED;
			else
				return Color.GREEN;
		}

		public CustomBarRenderer3D()
		{
		}
	}
}
