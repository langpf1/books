/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午10:37:12
 * copyright Anymusic Ltd.
 */
package xujun.control.shortcut;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.Border;

import xujun.control.XContorlUtil;
import xujun.control.XHeader;
import xujun.control.XList;
import xujun.control.XListSplitListener;
import xujun.control.outlookpanel.XOutlookList;
import xujun.control.outlookpanel.XOutlookPanelListItem;

/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XShortcutPanel extends JPanel
{
	private XList list;
	private JPanel split;
	private XHeader header;
	private XListSplitListener splitListener;
	
	public XShortcutPanel()
	{
		super();
		list = new XList();
		split = new JPanel(new BorderLayout());
		header = new XHeader() {

			public void setShrink(boolean shrinked)
			{
				super.setShrink(shrinked);
				if (shrinked)
					split.setCursor(Cursor.getDefaultCursor());
				else
					split.setCursor(Cursor.getPredefinedCursor(10));
			}
		};
		splitListener = new XListSplitListener(header);
		init();
	}
	public void setData(XShortcutItem[] items,XShortcutItemClickListenter listenter)
	{
		list.setListData(items);
		list.setListenter(listenter);
	}
	private void init()
	{
		setLayout(new BorderLayout());
		JPanel rightInsetPane = new JPanel();
		rightInsetPane.setPreferredSize(new Dimension(2, 0));
		rightInsetPane.setBackground(XContorlUtil.LIST_BACKGROUND);
		add(rightInsetPane, "East");
		add(header, "North");
		split.setBorder(new Border() {
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
			{
				g.setColor(XContorlUtil.LIST_SPLIT_COLOR);
				g.drawLine(x, y, x, y + height);
			}

			public Insets getBorderInsets(Component c)
			{
				return new Insets(0, 1, 0, 0);
			}

			public boolean isBorderOpaque()
			{
				return true;
			}

		});
		split.setOpaque(true);
		split.setPreferredSize(new Dimension(4, 0));
		split.setBackground(XContorlUtil.LIST_BACKGROUND);
		split.setCursor(Cursor.getPredefinedCursor(10));
		split.addMouseListener(splitListener);
		split.addMouseMotionListener(splitListener);
		add(split, "West");
		add(list, "Center");
	}

	public XList getList()
	{
		return list;
	}

	public void setTitle(String title)
	{
		header.setTitle(title);
	}

	public String getTitle()
	{
		return header.getTitle();
	}

	public void setShrink(boolean shrinked)
	{
		header.setShrink(shrinked);
	}

	public boolean isShrinked()
	{
		return header.isShrinked();
	}
}
