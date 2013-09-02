/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-13 下午02:17:25
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




/**
 * @author 徐骏
 * @data   2010-7-13
 */
public class XChartPanel extends JPanel implements XChartletFullScreenListenter
{
	private GridLayout layout;
	private List<JPanel> chartList;
	private boolean isFullScreen;
	
	public XChartPanel(int rows,int cols,List<JPanel> childPanels) 
	{
		this.chartList = childPanels;
		//设置JPanel为3×3的网格布局，每个Cell之间有3的间隔
		layout = new GridLayout();
		layout.setRows(rows);
		layout.setColumns(cols);
		layout.setVgap(3);
		layout.setHgap(3);
		
		setLayout(layout);
		for (int i = 0; i < chartList.size(); i++)
		{
			JPanel let = chartList.get(i);
			//将图形添加到JPanel中
			add(let);
			//给每个panellet分配一个线程
		//	Thread thread = new Thread(let);
		//	thread.start();
		}
	}
	public void initialize()
	{
		int size = chartList.size();
		setLayout(layout);
		for (int i = 0; i < size; i++)
			add(chartList.get(i));
	}


	@Override
	public void fullScreen(JPanel chartPanellet)
	{
		removeAll();
		isFullScreen = !isFullScreen;
		if (isFullScreen)
		{
			setLayout(new BorderLayout());
			add(chartPanellet, "Center");
		} else
		{
			initialize();
		}
		repaint();
		revalidate();
	}

}
