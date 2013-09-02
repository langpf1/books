/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-27 上午10:46:20
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 * 钟表图
 * @author 徐骏
 * @data   2010-7-27
 */
public class DialChart extends XChartPanellet
{
	private DefaultValueDataset dataset = new DefaultValueDataset(0); //值
	public DialChart()
	{
		DialPlot plot = new DialPlot();
		plot.setDataset(dataset);//设值
		plot.setDialFrame(new StandardDialFrame());//设置面板，必须
		//设置背景，默认是白色的，没有背景色
		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		plot.setBackground(dialbackground);
		//下方文本
		DialTextAnnotation dialtextannotation = new DialTextAnnotation("压力");
		dialtextannotation.setFont(new Font("微软雅黑", 1, 14));
		dialtextannotation.setRadius(0.69999999999999996D);
		plot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		plot.addLayer(dialvalueindicator);
		StandardDialScale standarddialscale = new StandardDialScale(-60, 60, -120D, -300D, 10D, 4);//刻度
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("微软雅黑", 0, 14));
		plot.addScale(0, standarddialscale);
		
		DialCap dialcap = new DialCap();
		plot.setCap(dialcap);
		
		setDialRange(plot);
		//plot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());//设置指针样式，pin是根红色的线
		plot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pointer());//Pointer是粗细针
		
		JFreeChart chart = new JFreeChart("钟表压力图", plot);
		setChart(chart);
		Animator animator = new Animator();
		animator.start();
	}
	//设置仪表盘值域
	private void setDialRange(DialPlot plot)
	{
		//设置危险区域
		StandardDialRange dangerRange = new StandardDialRange(40,60,Color.RED);
		dangerRange.setInnerRadius(0.52000000000000002D);
		dangerRange.setOuterRadius(0.55000000000000004D);
		plot.addLayer(dangerRange);
		//设置警告区域
		StandardDialRange warnRange = new StandardDialRange(10, 40, Color.ORANGE);
		warnRange.setInnerRadius(0.52000000000000002D);
		warnRange.setOuterRadius(0.55000000000000004D);
		plot.addLayer(warnRange);
		//设置安全区域
		StandardDialRange securityRange = new StandardDialRange(-60, 10, Color.GREEN);
		securityRange.setInnerRadius(0.52000000000000002D);
		securityRange.setOuterRadius(0.55000000000000004D);
		plot.addLayer(securityRange);
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
			dataset.setValue(Math.random() * 60 );
		}
	}
}
