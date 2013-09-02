/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-12 下午03:34:53
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Dataset;
import org.jfree.ui.RectangleInsets;
import com.jgoodies.looks.BorderStyle;

import xujun.control.XContorlUtil;

/**
 * 图形面板，解决JFreeChart的中文问题
 * @author 徐骏
 * @data   2010-7-12
 */
public class XChartPanellet extends JPanel implements Runnable
{
	private JFreeChart chart;
	private ChartPanel chartPanel;
	//记录下原始的大小，以便缩放还原按钮使用
	private RectangleInsets originalPadding;

	public XChartPanellet()
	{}
	protected void addChartMouseListener(ChartMouseListener listener)
	{
		chartPanel.addChartMouseListener(listener);
	}
	public void setChart(JFreeChart jfreechart)
	{
		chart = jfreechart;
		//chart.getPlot().setBackgroundAlpha(0.2f);
		//设置中文字体
		chart.getTitle().setFont(XContorlUtil.CHART_TITLE_FONT);
		if(chart.getLegend() != null)
		{
			chart.getLegend().setItemFont(XContorlUtil.CHART_LEGEND_FONT);
		}
		//设置坐标字体 
		//JFreeChart没有在Plot基类中提供对Font的设置，所以这里都要转型，有点麻烦，但为了保持对jfreechart的版本兼容，不推荐修改源码
		if(chart.getPlot() instanceof CategoryPlot)
		{
			chart.getCategoryPlot().getDomainAxis().setLabelFont(XContorlUtil.CHART_AXIS_FONT);
			chart.getCategoryPlot().getDomainAxis().setTickLabelFont(XContorlUtil.CHART_AXIS_FONT);
			chart.getCategoryPlot().getRangeAxis().setLabelFont(XContorlUtil.CHART_AXIS_FONT);
			chart.getCategoryPlot().getRangeAxis().setTickLabelFont(XContorlUtil.CHART_AXIS_FONT);
			//设置plot区域的Ranger的网格线
			chart.getCategoryPlot().setRangeGridlinePaint(Color.GRAY);
			//设置tooptip
			//xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
			//如果要DomainGrid，需要启用Visible，默认是false的
			//chart.getCategoryPlot().setDomainGridlinesVisible(true);
			//chart.getCategoryPlot().setDomainGridlinePaint(Color.red);
		}
		else if(chart.getPlot() instanceof XYPlot)
		{
			if(chart.getXYPlot().getDomainAxis() != null)
			{
				chart.getXYPlot().getDomainAxis().setLabelFont(XContorlUtil.CHART_AXIS_FONT);
				chart.getXYPlot().getDomainAxis().setTickLabelFont(XContorlUtil.CHART_AXIS_FONT);
			}
			if(chart.getXYPlot().getRangeAxis() != null)
			{
				chart.getXYPlot().getRangeAxis().setLabelFont(XContorlUtil.CHART_AXIS_FONT);
				chart.getXYPlot().getRangeAxis().setTickLabelFont(XContorlUtil.CHART_AXIS_FONT);
			}
			//设置plot区域的Ranger的网格线,默认DomainGrid是true
			chart.getXYPlot().setRangeGridlinePaint(Color.GRAY);
		//	chart.getXYPlot().setDomainGridlinePaint(Color.GRAY);//默认不显示竖网格线，bar图自己设置
			chart.getXYPlot().setDomainPannable(true);//可移动
			chart.getXYPlot().setRangePannable(true);//可移动
		}
		//设置背景色
		chart.setBackgroundPaint(Color.WHITE);
		//chart.getPlot().setForegroundAlpha(0.5f);
		setLayout(new BorderLayout());
		chartPanel = new ChartPanel(chart){
			/*处理十字线点击事件
			@Override
			public void chartProgress(ChartProgressEvent event)
			{
				//事件类型判断
				if(event.getType() == ChartProgressEvent.DRAWING_FINISHED)
				{
					//只有XYPlot支持
					if(chart.getPlot() instanceof XYPlot)
					{
						XYPlot plot = (XYPlot)chart.getPlot();
						
						System.out.println(plot.getDomainCrosshairValue());
						System.out.println(plot.getRangeCrosshairValue());
					}
				}
				super.chartProgress(event);
			}
			*/
		};
		chartPanel.setMouseZoomable(true);

		add(chartPanel,BorderLayout.CENTER);
		add(getCharToolBar(),BorderLayout.SOUTH);
		//记录下图形原始的尺寸
		originalPadding = chart.getPadding();
	}
	public JFreeChart getChart()
	{
		return chart;
	}
	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}
	public JToolBar getCharToolBar()
	{
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);//鼠标悬停效果
		toolBar.setFloatable(false);//不能拖动
		//图形导出按钮
		JButton exportButton = createButton("xujun/control/images/chartpanel/exportToImage.png");
		exportButton.addActionListener(chartPanel);
		exportButton.setActionCommand(ChartPanel.SAVE_COMMAND);
		toolBar.add(exportButton);
		//X轴坐标放大按钮
		JButton zoomInButton = createButton("xujun/control/images/chartpanel/zoomIn.png");
		/*ChartPanel本身实现了ActionListener接口*/
		zoomInButton.setEnabled(chartPanel.isDomainZoomable());
		zoomInButton.setActionCommand(ChartPanel.ZOOM_IN_DOMAIN_COMMAND);
		zoomInButton.addActionListener(chartPanel);
		toolBar.add(zoomInButton);
		//X轴坐标缩小按钮
		JButton zoomOutButton = createButton("xujun/control/images/chartpanel/zoomOut.png");
		zoomOutButton.setEnabled(chartPanel.isDomainZoomable());
		zoomOutButton.addActionListener(chartPanel);
		zoomOutButton.setActionCommand(ChartPanel.ZOOM_OUT_DOMAIN_COMMAND);
		toolBar.add(zoomOutButton);
		//X轴还原按钮
		JButton zoomResetButton = createButton("xujun/control/images/chartpanel/zoomReset.png");
		zoomResetButton.setEnabled(chartPanel.isDomainZoomable());
		zoomResetButton.addActionListener(chartPanel);
		zoomResetButton.setActionCommand(ChartPanel.ZOOM_RESET_DOMAIN_COMMAND);
		toolBar.add(zoomResetButton);
		
		//全屏按钮
		JButton fullScreenButton = createButton("xujun/control/images/chartpanel/zoomToOverview.png");
		fullScreenButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				XChartPanel chartPanel = (XChartPanel)getParent();
				chartPanel.fullScreen(XChartPanellet.this);
			}
			
		});
		toolBar.add(fullScreenButton);
		
		//增加鼠标滚轮缩放功能
		chartPanel.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				if(e.getUnitsToScroll() == 3)
				{
					zoomChart(3);
				}
				else if(e.getUnitsToScroll() == -3)
				{
					zoomChart(-3);
				}
			}
		});
		
		return toolBar;
	}
	private void zoomChart(int unit)
	{
		chart.setPadding(new RectangleInsets(chart.getPadding().getTop()+unit,chart.getPadding().getBottom()+unit,chart.getPadding().getLeft()+unit,chart.getPadding().getRight()+unit));
	}
	private JButton createButton(String icon)
	{
		JButton button = new JButton(XContorlUtil.getImageIcon(icon));
		button.setMargin(new Insets(0,0,0,0));
		return button;
	}

	@Override
	public void run()
	{
		
	}
}
