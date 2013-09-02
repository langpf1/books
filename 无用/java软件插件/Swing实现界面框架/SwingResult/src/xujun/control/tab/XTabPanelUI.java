/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-6 下午03:17:00
 * copyright Anymusic Ltd.
 */
package xujun.control.tab;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import xujun.control.XContorlUtil;


/**
 * @author 徐骏
 * @data   2010-7-6
 */
public class XTabPanelUI extends MetalTabbedPaneUI
{
	private XTabPanel tab;
	private int firstTabIndent;

	public XTabPanelUI(XTabPanel tab)
	{
		this.tab = null;
		firstTabIndent = 5;
		this.tab = tab;
	}

	protected Rectangle getTabBounds(int tabIndex, Rectangle dest)
	{
		Rectangle bounds = super.getTabBounds(tabIndex, dest);
		bounds.x += firstTabIndent;
		return bounds;
	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, 
			boolean isSelected)
	{
		g.setColor(XContorlUtil.TAB_BOTTOM_LINE_COLOR);
		int lineY = tab.getPreferredTabHeight() - 1;
		g.drawLine(0, lineY, firstTabIndent, lineY);
	}

	protected void paintTabBackground(Graphics g1, int i, int j, int k, int l, int i1, int j1, 
			boolean flag)
	{
	}

	protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
	{
		int width = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
		return width - 5;
	}

	protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		return tab.getPreferredTabHeight();
	}
}
