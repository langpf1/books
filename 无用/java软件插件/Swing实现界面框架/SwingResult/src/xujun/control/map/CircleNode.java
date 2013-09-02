/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-29 上午11:11:55
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * @author 徐骏
 * @data   2010-7-29
 */
public class CircleNode extends NodeEntity
{
	public CircleNode(float scaleX,float scaleY,int diameter,String layer,String nodeText)
	{
		super(scaleX,scaleY,layer,nodeText);
		this.setLayer(layer);
		setNodeWidth(diameter);
		setNodeHeight(diameter);
	}

	@Override
	public Shape getArea()
	{
		return new Ellipse2D.Double(getInViewPoint().getX(),getInViewPoint().getY(),getNodeWidth(),getNodeHeight());
	}

}
