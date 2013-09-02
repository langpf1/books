/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-6 下午03:34:21
 * copyright Anymusic Ltd.
 */
package xujun.control.toolbar;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import xujun.control.XContorlUtil;


/**
 * @author 徐骏
 * @data   2010-7-6
 */
public class XToolBarRoverButton extends XToolBarButton
{
	private Color roverDyeColor;

	public XToolBarRoverButton()
	{
		roverDyeColor = new Color(86, 146, 61);
	}

	public void setIcon(Icon icon)
	{
		super.setIcon(icon);
		if (icon == null)
		{
			setPressedIcon(null);
			setRolloverIcon(null);
		} else
		{
			java.awt.Image image = XContorlUtil.iconToImage(icon);
			Icon roverIcon = XContorlUtil.createDyedIcon(new ImageIcon(image), roverDyeColor);
			Icon pressedIcon = XContorlUtil.createMovedIcon(roverIcon);
			setRolloverIcon(roverIcon);
			setPressedIcon(pressedIcon);
		}
	}
}
