/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午11:22:21
 * copyright Anymusic Ltd.
 */
package xujun.control.shortcut;

import xujun.control.outlookpanel.XOutlookPanelListItem;

/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XShortcutItem extends XOutlookPanelListItem
{
	private boolean isGroup;


	public void setGroup(boolean isGroup)
	{
		this.isGroup = isGroup;
	}

	public boolean isGroup()
	{
		return isGroup;
	}
}
