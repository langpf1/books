/**   
 * @Title: XGCButton.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:39:31
 * @version V1.0   
 */

package xujun.control.statusbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xujun.control.XContorlUtil;
import xujun.control.toolbar.XToolBarButton;

/**
 * @ClassName: XGCButton
 * @Description: GC按钮
 * @date 2010-7-6 下午10:39:31
 * 
 */
public class XGCButton extends XToolBarButton
{
	public XGCButton()
	{
		super();
		setIcon(XContorlUtil.getImageIcon("xujun/control/images/gc.png"));
		setToolTipText("释放内存");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.gc();
			}
		});
	}
}
