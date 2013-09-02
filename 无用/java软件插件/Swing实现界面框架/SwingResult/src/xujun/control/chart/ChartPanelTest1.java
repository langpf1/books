/**   
 * @Title: ChartPanelTest1.java
 * @Package xujun.control.chart
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-26 下午08:19:12
 * @version V1.0   
 */

package xujun.control.chart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @ClassName: ChartPanelTest1
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2010-7-26 下午08:19:12
 * 
 */
public class ChartPanelTest1 
{
	public JPanel getChartPanel()
	{
		List<JPanel> chartPanels = new ArrayList<JPanel>();
		//柱图
		chartPanels.add(new BarChart());
		chartPanels.add(new BarXYChart());
		chartPanels.add(new Bar3DChart());
		//线图
		chartPanels.add(new LineChart());
		chartPanels.add(new CombinedLineAndBarChart());
		chartPanels.add(new LineHiddenChart());
		chartPanels.add(new DynamicDatasetChart());
		chartPanels.add(new IntervalChart());
		chartPanels.add(new StackLineChart());
		
		XChartPanel chartPanel = new XChartPanel(3,3,chartPanels);
		return chartPanel;
	}
}
