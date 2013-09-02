/**   
 * @Title: XStatusMessageLabel.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:11:33
 * @version V1.0   
 */

package xujun.control.statusbar;

import javax.swing.ImageIcon;

import xujun.control.XContorlUtil;

/**
 * @ClassName: XStatusMessageLabel
 * @Description: 状态栏最左边的服务器状态信息标签
 * @date 2010-7-6 下午10:11:33
 * 
 */
public class XStatusMessageLabel extends XStatusLabel
{
	private static final ImageIcon ICON_ORANGE = XContorlUtil.getImageIcon("xujun/control/images/statusbar_message_light_orange.png");
	private static final ImageIcon ICON_RED = XContorlUtil.getImageIcon("xujun/control/images/statusbar_message_light_red.png");
	private static final ImageIcon ICON_GREEN = XContorlUtil.getImageIcon("xujun/control/images/statusbar_message_light_green.png");

	public XStatusMessageLabel()
	{
		setText("Server is connected");
	}

	protected void init()
	{
		super.init();
		setFont(XContorlUtil.FONT_14_BOLD);
		setGreenLight();
		//启动监听线程
		initMockers();
	}

	public void setRedLight()
	{
		setIcon(ICON_RED);
	}

	public void setGreenLight()
	{
		setIcon(ICON_GREEN);
	}

	public void setOrangeLight()
	{
		setIcon(ICON_ORANGE);
	}
	private void initMockers()
	{
		Thread thread = new Thread()
		{
			public void run()
			{
				do
				{
					int i = 0;
					while (i < 3)
					{
						try
						{
							Thread.sleep(5000L);
							if (i == 0)
							{
								setGreenLight();
								setText("Server is connected");
							}
							if (i == 1)
							{
								setOrangeLight();
								setText("Server connection is slow");
							}
							if (i == 2)
							{
								setRedLight();
								setText("Server connection is broken");
							}
						}
						catch (InterruptedException ex)
						{
							ex.printStackTrace();
						}
						i++;
					}
				}
				while (true);
			}
		};
		thread.start();
	}
}
