/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午09:11:27
 * copyright Anymusic Ltd.
 */
package xujun.control.outlookpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import xujun.control.XContorlUtil;


/**
 * OutLookPanel中的每个模块的工具栏，负责每个模块的缩张
 * @author 徐骏
 * @data   2010-7-2
 */
public class XOutlookBar extends JPanel
{
	private Image backgroundSelectedLeft;
	private Image backgroundSelectedRight;
	private Image backgroundImage;
	private ImageIcon handlerIcon;
	private ImageIcon handlerSelectedIcon;
	private TexturePaint paint;
	private TexturePaint selectedPaint;
	private JLabel lbHandler;
	private Border handlerBorder;
	private Border handlerShrinkedBorder;
	private JLabel lbIcon;
	private JLabel lbTitle;
	private boolean selected;
	private Color titleColor;
	private Color selectedTitleColor;
	private MouseListener mouseListener;

	private Icon icon = null;
	private Icon selectedIcon = null;
	private XOutlookPanel outlookPanel;
	private XOutlookListPanel outlookListPanel;
	
	public XOutlookBar(XOutlookPanel panel)
	{
		super();
		backgroundSelectedLeft = XContorlUtil.getImage("xujun/control/images/outlook_bar_background_selected_left.png");
		backgroundSelectedRight = XContorlUtil.getImage("xujun/control/images/outlook_bar_background_selected_right.png");
		backgroundImage = XContorlUtil.getImage("xujun/control/images/outlook_bar_background.png");
		handlerIcon = XContorlUtil.getImageIcon("xujun/control/images/outlook_bar_handler.png");
		handlerSelectedIcon = XContorlUtil.getImageIcon("xujun/control/images/outlook_bar_handler_selected.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/outlook_bar_background.png");
		selectedPaint = XContorlUtil.createTexturePaint("xujun/control/images/outlook_bar_background_selected.png");
		lbHandler = new JLabel();
		handlerBorder = BorderFactory.createEmptyBorder(0, 0, 0, 16);
		handlerShrinkedBorder = BorderFactory.createEmptyBorder(0, 0, 0, 22);
		lbIcon = new JLabel();
		lbTitle = new JLabel();
		selected = false;
		titleColor = XContorlUtil.OUTLOOK_TEXT_COLOR;
		selectedTitleColor = Color.white;
		mouseListener = new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				if (((JComponent) e.getSource()).contains(e.getPoint()))
					changeStatus();
			}
		};
		this.outlookPanel = panel;
		
		init();
	}
	public void setOutlookListPanel(XOutlookListPanel listPanel)
	{
		this.outlookListPanel=listPanel;
	}
	private void init()
	{
		setLayout(new BorderLayout());
		lbHandler.setVerticalAlignment(0);
		lbHandler.setIcon(handlerIcon);
		lbHandler.setBorder(handlerBorder);
		add(lbHandler, "East");
		lbIcon.setVerticalAlignment(0);
		lbIcon.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
		add(lbIcon, "West");
		lbTitle.setVerticalAlignment(0);
		lbTitle.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
		lbTitle.setFont(XContorlUtil.FONT_14_BOLD);
		lbTitle.setForeground(titleColor);
		add(lbTitle, "Center");
		lbHandler.addMouseListener(mouseListener);
		lbTitle.addMouseListener(mouseListener);
		lbIcon.addMouseListener(mouseListener);		
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (isSelected())
		{
			g2d.setPaint(selectedPaint);
			if (getSelectedIcon() != null)
				lbIcon.setIcon(getSelectedIcon());
			else
				lbIcon.setIcon(getIcon());
		}
		else
		{
			g2d.setPaint(paint);
			lbIcon.setIcon(getIcon());
		}
		g2d.fillRect(0, 0, getWidth(), getHeight());
		if (isSelected())
		{
			g2d.drawImage(backgroundSelectedLeft, 0, 0, null);
			g2d.drawImage(backgroundSelectedRight, getWidth()
					- backgroundSelectedRight.getWidth(null), 0, null);
		}
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, backgroundImage
				.getHeight(null));
	}
	public void setSelected(boolean selected)
	{
		if (selected != this.selected)
		{
			if (!isSelected())
				outlookPanel.closeAllBars();
			this.selected = selected;
			if (selected)
			{
				lbHandler.setIcon(handlerSelectedIcon);
				lbTitle.setForeground(selectedTitleColor);
			}
			else
			{
				lbHandler.setIcon(handlerIcon);
				lbTitle.setForeground(titleColor);
			}
			
			outlookPanel.updateLayoutConstraint(getContentComponent(), selected);
			outlookPanel.setAdditionalPaneVisible(!selected);
			outlookPanel.revalidate();
		}
	}
	public void changeStatus()
	{
		setSelected(!isSelected());
	}
	public boolean isSelected()
	{
		return selected;
	}
	public Icon getIcon()
	{
		return icon;
	}
	public void setIcon(Icon icon)
	{
		this.icon = icon;
		updateIcon();
	}
	public Icon getSelectedIcon()
	{
		return selectedIcon;
	}
	public void setSelectedIcon(Icon selectedIcon)
	{
		this.selectedIcon = selectedIcon;
		updateIcon();
	}
	private void updateIcon()
	{
		if (selected)
			lbIcon.setIcon(selectedIcon);
		else
			lbIcon.setIcon(icon);
	}
	public void setTitle(String title)
	{
		lbTitle.setText(title);
		lbTitle.setToolTipText(title);
		lbHandler.setToolTipText(title);
		lbIcon.setToolTipText(title);
	}
	public String getTitle()
	{
		return lbTitle.getText();
	}
	
	public XOutlookListPanel getContentComponent()
	{
		return outlookListPanel;
	}
	public XOutlookList getList()
	{
		return this.outlookListPanel.getOutlookList();
	}
	public XOutlookPanel getOutlookPanel()
	{
		return this.outlookPanel;
	}
	public void headerShrinkChanged(boolean headShrinked)
	{
		if (headShrinked)
			lbHandler.setBorder(handlerShrinkedBorder);
		else
			lbHandler.setBorder(handlerBorder);
	}

}
