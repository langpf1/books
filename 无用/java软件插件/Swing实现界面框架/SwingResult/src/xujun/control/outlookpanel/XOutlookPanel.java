/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 下午02:03:57
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import layout.TableLayout;
import xujun.control.XContorlUtil;


/**
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookPanel extends JPanel
{
	//Header
	private XOutlookHeader header;
	//容器Panel
	private JPanel barPane;;
	//右边的分割滑动线条
	private JPanel split;
	private int splitWidth;
	private Color splitColor;
	private JPanel additionalPane;
	private Hashtable componentLayoutRows;
	private JPanel centerPane;
	private TableLayout barPaneLayout;
	private XOutlookSplitListener splitListener;
	private ListSelectionListener listSelectionListener;
	
	/**
	 * 构造XOutlookPanel
	 * @param headerListener Header的监听器
	 * @param listSelectionListener ListItemSelection的监听器
	 */
	public XOutlookPanel(ActionListener headerListener,ListSelectionListener listSelectionListener)
	{
		super();
		//注册Header事件
		this.header = new XOutlookHeader(headerListener){
			public void setShrink(boolean shrinked)
			{
				super.setShrink(shrinked);
				shrinkChanged(shrinked);
			}

		};
		this.listSelectionListener = listSelectionListener;
		barPaneLayout = new TableLayout();
		barPane = new JPanel(barPaneLayout);
		
		additionalPane = new JPanel(new BorderLayout());
		componentLayoutRows = new Hashtable();
		centerPane = new JPanel(new BorderLayout());

		//分割条		
		split = new JPanel();
		splitWidth = 1;
		splitColor = new Color(166, 172, 174);	
		splitListener = new XOutlookSplitListener(header);
	
		init();
	}
	private void init()
	{
		split.setPreferredSize(new Dimension(splitWidth, 0));
		split.setOpaque(true);
		split.setBackground(splitColor);
		split.setCursor(Cursor.getPredefinedCursor(10));
		split.addMouseListener(splitListener);
		split.addMouseMotionListener(splitListener);
		additionalPane.setBackground(XContorlUtil.OUTLOOK_CONTAINER_COLOR);
		additionalPane.setPreferredSize(new Dimension(0, 0));
		additionalPane.setBorder(new Border() {

			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
			{
				g.setColor(XContorlUtil.OUTLOOK_SPLIT_COLOR);
				g.drawLine(0, 0, width, 0);
			}

			public Insets getBorderInsets(Component c)
			{
				return new Insets(1, 0, 0, 0);
			}

			public boolean isBorderOpaque()
			{
				return true;
			}

		});
	
		centerPane.add(barPane, "North");
		centerPane.add(additionalPane, "Center");
		barPaneLayout.insertColumn(0, -1D);
		setLayout(new BorderLayout());
		add(header, "North");
		add(centerPane, "Center");
		add(split, "East");
	}
	public XOutlookBar addBar(String title, Icon icon, Icon selectedIcon,XOutlookPanelListItem[] listItems)
	{
		XOutlookBar bar = new XOutlookBar(this);
		XOutlookListPanel listPanel = getListPanel(listItems, bar);
		//注册JList事件
		listPanel.getOutlookList().addListSelectionListener(listSelectionListener);
		bar.setOutlookListPanel(listPanel);
		bar.setSelected(false);
		bar.setTitle(title);
		bar.setIcon(icon);
		bar.setSelectedIcon(selectedIcon);
		int rowCount = barPaneLayout.getRow().length;
		barPaneLayout.insertRow(rowCount, -2D);
		barPane.add(bar, (new StringBuilder()).append("0,").append(rowCount).toString());
		componentLayoutRows.put(bar, Integer.valueOf(rowCount));
		rowCount++;
		barPaneLayout.insertRow(rowCount, -3D);
		//加载列表
		barPane.add(listPanel,(new StringBuilder()).append("0,").append(rowCount).toString());
		componentLayoutRows.put(bar.getContentComponent(), Integer.valueOf(rowCount));
		return bar;
	}
	private XOutlookListPanel getListPanel(XOutlookPanelListItem[] listItems,XOutlookBar bar)
	{
		XOutlookList list = new XOutlookList(listItems,bar);
		XOutlookListPanel listPanel = new XOutlookListPanel(list);
		return listPanel;
	}
	public void updateLayoutConstraint(Component component, boolean selected)
	{
		int rowIndex = ((Integer)componentLayoutRows.get(component)).intValue();
		double constraint = -1D;
		if (!selected)
			constraint = -3D;
		barPaneLayout.setRow(rowIndex, constraint);
	}
	public JComponent getAdditionalPane()
	{
		return additionalPane;
	}
	public void setAdditionalPaneVisible(boolean visible)
	{
		centerPane.remove(barPane);
		centerPane.remove(additionalPane);
		if (visible)
		{
			centerPane.add(barPane, "North");
			centerPane.add(additionalPane, "Center");
		} else
		{
			centerPane.add(barPane, "Center");
		}
	}
	public void closeAllBars()
	{
		for (int i = 0; i < barPane.getComponentCount(); i++)
		{
			Component c = barPane.getComponent(i);
			if (!(c instanceof XOutlookBar))
				continue;
			XOutlookBar bar = (XOutlookBar)c;
			if (bar.isSelected())
				bar.setSelected(false);
		}
	}
	public XOutlookBar getSelectedBar()
	{
		for (int i = 0; i < barPane.getComponentCount(); i++)
		{
			Component c = barPane.getComponent(i);
			if (!(c instanceof XOutlookBar))
				continue;
			XOutlookBar bar = (XOutlookBar)c;
			if (bar.isSelected())
				return bar;
		}

		return null;
	}
	public void setShrink(boolean shrinked)
	{
		header.setShrink(shrinked);
	}

	public boolean isShrinked()
	{
		return header.isShrinked();
	}
	private void shrinkChanged(boolean shrinked)
	{
		if (shrinked)
			split.setCursor(Cursor.getDefaultCursor());
		else
			split.setCursor(Cursor.getPredefinedCursor(10));
		for (int i = 0; i < barPane.getComponentCount(); i++)
		{
			Component c = barPane.getComponent(i);
			if (c instanceof XOutlookBar)
			{
				XOutlookBar bar = (XOutlookBar)c;
				bar.headerShrinkChanged(shrinked);
				
				
				//XOutlookList list = bar.getList();
				
				//list.firePropertyChange("layoutOrientation", true, false);
			}
		}
	}
}
