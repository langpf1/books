/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午02:59:28
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * @author 徐骏
 * @data   2010-7-28
 */
public class MapMouseEvent extends EventObject
{

	private MouseEvent trigger;
	private NodeEntity nodeEntity;
	public MapMouseEvent(MouseEvent trigger,NodeEntity entity)
	{
		super(entity);
		this.trigger = trigger;
		this.nodeEntity = entity;
	}
	public MouseEvent getTrigger()
	{
		return trigger;
	}
	
	public void setTrigger(MouseEvent trigger)
	{
		this.trigger = trigger;
	}
	
	public NodeEntity getNodeEntity()
	{
		return nodeEntity;
	}

	public void setNodeEntity(NodeEntity nodeEntity)
	{
		this.nodeEntity = nodeEntity;
	}
	
}
