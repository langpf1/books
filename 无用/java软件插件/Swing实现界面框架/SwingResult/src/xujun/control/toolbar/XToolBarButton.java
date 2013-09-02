/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午10:21:28
 * copyright Anymusic Ltd.
 */
package xujun.control.toolbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;
import xujun.control.XContorlUtil;

/**
 * @author 徐骏
 * @data   2010-7-2
 */
public class XToolBarButton extends JButton
{
	private int buttonSize;
	private Color roverBorderColor;
	private Border roverBorder;
	private Border emptyBorder;

	public XToolBarButton()
	{
		super();
		buttonSize = 20;
		roverBorderColor = XContorlUtil.BUTTON_ROVER_COLOR;
		roverBorder = new Border() {

			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
			{
				g.setColor(roverBorderColor);
				g.drawRect(x, y, width - 1, height - 1);
			}

			public Insets getBorderInsets(Component c)
			{
				return new Insets(1, 1, 1, 1);
			}

			public boolean isBorderOpaque()
			{
				return true;
			}
		};
		emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		init();
	}

	private void init()
	{
		setVerticalAlignment(0);
		setFont(XContorlUtil.FONT_12_BOLD);
		setOpaque(false);
		setBorder(emptyBorder);
		setContentAreaFilled(false);
		setFocusPainted(false);
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				setBorder(roverBorder);
			}

			public void mouseExited(MouseEvent e)
			{
				setBorder(emptyBorder);
			}
		});
	}

	@Override
	public void setIcon(Icon icon)
	{
		super.setIcon(icon);
		if (icon == null)
		{
			setPressedIcon(null);
			setRolloverIcon(null);
		} else
		{
			Icon pressedIcon = XContorlUtil.createMovedIcon(icon);
			setPressedIcon(pressedIcon);
		}
	}


	@Override
	public Dimension getPreferredSize()
	{
		int width = super.getPreferredSize().width;
		width = Math.max(width, buttonSize);
		int height = buttonSize;
		return new Dimension(width, height);
	}
}
