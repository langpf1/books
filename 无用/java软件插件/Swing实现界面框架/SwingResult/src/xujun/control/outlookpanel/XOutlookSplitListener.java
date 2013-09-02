/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午10:04:56
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import xujun.control.XHeader;
import xujun.control.XListSplitListener;

/**
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookSplitListener extends XListSplitListener
{
	public XOutlookSplitListener(XHeader header)
	{
		super(header);
	}

	public void mouseDragged(MouseEvent e)
	{
		if (!header.isShrinked() && lastPoint != null)
		{
			JComponent parent = (JComponent)header.getParent();
			Dimension size = parent.getPreferredSize();
			Point thisPoint = e.getPoint();
			int xMovement = thisPoint.x - lastPoint.x;
			size.width += xMovement;
			size.width = Math.max(size.width, 37);
			parent.setPreferredSize(size);
			header.revalidateParent();
		}
	}
}
