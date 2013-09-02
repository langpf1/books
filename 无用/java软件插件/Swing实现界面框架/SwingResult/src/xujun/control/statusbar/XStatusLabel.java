/**   
 * @Title: XStatusLabel.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:09:13
 * @version V1.0   
 */

package xujun.control.statusbar;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

import xujun.control.XContorlUtil;

/**
 * @ClassName: XStatusLabel
 * @Description: 所有状态栏标签的父类
 * @date 2010-7-6 下午10:09:13
 * 
 */
public class XStatusLabel extends JLabel
{
	public XStatusLabel()
	{
		this(null, null);
	}

	public XStatusLabel(String text)
	{
		this(text, null);
	}

	public XStatusLabel(Icon icon)
	{
		this(null, icon);
	}

	public XStatusLabel(String text, Icon icon)
	{
		super(text, icon, 10);
		init();
	}

	protected void init()
	{
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		setFont(XContorlUtil.FONT_12_BOLD);
		setForeground(XContorlUtil.DEFAULT_TEXT_COLOR);
		setVerticalAlignment(0);
		setVerticalTextPosition(0);
		setIconTextGap(5);
	}
}
