/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午11:02:05
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import javax.swing.JSeparator;


/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XSeparator extends JSeparator
{
	private Image image;
	private TexturePaint paint;

	public XSeparator()
	{
		image = XContorlUtil.getImage("xujun/control/images/separator_background.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/separator_background.png");
	}

	public XSeparator(int direction)
	{
		super(direction);
		image = XContorlUtil.getImage("xujun/control/images/separator_background.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/separator_background.png");
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(paint);
		int x = 0;
		int y = 0;
		int width = getWidth();
		int height = image.getHeight(null);
		g2d.fillRect(x, y, width, height);
	}
}
