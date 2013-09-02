/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午01:07:45
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import xujun.control.XContorlUtil;

/**
 * @author 徐骏
 * @data   2010-7-28
 */
public class MapToolBar extends JToolBar implements ActionListener
{
	private MapPanel mapPanel;
	public MapToolBar(MapPanel panel)
	{
		this.mapPanel = panel;
		setRollover(true);//鼠标悬停效果
		setFloatable(false);//不能拖动
		ButtonGroup btnGroup = new ButtonGroup();//放置互斥按钮
		//选择
		AbstractButton selBtn = createButton("xujun/control/images/map/select.png",true,"选点");
		selBtn.setActionCommand("Select");
		selBtn.addActionListener(this);
		btnGroup.add(selBtn);
		add(selBtn);
		
		//移动
		AbstractButton panBtn = createButton("xujun/control/images/map/pan.png",true,"移动");
		panBtn.setActionCommand("Pan");
		panBtn.addActionListener(this);
		btnGroup.add(panBtn);
		add(panBtn);
		
		//控制面板显示
		AbstractButton controlPanelBtn = createButton("xujun/control/images/map/controlpanel.png", false,"控制面板");
		controlPanelBtn.setActionCommand("ControlPanel");
		controlPanelBtn.addActionListener(this);
		add(controlPanelBtn);
		
		//放大 缩小
		AbstractButton zoomInBtn = createButton("xujun/control/images/map/zoomIn.png",false,"放大");
		zoomInBtn.setActionCommand("ZoomIn");
		zoomInBtn.addActionListener(this);
		AbstractButton zoomOutBtn = createButton("xujun/control/images/map/zoomOut.png",false,"缩小");
		zoomOutBtn.setActionCommand("ZoomOut");
		zoomOutBtn.addActionListener(this);
		add(zoomInBtn);
		add(zoomOutBtn);
		//原始大小
		AbstractButton zoomResetBtn = createButton("xujun/control/images/map/zoomReset.png",false,"原始尺寸");
		zoomResetBtn.setActionCommand("ZoomRest");
		zoomResetBtn.addActionListener(this);
		add(zoomResetBtn);
		//屏幕自适应
		AbstractButton zoomToOverviewBtn = createButton("xujun/control/images/map/zoomToOverview.png",false,"屏幕自适应");
		zoomToOverviewBtn.setActionCommand("ZoomOverview");
		zoomToOverviewBtn.addActionListener(this);
		add(zoomToOverviewBtn);
		//全屏
		AbstractButton fullScreenBtn = createButton("xujun/control/images/map/fullScreen.png",false,"全屏");
		add(fullScreenBtn);

		
	}
	//按钮,可能是JToggleButton状态按钮，也可能是普通的JButton
	private AbstractButton createButton(String icon,boolean isJToggleButton,String tooltip)
	{
		AbstractButton button;

		if(isJToggleButton)
		{
			button = new JToggleButton(XContorlUtil.getImageIcon(icon));
		}
		else
		{
			button = new JButton(XContorlUtil.getImageIcon(icon));
		}
		button.setMargin(new Insets(0,0,0,0));
		button.setToolTipText(tooltip);
		return button;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if(command.equals("Select"))
		{
			mapPanel.getMap().select();
		}
		if(command.equals("Pan"))
		{
			mapPanel.getMap().pan();
		}
		if(command.equals("ZoomIn"))
		{
			mapPanel.getMap().zoomIn();
		}
		if(command.equals("ZoomOut"))
		{
			mapPanel.getMap().zoomOut();
		}
		if(command.equals("ZoomRest"))
		{
			mapPanel.getMap().zoomRest();
		}
		if(command.equals("ZoomOverview"))
		{
			mapPanel.getMap().zoomOverview();
		}
		if(command.equals("ControlPanel"))
		{
			mapPanel.getControlPanel().setVisible(!mapPanel.getControlPanel().isVisible());
		}
	}
}
