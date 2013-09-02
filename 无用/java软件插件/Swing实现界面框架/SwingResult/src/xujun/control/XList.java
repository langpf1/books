/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午10:57:59
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import xujun.control.shortcut.XShortcutItem;
import xujun.control.shortcut.XShortcutItemClickListenter;


/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XList extends JList
{
	private  XShortcutItemClickListenter clickListenter;
	
	public XList()
	{
		init();
	}
	public void setListenter(XShortcutItemClickListenter itemClickListenter)
	{
		this.clickListenter = itemClickListenter;
		MouseInputListener listener = new MouseInputAdapter()
		{
			//通过Move设置Item为selected，从而触发渲染器处理，实现高亮
			@Override
			public void mouseMoved(MouseEvent e)
			{
				int moveIndex = locationToIndex(e.getPoint());
				setSelectedIndex(moveIndex);
			}
			//移动出去之后，清除选中，从而触发渲染器
			@Override
			public void mouseExited(MouseEvent e)
			{
				getSelectionModel().clearSelection();
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				//因为该JList是单选模式，所以取第一个选择的就可以了
				Object selObj = getSelectedValue();
				if(selObj != null)
				{
					XShortcutItem item = (XShortcutItem)selObj;
					if(!item.isGroup())
					{
						clickListenter.ItemClick(item.getActionCommand());
					}
				}
				//itemClick.ItemClick(actionCommand)
			}
		};
		addMouseMotionListener(listener);
		addMouseListener(listener);
	}
	private void init()
	{
		setFont(XContorlUtil.FONT_12_BOLD);
		setForeground(XContorlUtil.DEFAULT_TEXT_COLOR);
		setBackground(XContorlUtil.LIST_BACKGROUND);
		setCellRenderer(new XListRenderer(this));
		setSelectionMode(0);
	}
}
