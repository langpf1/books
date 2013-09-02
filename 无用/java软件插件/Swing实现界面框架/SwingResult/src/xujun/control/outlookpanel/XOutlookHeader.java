/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午10:03:22
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import xujun.control.XContorlUtil;
import xujun.control.XHeader;
import xujun.control.XListSplitListener;
import xujun.control.toolbar.XToolBarButton;

/**
 * OutLook面板的顶头的快捷工具栏目，并负责缩进效果
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookHeader extends XHeader
{
	private LayoutManager toolbarLayout;
	private JPanel toolbar;
	private ImageIcon separatorIcon;

	public XOutlookHeader(ActionListener listener)
	{
		toolbarLayout = new FlowLayout(FlowLayout.LEFT,2,1);
		toolbar = new JPanel(toolbarLayout);
		separatorIcon = XContorlUtil.getImageIcon("xujun/control/images/toolbar_separator.png");
		init();
		//加载工具栏
		XContorlUtil.loadOutlookToolBar("xujun/control/toolbar.xml", this,listener);
	}

	private void init()
	{
		toolbar.setOpaque(false);
		toolbar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		add(toolbar, "Center");
	}
	//在Header上添加工具按钮
	public XToolBarButton addButton(Icon icon, String tooltip, ActionListener action, String command)
	{
		XToolBarButton button = new XToolBarButton();
		button.setIcon(icon);
		button.setToolTipText(tooltip);
		if (action != null)
			button.addActionListener(action);
		button.setActionCommand(command);
		toolbar.add(button);
		return button;
	}

	public void addSeparator()
	{
		toolbar.add(new JLabel(separatorIcon));
	}

	protected Object getResizeHandlerLayoutConstraint()
	{
		return "East";
	}

	protected Object getShrinkHandlerLayoutConstraint()
	{
		return "West";
	}

	protected XListSplitListener createSplitListener()
	{
		return new XOutlookSplitListener(this);
	}

	protected Border createBorder()
	{
		return BorderFactory.createEmptyBorder(4, 0, 0, 7);
	}

	protected ImageIcon getShrinkIcon(boolean shrinked)
	{
		if (shrinked)
			return RIGHT_ARROW_ICON;
		else
			return LEFT_ARROW_ICON;
	}

	protected JComponent getCenterComponent()
	{
		return null;
	}

	public void setShrink(boolean shrinked)
	{
		super.setShrink(shrinked);
		toolbar.setVisible(!shrinked);
	}

	protected int getShrinkedWidth()
	{
		return 37;
	}

	public JPanel getToolBar()
	{
		return toolbar;
	}
}
