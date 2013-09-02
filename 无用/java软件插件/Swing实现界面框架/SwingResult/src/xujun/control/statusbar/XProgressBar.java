/**   
 * @Title: XProgressBar.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:16:22
 * @version V1.0   
 */

package xujun.control.statusbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import xujun.control.XContorlUtil;


/**
 * @ClassName: XProgressBar
 * @Description: 自定义进度条控件，用在状态栏的内存信息显示上
 * @date 2010-7-6 下午10:16:22
 * 
 */
public class XProgressBar extends JProgressBar
{
	private ImageIcon selectedBackgroundImageIcon;
	private Image selectedBackgroundLeft;
	private Image selectedBackgroundRight;
	private TexturePaint selectedPaint;
	private TexturePaint unselectedPaint;
	private Color selectedBorderColor;
	private Color unselectedBorderColor;
	private int roundArc;
	private Font font;

	public XProgressBar()
	{
		this(0, 0, 100);
	}

	public XProgressBar(int min, int max)
	{
		this(0, min, max);
	}

	public XProgressBar(int value, int min, int max)
	{
		super(min, max);

		selectedBackgroundImageIcon = XContorlUtil.getImageIcon("xujun/control/images/progress_select_background.png");
		selectedBackgroundLeft = XContorlUtil.getImage("xujun/control/images/progress_selected_left.png");
		selectedBackgroundRight = XContorlUtil.getImage("xujun/control/images/progress_selected_right.png");
		selectedPaint = XContorlUtil.createTexturePaint("xujun/control/images/progress_select_background.png");
		unselectedPaint = XContorlUtil.createTexturePaint("xujun/control/images/progress_unselect_background.png");
		selectedBorderColor = new Color(233, 145, 17);
		unselectedBorderColor = new Color(105, 166, 44);
		roundArc = 6;
		font = XContorlUtil.FONT_12_BOLD;
		setValue(value);
		init();
	}

	private void init()
	{
		setOpaque(false);
		setBackground(Color.white);
		setBorder(null);
		setStringPainted(true);
		setFont(font);
		setForeground(XContorlUtil.DEFAULT_TEXT_COLOR);
		addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e)
			{
				updateString();
			}

		});
		updateString();
	}

	private void updateString()
	{
		int percent = (getValue() * 100) / getMaximum();
		setString((new StringBuilder()).append(percent).append("%, ").append(getValue()).append("M/").append(getMaximum()).append("M").toString());
	}

	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		Object oldHint = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape allShape = getAllShape();
		paintShape(g2d, allShape, allShape, unselectedPaint, unselectedBorderColor, null, null);
		Shape selectedShape = getSelectedShape();
		paintShape(g2d, allShape, selectedShape, selectedPaint, selectedBorderColor, selectedBackgroundLeft, selectedBackgroundRight);
		g2d.setFont(getFont());
		g2d.setColor(XContorlUtil.DEFAULT_TEXT_COLOR);
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(getString(), g);
		int x = (int)(((double)getWidth() - bounds.getWidth()) / 2D);
		int y = (int)(((double)getHeight() - bounds.getHeight()) / 2D) + g2d.getFontMetrics().getAscent() + 1;
		g2d.drawString(getString(), x, y);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldHint);
	}

	private void paintShape(Graphics2D g2d, Shape shape, Shape clip, Paint fillPaint, Color borderColor, Image leftImage, Image rightImage)
	{
		Shape oldClip = g2d.getClip();
		if (clip != null)
			g2d.setClip(clip);
		g2d.setPaint(fillPaint);
		g2d.fill(shape);
		if (leftImage != null)
			g2d.drawImage(leftImage, 0, 0, null);
		if (rightImage != null)
		{
			int x = getWidth() - rightImage.getWidth(null);
			int y = 0;
			g2d.drawImage(rightImage, x, y, this);
		}
		g2d.setClip(oldClip);
		g2d.setColor(borderColor);
	//	g2d.setStroke(TWaverConst.BASIC_STROKE);
		g2d.draw(clip);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, selectedBackgroundImageIcon.getIconHeight());
	}

	private Shape getSelectedShape()
	{
		Shape round = getAllShape();
		double percent = (double)getValue() / (double)getMaximum();
		int x = (int)((double)getWidth() * percent);
		int y = 0;
		int width = getWidth() - x;
		int height = getHeight() - 1;
		Rectangle unselectedRect = new Rectangle(x, y, width, height);
		Area selectedShape = new Area(round);
		selectedShape.subtract(new Area(unselectedRect));
		return selectedShape;
	}

	private Shape getAllShape()
	{
		int width = getWidth() - 1;
		int height = getHeight() - 1;
		return new java.awt.geom.RoundRectangle2D.Float(0.0F, 0.0F, width, height, roundArc, roundArc);
	}

}
