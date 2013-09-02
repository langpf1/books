/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午03:29:16
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author 徐骏
 * @data   2010-7-28
 */
public class NodeEntityCollection
{
	private List<NodeEntity> entities ;
	
	public NodeEntityCollection()
	{
		entities = new ArrayList<NodeEntity>();
	}

    public int getEntityCount() {
        return this.entities.size();
    }

    public NodeEntity getEntity(int index) {
        return (NodeEntity) this.entities.get(index);
    }

    public void clear() {
        this.entities.clear();
    }

    public void add(NodeEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Null 'entity' argument.");
        }
        this.entities.add(entity);
    }

    public void addAll(NodeEntityCollection collection) {
        this.entities.addAll(collection.getEntities());
    }

    public NodeEntity getEntity(double x, double y) {
        int entityCount = this.entities.size();
        for (int i = entityCount - 1; i >= 0; i--) {
            NodeEntity entity = this.entities.get(i);
            if (entity.getArea().contains(x, y)) {
                return entity;
            }
        }
        return null;
    }
    public void setNodeSelected(NodeEntity[] selNodes)
	{
		for (NodeEntity node : entities)
		{
			node.setSelected(false);
		}
		for(NodeEntity selNode:selNodes)
		{
			selNode.setSelected(true);
		}
	}
    public List<NodeEntity> getSelectedNodes()
    {
    	List<NodeEntity> selectedNodeList = new ArrayList<NodeEntity>();
    	for (NodeEntity node : entities)
		{
			if(node.isSelected())
			{
				selectedNodeList.add(node);
			}
		}
    	return selectedNodeList;
    }
    public Collection<NodeEntity> getEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }

    public Iterator<NodeEntity> iterator() {
        return this.entities.iterator();
    }

}
