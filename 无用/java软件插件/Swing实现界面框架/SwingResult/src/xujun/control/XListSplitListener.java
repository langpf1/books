/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午09:44:07
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;


/**
 * @author 徐骏
 * @data   2010-7-2
 */
public class XListSplitListener extends MouseInputAdapter
{
	protected Point lastPoint;
	protected XHeader header;

	public XListSplitListener(XHeader header)
	{
		lastPoint = null;
		this.header = null;
		this.header = header;
	}

	public void mousePressed(MouseEvent e)
	{
		if (!header.isShrinked())
			lastPoint = e.getPoint();
	}

	public void mouseReleased(MouseEvent e)
	{
		lastPoint = null;
	}

	public void mouseDragged(MouseEvent e)
	{
		if (!header.isShrinked() && lastPoint != null)
		{
			JComponent parent = (JComponent)header.getParent();
			Dimension size = parent.getPreferredSize();
			Point thisPoint = e.getPoint();
			int xMovement = thisPoint.x - lastPoint.x;
			size.width -= xMovement;
			size.width = Math.max(size.width, 37);
			parent.setPreferredSize(size);
			header.revalidateParent();
		}
	}

}
