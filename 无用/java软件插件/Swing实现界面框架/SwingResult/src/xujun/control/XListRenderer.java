/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午11:00:49
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import xujun.control.shortcut.XShortcutItem;
import xujun.control.shortcut.XShortcutPanel;


/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XListRenderer extends DefaultListCellRenderer
{
	private JPanel itemRender;
	private int separatorHeight = 30;
	//每组List的分割Group
	private JPanel separatorPanel;
	private JLabel separatorLabel;
	private XSeparator separator;
	private Color itemTextColor;
	private Color separatorTextColor;
	private Color itemSelectedBackground;
	private Color itemSelectedBorder;
	private Font separatorFont;
	
	public XListRenderer(XList list)
	{
		super();
		itemRender = new JPanel(new BorderLayout());
		separatorPanel = new JPanel() {

			public Dimension getPreferredSize()
			{
				Dimension size = super.getPreferredSize();
				return new Dimension(size.width, separatorHeight);
			}

		};
		//分割Group的样式
		separatorLabel = new JLabel();
		separator = new XSeparator(0);
		separatorTextColor = Color.white;
		separatorFont = XContorlUtil.FONT_12_BOLD;
		
		itemTextColor = XContorlUtil.LIST_TEXT_COLOR;	
		itemSelectedBackground = new Color(199, 198, 200);
		itemSelectedBorder = new Color(163, 163, 163);	
		itemRender.setOpaque(false);
		itemRender.add(this, "Center");
		separatorPanel.setLayout(new OverlayLayout(separatorPanel));
		JPanel separatorHelpPane = new JPanel(new BorderLayout());
		separatorHelpPane.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
		separatorHelpPane.add(separator);
		separatorHelpPane.setOpaque(false);
		separatorPanel.setOpaque(false);
		separatorLabel.setOpaque(true);
		separatorLabel.setBackground(XContorlUtil.LIST_BACKGROUND);
		separatorLabel.setForeground(separatorTextColor);
		separatorLabel.setFont(separatorFont);
		separatorLabel.setVerticalAlignment(1);
		separatorLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		JPanel labelHelpPane = new JPanel(new BorderLayout());
		labelHelpPane.setBorder(BorderFactory.createEmptyBorder(6, 15, 0, 0));
		labelHelpPane.add(separatorLabel, "West");
		labelHelpPane.setOpaque(false);
		separatorPanel.add(labelHelpPane);
		separatorPanel.add(separatorHelpPane);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		XShortcutItem shortcutItem = (XShortcutItem)value;
		//Group的渲染
		if(shortcutItem.isGroup())
		{
			String groupName = shortcutItem.getText();
			separatorLabel.setText(groupName);
			separatorPanel.setToolTipText(groupName);
			if (list.getParent() instanceof XShortcutPanel)
			{
				XShortcutPanel pane = (XShortcutPanel)list.getParent();
				if (pane.isShrinked())
				{
					separatorLabel.setText(" ");
					separatorLabel.setOpaque(false);
				} else
				{
					separatorLabel.setOpaque(true);
				}
			}
			return separatorPanel;
		}
		//普通Item的渲染
		else 
		{
			setText(shortcutItem.getText());
			setBackground(XContorlUtil.LIST_BACKGROUND);
			setToolTipText(shortcutItem.getToolTip());
			setIcon(shortcutItem.getIcon());
		}
	
		//判断List的父容器是否是快捷菜单面板，如果是，需要考虑收缩展开的处理
		if (list.getParent() instanceof XShortcutPanel)
		{
			XShortcutPanel pane = (XShortcutPanel)list.getParent();
			if (pane.isShrinked())
			{
				setBorder(BorderFactory.createEmptyBorder(2, 7, 1, 2));
				setText("");
			} else
			{
				setBorder(BorderFactory.createEmptyBorder(2, 20, 1, 2));
			}
		}
		if (isSelected)
		{
			setBackground(itemSelectedBackground);
			itemRender.setBorder(BorderFactory.createLineBorder(itemSelectedBorder));
		} 
		else
		{
			itemRender.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		}
		setForeground(itemTextColor);
		
		return itemRender;
	}

}
