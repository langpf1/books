/**   
 * @Title: XStatusSeparator.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:05:08
 * @version V1.0   
 */

package xujun.control.statusbar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import xujun.control.XContorlUtil;

/**
 * @ClassName: XStatusSeparator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-6 下午10:05:08
 * 
 */
public class XStatusSeparator extends JLabel
{
	private ImageIcon imageIcon;

	public XStatusSeparator()
	{
		imageIcon = XContorlUtil.getImageIcon("xujun/control/images/statusbar_separator.png");
		init();
	}

	private void init()
	{
		setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		setOpaque(false);
		setIcon(imageIcon);
	}
}
