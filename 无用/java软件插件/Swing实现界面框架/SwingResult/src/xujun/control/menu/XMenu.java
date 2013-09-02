/**   
 * @Title: XMenu.java
 * @Package xujun.control
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-1 下午06:59:26
 * @version V1.0   
 */

package xujun.control.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.border.Border;
import xujun.control.XContorlUtil;



/**
 * @ClassName: XMenu
 * @Description: TODO(菜单)
 * @date 2010-7-1 下午06:59:26
 * 
 */
public class XMenu extends JMenu
{
	private Color backgroundColor;
	private Color foregroundColor;
	private int borderThickness;
	private Border border;
	private int preferredHeight;

	public XMenu()
	{
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 25;
		init();
	}

	public XMenu(String text)
	{
		super(text);
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 25;
		init();
	}

	private void init()
	{
		setForeground(foregroundColor);
		setFont(XContorlUtil.FONT_14_BOLD);
		setOpaque(true);
		setBackground(backgroundColor);
		setBorder(border);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		if (isSelected())
		{
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(XContorlUtil.MENUITEM_SELECTED_BACKGROUND);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			super.paintComponent(g);
		} else
		{
			super.paintComponent(g);
		}
	}
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, preferredHeight);
	}
}
