/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-27 下午01:39:39
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;


/**
 * @author 徐骏
 * @data   2010-7-27
 */
public class MeterChart extends XChartPanellet
{
	private DefaultValueDataset dataset = new DefaultValueDataset(10);
	public MeterChart()
	{
		MeterPlot meterplot = new MeterPlot(dataset);
		meterplot.setRange(new Range(0.0D, 60D));
		meterplot.addInterval(new MeterInterval("安全", new Range(0.0D, 35D), Color.lightGray, new BasicStroke(2.0F), new Color(0, 255, 0, 64)));
		meterplot.addInterval(new MeterInterval("警告", new Range(35D, 50D), Color.lightGray, new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
		meterplot.addInterval(new MeterInterval("危险", new Range(50D, 60D), Color.lightGray, new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
		meterplot.setNeedlePaint(Color.darkGray);
		meterplot.setDialBackgroundPaint(Color.white);
		meterplot.setDialOutlinePaint(Color.gray);
		meterplot.setDialShape(DialShape.CHORD);
		meterplot.setMeterAngle(260);
		meterplot.setTickLabelsVisible(true);
		meterplot.setUnits("°C");//显示的数值单位
		meterplot.setTickLabelFont(new Font("微软雅黑", 1, 10));
		meterplot.setTickLabelPaint(Color.darkGray);
		meterplot.setTickSize(5D);
		meterplot.setTickPaint(Color.lightGray);
		meterplot.setValuePaint(Color.black);
		meterplot.setValueFont(new Font("微软雅黑", 1, 14));
		JFreeChart jfreechart = new JFreeChart("仪表图", JFreeChart.DEFAULT_TITLE_FONT, meterplot, true);
		setChart(jfreechart);
		new Animator().start();
	}
	class Animator extends Timer implements ActionListener
	{

		public Animator()
		{
			super(1000, null);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			dataset.setValue(Math.random() * 60);
		}
	}
}
