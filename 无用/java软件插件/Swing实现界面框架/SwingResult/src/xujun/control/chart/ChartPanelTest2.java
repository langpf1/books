/**   
 * @Title: ChartPanelTest2.java
 * @Package xujun.control.chart
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-26 下午08:23:33
 * @version V1.0   
 */

package xujun.control.chart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @ClassName: ChartPanelTest2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-26 下午08:23:33
 * 
 */
public class ChartPanelTest2
{
	public JPanel getChartPanel()
	{
		List<JPanel> chartPanels = new ArrayList<JPanel>();
		//柱图
		chartPanels.add(new StackBarChart());
		chartPanels.add(new StackBarChart2());
		chartPanels.add(new AreaChart());
		//线图
		chartPanels.add(new PieChart());
		chartPanels.add(new GanttChart());
		chartPanels.add(new DialChart());
		chartPanels.add(new MeterChart());
		chartPanels.add(new BubbleChart());
		chartPanels.add(new ScatterChart());
		
		XChartPanel chartPanel = new XChartPanel(3,3,chartPanels);
		return chartPanel;
	}
}
