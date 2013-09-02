/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-29 上午10:23:10
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import xujun.control.XContorlUtil;

/**
 * 圆形Node的渲染器
 * @author 徐骏
 * @data   2010-7-29
 */
public class CircleNodeRenderer implements NodeRenderer
{
	private Color fillColor1;
	private Color fillColor2;

	private Paint paint;//每个Renderer只有一个Paint对象，不重复创建
	
	public CircleNodeRenderer(Color fillColor1,Color fillColor2)
	{
		this.fillColor1 = fillColor1;
		this.fillColor2 = fillColor2;
	}
	
	@Override
	public void drawEntity(Graphics g,NodeEntity nodeEntity)
	{
		Graphics2D g2 = (Graphics2D)g;
		Rectangle2D bound = nodeEntity.getArea().getBounds2D();
		paint = new GradientPaint((float)bound.getX(),(float)bound.getY(),fillColor1,(float)bound.getMaxX(),(float)bound.getMinY(),fillColor2);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(paint);
		g2.fill(nodeEntity.getArea());
		if(nodeEntity.isSelected())
		{
			g2.setPaint(XContorlUtil.getColor("FFAD00"));
			g2.setStroke(new BasicStroke(3.5f));
			g2.drawOval((int)bound.getX()-2, (int)bound.getY()-2, (int)bound.getWidth()+3, (int)bound.getHeight()+3);
		}
	}


	public Color getFillColor1()
	{
		return fillColor1;
	}
	public void setFillColor1(Color fillColor1)
	{
		this.fillColor1 = fillColor1;
	}
	public Color getFillColor2()
	{
		return fillColor2;
	}
	public void setFillColor2(Color fillColor2)
	{
		this.fillColor2 = fillColor2;
	}

	
}
