/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午01:22:13
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import xujun.control.XContorlUtil;

/**
 * @author 徐骏
 * @data   2010-7-28
 */
public class XMap extends JPanel
{
	private MapToolBar toolBar;
	private MapPanel mapPanel;

	public XMap()
	{
		setLayout(new BorderLayout());
		mapPanel = new MapPanel();
		toolBar = new MapToolBar(mapPanel);
		
		add(toolBar,BorderLayout.NORTH);
		add(mapPanel,BorderLayout.CENTER);	
	}
	public Map getMap()
	{
		return mapPanel.getMap();
	}
	
}
