/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-6 下午03:25:32
 * copyright Anymusic Ltd.
 */
package xujun.control.toolbar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;

import xujun.control.XContorlUtil;


/**
 * 工具栏
 * @author 徐骏
 * @data   2010-7-6
 */
public class XToolBar extends JPanel
{
	private String backgroundImageURL;
	private int preferredHeight;
	private TexturePaint paint;
	private int buttonGap;

	public XToolBar()
	{
		preferredHeight = XContorlUtil.getImageIcon("xujun/control/images/toolbar_background.png").getIconHeight();
		paint = XContorlUtil.createTexturePaint("xujun/control/images/toolbar_background.png");
		buttonGap = 2;
		init();
	}

	private void init()
	{
		setLayout(new FlowLayout(3, buttonGap, 0));
		setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 5));
	}
	public void addButton(Icon icon, String tooltip,String actionCommand,boolean rover)
	{
		XToolBarButton barButton;
		if (rover)
			barButton = new XToolBarRoverButton();
		else
			barButton = new XToolBarButton();
		barButton.setIcon(icon);
		barButton.setToolTipText(tooltip);
		barButton.setActionCommand(actionCommand);
		add(barButton);
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, preferredHeight);
	}
}
