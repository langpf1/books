/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-8-3 上午10:01:35
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JLayeredPane;
import xujun.control.XContorlUtil;

/**
 * @author 徐骏
 * @data   2010-8-3
 */
public class MapPanel extends JLayeredPane
{
	private Map map;
	private MapControlPanel controlPanel;
	
	public MapPanel()
	{
		createMap();
		map.addMapMouseListener(new MapMouseListener(){

			@Override
			public void MapMouseClicked(MapMouseEvent event)
			{
				NodeEntity node = (NodeEntity)event.getSource();
				if(node != null)
				{
					System.out.println(node.getNodeText());
				}
			}
		});
		controlPanel = new MapControlPanel("控制面板",map);
		add(map, 1);
		add(controlPanel,0);
	}
	/* (non-Javadoc)
	 * @see javax.swing.JLayeredPane#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g)
	{
		map.setBounds(0, 0, getWidth(), getHeight());//layerPanel又不能指定BorderLayout布局管理，只能手工指定内容面板的大小,internalFrame因为可以pack(),不用指定
		super.paint(g);
	}
	private void createMap()
	{
		NodeEntityCollection collection = new NodeEntityCollection();
		collection.add(new CircleNode(0.11f,0.15f,30,"门店","门店1"));
		collection.add(new CircleNode(0.09f,0.28f,25,"门店","门店5"));
		collection.add(new CircleNode(0.19f,0.3f,20,"门店","门店2"));
		collection.add(new CircleNode(0.3f,0.35f,15,"门店","门店3"));
		collection.add(new CircleNode(0.32f,0.48f,15,"门店","门店4"));
		
		collection.add(new CircleNode(0.42f,0.3f,30,"仓库","王家门"));
		collection.add(new CircleNode(0.28f,0.18f,40,"仓库","随家仓"));
		collection.add(new CircleNode(0.46f,0.495f,35,"仓库","中华门"));
		
		map = new Map(collection);
		map.setNodeRenderer("门店", new CircleNodeRenderer(XContorlUtil.getColor("FFBF00"),XContorlUtil.getColor("FFD7A8")));
		map.setNodeRenderer("仓库", new CircleNodeRenderer(XContorlUtil.getColor("FF0052"),XContorlUtil.getColor("FFD5DF")));
		map.setBackgroundImage(XContorlUtil.getImage("xujun/control/images/map/usa.gif"));
	}
	public Map getMap()
	{
		return map;
	}
	public MapControlPanel getControlPanel()
	{
		return controlPanel;
	}
}
