/**   
 * @Title: XMemoryBar.java
 * @Package xujun.control.statusbar
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-6 下午10:15:31
 * @version V1.0   
 */

package xujun.control.statusbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.NumberFormat;

import javax.swing.Timer;


/**
 * @ClassName: XMemoryBar
 * @Description: 状态栏中的内存状态标签
 * @date 2010-7-6 下午10:15:31
 * 
 */
public class XMemoryBar extends XProgressBar
{
	private static final int kilo = 1024;
	private static final String mega = "M";
	private static MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
	private static NumberFormat format = NumberFormat.getInstance();
	private int delay;

	public XMemoryBar()
	{
		super(0, 0, 100);
		delay = 2000;
		ActionListener taskPerformer = new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				long usedMemory = memorymbean.getHeapMemoryUsage().getUsed();
				long totalMemory = memorymbean.getHeapMemoryUsage().getMax();
				updateMemoryUsage(usedMemory, totalMemory);
			}

		};
		(new Timer(delay, taskPerformer)).start();
	}

	private void updateMemoryUsage(long usedMemory, long totalMemory)
	{
		int percent = (int)((usedMemory * 100L) / totalMemory);
		setValue(percent);
		String usedMega = (new StringBuilder()).append(format.format(usedMemory / 1024L / 1024L)).append("M").toString();
		String totalMega = (new StringBuilder()).append(format.format(totalMemory / 1024L / 1024L)).append("M").toString();
		String message = (new StringBuilder()).append(usedMega).append("/").append(totalMega).toString();
		setString(message);
		setToolTipText((new StringBuilder()).append("Memory used ").append(format.format(usedMemory)).append(" of total ").append(format.format(totalMemory)).toString());
	}
}
