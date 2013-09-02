/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 上午10:57:10
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import xujun.control.XContorlUtil;

/**
 * Map 图片放大原理：Graphic的drawImage的自动缩放功能，只需要计算出在图片上截取的范围，就可以了
 * 
 * @author 徐骏
 * @data 2010-7-28
 */
public class Map extends JPanel implements MouseListener, MouseMotionListener
{
	private EventListenerList mapMouseListenerList; // 监听器列表
	private NodeEntityCollection nodeEntities; // 数据源
	private HashMap<String, NodeRenderer> nodeRenderers; // 渲染器集合
	private Image backgroundImage; // 背景图片

	private int imageWidth; // 图片的原始大小，然后计算出Node在图片的位置
	private int imageHeight;
	
	private int scaleImageWidht;
	private int scaleImageHeight;

	private Point viewStartPoint = new Point(0, 0);// 当前view的起点坐标，默认是(0,0)
	private Point nodeMovePoint = new Point(0,0); //Node移动的记录，和view要区分开来
	private Point moveStartPoint = new Point(0, 0);
	
	private float scale = 1.0f;

	private MapOperator mapOperator;
	
	private boolean nodeEnableMove;
	
	public Map(NodeEntityCollection dataset)
	{
		nodeRenderers = new HashMap<String, NodeRenderer>();
		mapMouseListenerList = new EventListenerList();
		this.nodeEntities = dataset;
		addMouseListener(this);// 增加鼠标监听，并将事件包装成MapMouseClick再转发
		addMouseMotionListener(this);// 增加鼠标移动监听
		
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		setMapBackground(g);
		paintEntity(g);
	}
	private void paintEntity(Graphics g)
	{
		for (NodeEntity node : nodeEntities.getEntities())
		{
			node.setInViewPoint(viewStartPoint);// 设置inViewPoint
			
			// 根据Node的layer分配渲染器
			node.setRenderer(nodeRenderers.get(node.getLayer()));
			node.paint(g);
		}
	}
	
	private void setMapBackground(Graphics g)
	{
		TexturePaint texturePaint = XContorlUtil
				.createTexturePaint("xujun/control/images/map/background.png");
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(texturePaint);
		g2.fillRect(0, 0, getWidth(), getHeight());
		if (backgroundImage != null)
		{
			//每次视图区域的四个点都要根据比例重新计算
			int w = (int)(getWidth()*scale);
			int h = (int)(getHeight()*scale);
			int dx1 = (int)(viewStartPoint.x*scale);//要×比例是因为移动的距离是针对原始图片，而视窗中的图片是有比例的
			int dy1 = (int)(viewStartPoint.y*scale);
			
			
			// 目标区域就是当前Panel的视窗，一直固定，源就是在图片上截取的范围，不断的变化
			g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), dx1,
					dy1, w+dx1, h+dy1, this);
			
		}
	}
	public void setNodeVisible(boolean visible)
	{
		for (NodeEntity node : nodeEntities.getEntities())
		{
			node.setVisible(visible);
			repaint();
		}
	}
	public void setNodeRenderer(String layer, NodeRenderer renderer)
	{
		nodeRenderers.put(layer, renderer);
	}
	public void addMapMouseListener(MapMouseListener listener)
	{
		mapMouseListenerList.add(MapMouseListener.class, listener);
	}
	public void removeMapMouseListener(MapMouseListener listener)
	{
		mapMouseListenerList.remove(MapMouseListener.class, listener);
	}
	
	
	// 设置背景图片
	public void setBackgroundImage(Image backgroundImage)
	{
		this.backgroundImage = backgroundImage;
		imageWidth = backgroundImage.getWidth(this);
		imageHeight = backgroundImage.getHeight(this);
		scaleImageWidht = imageWidth;
		scaleImageHeight = imageHeight;
		setEveryNodeInMapPoint();
	}
	private void setEveryNodeInMapPoint()
	{
		for (NodeEntity node : nodeEntities.getEntities()) {
			node.setInMapPoint(scaleImageWidht, scaleImageHeight);// 设置inMapPoint
		}
	}
	private void setEveryNodeSize()
	{
		for (NodeEntity node : nodeEntities.getEntities()) {
			node.setImageScale(scale);
		}
	}
	// 图片截取区域,放大缩小移动的过程 就是 截取区域缩放的过程
	// 放大：截取区域变小 缩小：截取区域变大
	// 放大
	public void zoomIn()
	{
		if(scale >0.22)
		{
			scale-=0.2;
			scaleImageWidht = (int)(imageWidth / scale);
			scaleImageHeight = (int)(imageHeight/scale);
			setEveryNodeInMapPoint();
			setEveryNodeSize();
			repaint();
		}
	}
	// 缩小
	public void zoomOut()
	{
		scale+=0.2;
		scaleImageWidht = (int)(imageWidth / scale);
		scaleImageHeight = (int)(imageHeight/scale);
		setEveryNodeInMapPoint();
		setEveryNodeSize();
		repaint();
	}
	//选择
	public void select()
	{
		mapOperator = MapOperator.SELECTED;
		setCursor();
	}
	//启用Node可以移动
	public void setNodeEnableMove(boolean move)
	{
		nodeEnableMove = move;
		setCursor();
	}
	//移动
	public void pan()
	{
		mapOperator = MapOperator.PAN;
		setCursor();
	}
	//图片原始大小
	public void zoomRest()
	{
		scale = 1.0f;
		scaleImageWidht = imageWidth ;
		scaleImageHeight = imageHeight;
		viewStartPoint.x = viewStartPoint.y = 0;//视图原点回复
		setEveryNodeInMapPoint();
		setEveryNodeSize();
		repaint();
	}
	//自适应面板
	public void zoomOverview()
	{
		//用窗体的当前W和H和图片的原始W和H作比例，以比例小的为准，计算出显示的W和H，当前的scale就是该比例
		float widthScale = (float)(getWidth()) / (float)(imageWidth);
		float heightScale = (float)(getHeight()) / (float)(imageHeight);
		if(widthScale > heightScale)
		{
			scale = (1.0f) /heightScale;//因为heightScale是面板/image，所以scale就是反向计算
		}
		else
		{
			scale = (1.0f) /widthScale;
		}
		scaleImageWidht = (int)(imageWidth / scale);
		scaleImageHeight = (int)(imageHeight/scale);
		viewStartPoint.x = viewStartPoint.y = 0;//视图原点回复
		setEveryNodeInMapPoint();
		setEveryNodeSize();
		repaint();
	}
	//移动
	private void panMap(int moveX,int moveY)
	{
		viewStartPoint.x -= moveX;
		viewStartPoint.y -= moveY;
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		//如果当前是SELECT操作，就执行
		if(mapOperator == MapOperator.SELECTED)
		{
			EventListener[] listeners = mapMouseListenerList
					.getListeners(MapMouseListener.class);
			if (listeners.length == 0)
			{
				return;
			}
			NodeEntity node = nodeEntities.getEntity(e.getX(), e.getY());
			// 向所有监听者发送消息
			if (node != null)
			{
				//设置Node的选中状态属性
				nodeEntities.setNodeSelected(new NodeEntity[]{node});
				repaint();
				MapMouseEvent chartEvent = new MapMouseEvent(e, node);
				for (int i = listeners.length - 1; i >= 0; i -= 1)
				{
					((MapMouseListener) listeners[i]).MapMouseClicked(chartEvent);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			moveStartPoint = e.getPoint();
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Point moveEndPoint = e.getPoint();
		int moveX = moveEndPoint.x - moveStartPoint.x;
		int moveY = moveEndPoint.y - moveStartPoint.y;
		//移动图片操作
		if(mapOperator == MapOperator.PAN)
		{
			panMap(moveX, moveY);
		}
		//移动Node必须在SELECTED状态下
		if(mapOperator == MapOperator.SELECTED && nodeEnableMove)
		{
			selectMoveNode(moveX, moveY);
		}
		repaint();
		moveStartPoint = moveEndPoint;
	}
	//Node重新定位,先计算移动，在鼠标释放的时候重新计算好node的scale
	private void selectMoveNode(int moveX,int moveY)
	{
		List<NodeEntity> selectedNodeList = nodeEntities.getSelectedNodes();//获得当前选中的Node
		for (NodeEntity selectedNode : selectedNodeList)
		{
			nodeMovePoint.x +=moveX;
			nodeMovePoint.y +=moveY;
			selectedNode.setMoveX(nodeMovePoint.x);
			selectedNode.setMoveY(nodeMovePoint.y);
		}
	}
	//在鼠标释放的时候，要注意，如果当前是移动Node，那么此时要重新计算node的sacle比例，并将move清零
	@Override
	public void mouseReleased(MouseEvent e)
	{
		List<NodeEntity> selectedNodeList = nodeEntities.getSelectedNodes();//获得当前选中的Node
		for (NodeEntity selectedNode : selectedNodeList)
		{
			//将Node原来的比例*image的size，再加上现在移动的尺寸，再重新计算比例
			float newWidth = selectedNode.getScaleX() * imageWidth + nodeMovePoint.x *scale ;
			float newHeight = selectedNode.getScaleY() * imageHeight + nodeMovePoint.y *scale;
			
			selectedNode.setScaleX(newWidth / (float)imageWidth);
			selectedNode.setScaleY(newHeight / (float)imageHeight);
		}
		
		nodeMovePoint.x = nodeMovePoint.y = 0;
		//重新计算Node在Image上的位置
		setEveryNodeInMapPoint();
	}
	
	public int getScaleImageWidht()
	{
		return scaleImageWidht;
	}
	public int getScaleImageHeight()
	{
		return scaleImageHeight;
	}
	public void setCursor()
	{
		if(mapOperator == MapOperator.PAN)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else 
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	@SuppressWarnings("unchecked")
	public EventListener[] getListeners(Class listenerType)
	{
		if (listenerType == MapMouseListener.class)
		{
			return mapMouseListenerList.getListeners(listenerType);
		}
		else
		{
			return super.getListeners(listenerType);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	
}
enum MapOperator
{
	DEFAULT,
	PAN,
	SELECTED,
	SELECTED_MOVE,
}
