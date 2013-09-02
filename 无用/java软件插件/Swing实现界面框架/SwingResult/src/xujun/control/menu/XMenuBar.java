/**   
 * @Title: XMenuBar.java
 * @Package xujun.control
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-1 下午07:09:53
 * @version V1.0   
 */

package xujun.control.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import xujun.control.XContorlUtil;



/**
 * @ClassName: XMenuBar
 * @Description: TODO(菜单栏)
 * @date 2010-7-1 下午07:09:53
 * 
 */
public class XMenuBar extends JMenuBar
{

	private Image backgroundLeftImage;
	private Image backgroundRightImage;
	private ImageIcon backgroundImageIcon;
	private TexturePaint paint;
	private Border border;
	
	public XMenuBar()
	{
		backgroundLeftImage = XContorlUtil.getImage("xujun/control/images/menubar_background_left.png");
		backgroundRightImage = XContorlUtil.getImage("xujun/control/images/menubar_background_right.png");
		backgroundImageIcon = XContorlUtil.getImageIcon("xujun/control/images/menubar_background.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/menubar_background.png");
		border = BorderFactory.createEmptyBorder();
		init();
	}
	private void init()
	{
		setBorder(border);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(backgroundLeftImage, 0, 0, null);
		g2d.drawImage(backgroundRightImage, getWidth() - backgroundRightImage.getWidth(null), 0, null);
	}
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, backgroundImageIcon.getIconHeight());
	}
	
}

