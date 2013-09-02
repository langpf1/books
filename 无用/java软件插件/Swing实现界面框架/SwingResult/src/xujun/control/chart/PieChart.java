/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-26 下午04:21:08
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import xujun.control.XContorlUtil;

/**
 * @author 徐骏
 * @data   2010-7-26
 */
public class PieChart extends XChartPanellet
{
	public PieChart()
	{
		JFreeChart chart = ChartFactory.createPieChart3D("3D饼图", getDataset(), true, true, false);
		PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();
		pieplot3d.setStartAngle(270D);
		pieplot3d.setDirection(Rotation.CLOCKWISE);
	
		NumberFormat numberFormat =  NumberFormat.getNumberInstance();
		numberFormat.setGroupingUsed(false);//取消千分位(,)

		pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}数值:{1},百分比:{2}",numberFormat,NumberFormat.getPercentInstance()));
		pieplot3d.setLabelFont(XContorlUtil.CHART_AXIS_FONT);
		pieplot3d.setForegroundAlpha(0.6F);
		Rotator rotator = new Rotator(pieplot3d);
		rotator.start();
		setChart(chart);
	}
	private PieDataset getDataset()
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("China", 1320.5);
		dataset.setValue("Japan", 2532.9);
		dataset.setValue("Americ", 3325.5);
		return dataset;
	}
	class Rotator extends Timer implements ActionListener
	{
		private PiePlot3D plot;
		private int angle;
		Rotator(PiePlot3D pieplot3d)
		{
			super(100, null);
			angle = 270;
			plot = pieplot3d;
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent actionevent)
		{
			plot.setStartAngle(angle);
			angle = angle + 1;
			if (angle == 360)
				angle = 0;
		}
	}
}
