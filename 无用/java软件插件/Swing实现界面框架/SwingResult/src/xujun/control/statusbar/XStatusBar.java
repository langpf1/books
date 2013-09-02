/**   
 * @Title: XStatusBar.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:00:09
 * @version V1.0   
 */

package xujun.control.statusbar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;


import xujun.control.XContorlUtil;


/**
 * @ClassName: XStatusBar
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-6 下午10:00:09
 * 
 */
public class XStatusBar extends JPanel
{
	private String backgroundImageURL;
	private Image backgroundLeftImage;
	private Image backgroundRightImage;
	private ImageIcon backgroundImageIcon;
	private TexturePaint paint;
	private JPanel leftPane;
	private JPanel rightPane;
	private Border border;
	
	public XStatusBar()
	{
		backgroundLeftImage = XContorlUtil.getImage("xujun/control/images/statusbar_background_left.png");
		backgroundRightImage = XContorlUtil.getImage("xujun/control/images/statusbar_background_right.png");
		backgroundImageIcon = XContorlUtil.getImageIcon("xujun/control/images/statusbar_background.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/statusbar_background.png");
		leftPane = new JPanel(new BorderLayout());
		rightPane = new JPanel(new FlowLayout(3, 0, 0));
		border = BorderFactory.createEmptyBorder(2, 10, 0, 0);
		init();
	}
	private void init()
	{
		setLayout(new BorderLayout());
		add(leftPane, "Center");
		add(rightPane, "East");
		setBorder(border);
		leftPane.setOpaque(false);
		rightPane.setOpaque(false);
		
		addDefaultSubLabel();
	}
	private void addDefaultSubLabel()
	{
		//消息
		leftPane.add(new XStatusMessageLabel(),BorderLayout.CENTER);
		addSeparator();
		//内存
		rightPane.add(new XMemoryBar());
		addSeparator();
		//释放内存
		rightPane.add(new XGCButton());
		addSeparator();
		//服务器地址
		rightPane.add(new XStatusLabel("10.144.84.7",XContorlUtil.getImageIcon("xujun/control/images/server.png")));
		addSeparator();
		//当前用户
		rightPane.add(new XStatusLabel("Admin",XContorlUtil.getImageIcon("xujun/control/images/toolbar/user.png")));
		addSeparator();
		//当前时间
		rightPane.add(new XStatusTimeLabel());
		addSeparator();
		//版本号
		rightPane.add(new XStatusLabel("V1.0",null));
		addSeparator();
		//版权
		rightPane.add(new XStatusLabel("Powered by Xujun"));
		
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(backgroundLeftImage, 0, 0, null);
		g2d.drawImage(backgroundRightImage, getWidth() - backgroundRightImage.getWidth(null), 0, null);
	}

	public JPanel getLeftPane()
	{
		return leftPane;
	}

	public JPanel getRightPane()
	{
		return rightPane;
	}

	public void addSeparator()
	{
		rightPane.add(new XStatusSeparator());
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, backgroundImageIcon.getIconHeight());
	}
}
