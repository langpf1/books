/**   
 * @Title: MenuItem.java
 * @Package xujun.control
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-1 下午06:43:40
 * @version V1.0   
 */

package xujun.control.menu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import xujun.control.XContorlUtil;

/**
 * @ClassName: MenuItem
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-1 下午06:43:40
 * 
 */
public class XMenuItem extends JMenuItem
{
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private Color foregroundColor;
	private int borderThickness;
	private Border border;
	private int preferredHeight;
	
	public XMenuItem()
	{
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 23;
		init();
	}
	public XMenuItem(String text)
	{
		super(text);
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 23;
		init();
	}
	private void init()
	{
		setForeground(foregroundColor);
		setFont(XContorlUtil.FONT_14_BOLD);
		setBackground(backgroundColor);
		setBorder(border);
	}
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, preferredHeight);
	}
}
