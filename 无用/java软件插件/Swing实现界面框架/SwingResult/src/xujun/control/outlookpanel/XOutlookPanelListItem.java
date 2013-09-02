/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 下午03:18:50
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import javax.swing.Icon;

/**
 * List的数据模型，可以带Icon
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookPanelListItem
{
	private Icon icon;
	private String text;
	private String toolTip;
	private String actionCommand;
	
	public void setIcon(Icon icon)
	{
		this.icon = icon;
	}

	public Icon getIcon()
	{
		return icon;
	}
	
	public void setText(String value)
	{
		this.text = value;
	}
	
	public String getText()
	{
		return text;
	}

	public void setToolTip(String toolTip)
	{
		this.toolTip = toolTip;
	}

	public String getToolTip()
	{
		return toolTip;
	}

	public void setActionCommand(String actionCommand)
	{
		this.actionCommand = actionCommand;
	}

	public String getActionCommand()
	{
		return actionCommand;
	}
}
