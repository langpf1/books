/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 下午02:14:42
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import javax.swing.JList;

import xujun.control.XContorlUtil;

/**
 * OutlookPanel中每个模块的导航部分的JList
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookList extends JList
{
	//通过XOutlookBar获得XOutlookPanel是否sh
	private XOutlookBar bar;
	public XOutlookList(XOutlookPanelListItem[] listItems,XOutlookBar bar)
	{
		this.bar = bar;
		setListData(listItems);
		init();
	}

	private void init()
	{
		setCellRenderer(new XOutlookListRenderer());
		setFont(XContorlUtil.FONT_12_BOLD);
		setForeground(XContorlUtil.OUTLOOK_TEXT_COLOR);
		setSelectionMode(0);
	}

	public XOutlookBar getOutlookBar()
	{
		return this.bar;
	}
}
