/**   
 * @Title: XContentPanel.java
 * @Package xujun.control
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-1 下午08:30:32
 * @version V1.0   
 */

package xujun.control;

import java.awt.BorderLayout;
import javax.swing.JPanel;
/**
 * @ClassName: XContentPanel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-1 下午08:30:32
 * 
 */
public class XContentPanel extends JPanel
{
	public XContentPanel()
	{
		super();
		setLayout(new BorderLayout());
		setBackground(XContorlUtil.CONTENT_PANE_BACKGROUND);
	}
}
