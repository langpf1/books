/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-5 下午02:38:24
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

/**
 * @author 徐骏
 * @data   2010-7-5
 */
public class XOutlookListPanel extends JPanel
{
	private XOutlookList list;
	
	//XOutlookBar对应的XOutlookList封装的Panel
	private JScrollPane listScrollPane;
	private Border scrollBorder;
	
	public XOutlookListPanel(XOutlookList list)
	{
		this.list = list;
		listScrollPane = new JScrollPane(list);
	//	listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		listScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBorder = new Border()
		{
			public void paintBorder(Component c, Graphics g, int x, int y,
					int width, int height)
			{
				g.setColor(new Color(233, 223, 207));
				g.drawLine(0, height, x, height);
			}
			public Insets getBorderInsets(Component c)
			{
				return new Insets(0, 0, 1, 0);
			}
			public boolean isBorderOpaque()
			{
				return true;
			}
		};
		init();
	}
	private void init()
	{
		listScrollPane.setMinimumSize(new Dimension(0, 0));
		listScrollPane.setBorder(scrollBorder);
		this.setLayout(new BorderLayout());
		add(listScrollPane,BorderLayout.CENTER);
	}
	public XOutlookList getOutlookList()
	{
		return list;
	}
}
