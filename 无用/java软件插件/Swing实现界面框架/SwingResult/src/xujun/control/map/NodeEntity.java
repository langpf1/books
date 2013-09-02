/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午03:04:33
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

/**
 * 所有MapNode的父类
 * @author 徐骏
 * @data   2010-7-28
 */
public class NodeEntity
{
	private Shape area;//区域范围,每个Node的area不同，不能放在renderer中
	private String nodeText;//文本
	private String tooltip;//tooltip
	private String layer; //层
	
	private boolean visible = true;//是否可见
	private boolean isSelected = false;// 是否选中
	
	private float scaleX ;//Node在X方向上占width的比例，根据该比例和width计算出Node的起点X
	private float scaleY ;//Node在Y方向上占Iheight的比例，根据该比例height计算出Node的起点Y
    private Point inMapPoint ;//热点在Map上的位置，当Map放大缩小之后,Map的widht和height都改变了，要重新计算
    private Point inViewPoint;//热点在可视范围内(Panel)的位置，用NodeInImagePoint-视图的顶点坐标，在图片拖动时，都要重新计算
    
    private int nodeWidth;//Node的尺寸,当缩放时候，可以变化
    private int nodeHeight;
    private float imageScale = 1.0f;//  当前图片的比例,用于Node的尺寸和Image的尺寸同步缩放
    
	private NodeRenderer renderer;
	
	private int moveX;//移动的距离，在绘制时候先处理，然后持久记录之后再清除
	private int moveY;

	public NodeEntity(float scaleX,float scaleY,String layer,String nodeText)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.layer = layer;
		this.nodeText = nodeText;
	}

	public void paint(Graphics g)
	{
		if(visible)
		{
			renderer.drawEntity(g,this);
		}
	}

    public void setInMapPoint (int mapWidth,int mapHeight)
    {
    	inMapPoint =  new Point((int)(mapWidth * scaleX), (int)(mapHeight * scaleY));
    	moveX = moveY = 0;//每次重新计算的时候，move是要清零的
    }
    //位置－View的size＋move的size
    public void setInViewPoint(Point viewStartPoint)
    {
    	inViewPoint = new Point(inMapPoint.x - viewStartPoint.x+moveX,inMapPoint.y - viewStartPoint.y+moveY);
    }
    public Point getInViewPoint()
    {
    	return inViewPoint;
    }

	public Shape getArea()
	{
		return area;
	}

	public void setArea(Shape area)
	{
		this.area = area;
	}

	public String getNodeText()
	{
		return nodeText;
	}
	
	public void setNodeText(String nodeText)
	{
		this.nodeText = nodeText;
	}

	public String getTooltip()
	{
		return tooltip;
	}
	
	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}
	
	public NodeRenderer getRenderer()
	{
		return renderer;
	}
	public void setRenderer(NodeRenderer renderer)
	{
		this.renderer = renderer;
	}
	public String getLayer()
	{
		return layer;
	}
	public void setLayer(String layer)
	{
		this.layer = layer;
	}
	public boolean isVisible()
	{
		return visible;
	}
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public float getScaleX()
	{
		return scaleX;
	}

	public void setScaleX(float scale)
	{
		scaleX = scale;
	}

	public float getScaleY()
	{
		return scaleY;
	}

	public void setScaleY(float scale)
	{
		scaleY = scale;
	}

	public int getNodeWidth()
	{
		return (int)(nodeWidth/imageScale);
	}

	public void setNodeWidth(int nodeWidth)
	{
		this.nodeWidth = nodeWidth;
	}

	public int getNodeHeight()
	{
		return (int)(nodeHeight/imageScale);
	}

	public void setNodeHeight(int nodeHeight)
	{
		this.nodeHeight = nodeHeight;
	}

	public float getImageScale()
	{
		return imageScale;
	}

	public void setImageScale(float imageScale)
	{
		this.imageScale = imageScale;
	}

	public boolean isSelected()
	{
		return isSelected;
	}

	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		if (!(obj instanceof NodeEntity))
		{
			return false;
		}
		NodeEntity that  = (NodeEntity)obj;
		if(that.getArea().equals(this.getArea()))
		{
			return true;
		}
		return false;
	}

	public void setMoveX(int moveX)
	{
		this.moveX = moveX;
	}

	public void setMoveY(int moveY)
	{
		this.moveY = moveY;
	}
}
