/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-27 上午10:13:32
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.TextAnchor;
import xujun.control.XContorlUtil;
/**
 * @author 徐骏
 * @data   2010-7-27
 */
public class GanttChart extends XChartPanellet
{
	public GanttChart()
	{
		JFreeChart chart = ChartFactory.createGanttChart("甘特图", "任务", "日期", getDataset(), true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
		categoryplot.setRangePannable(true);
		categoryplot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10F);
		DateAxis dateaxis = (DateAxis)categoryplot.getRangeAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
		dateaxis.setLowerMargin(0.1);
		dateaxis.setUpperMargin(0.1);
		GanttRenderer ganttrenderer = (GanttRenderer)categoryplot.getRenderer();
		ganttrenderer.setDrawBarOutline(false);
		ganttrenderer.setBaseItemLabelFont(XContorlUtil.CHART_AXIS_FONT);//中文
		ganttrenderer.setBaseItemLabelGenerator(new MyLabelGenerator(new SimpleDateFormat("MM-dd")));
		ganttrenderer.setBaseItemLabelsVisible(true);
		ganttrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT));
		
		setChart(chart);
	}
	private IntervalCategoryDataset getDataset()
	{
		TaskSeries taskseries = new TaskSeries("计划");
		taskseries.add(new Task("需求分析", new SimpleTimePeriod(date(1, 3, 2001), date(5, 3, 2001))));
		taskseries.add(new Task("需求确认", new SimpleTimePeriod(date(9, 3, 2001), date(9, 3, 2001))));
		taskseries.add(new Task("逻辑视图", new SimpleTimePeriod(date(10, 3, 2001), date(5, 4, 2001))));
		taskseries.add(new Task("架构设计", new SimpleTimePeriod(date(6, 4, 2001), date(30, 4, 2001))));
		taskseries.add(new Task("架构确认", new SimpleTimePeriod(date(2, 5, 2001), date(2, 5, 2001))));
		taskseries.add(new Task("物理视图", new SimpleTimePeriod(date(3, 5, 2001), date(31, 6, 2001))));
		taskseries.add(new Task("编码", new SimpleTimePeriod(date(1, 7, 2001), date(8, 7, 2001))));
		taskseries.add(new Task("单元测试", new SimpleTimePeriod(date(10, 7, 2001), date(10, 7, 2001))));
		taskseries.add(new Task("压力测试", new SimpleTimePeriod(date(12, 7, 2001), date(12, 8, 2001))));
		taskseries.add(new Task("性能优化", new SimpleTimePeriod(date(13, 8, 2001), date(31, 9, 2001))));
		taskseries.add(new Task("部署", new SimpleTimePeriod(date(1, 10, 2001), date(15, 10, 2001))));
		taskseries.add(new Task("上线", new SimpleTimePeriod(date(28, 10, 2001), date(30, 10, 2001))));
		TaskSeries taskseries1 = new TaskSeries("实际");
		taskseries1.add(new Task("需求分析", new SimpleTimePeriod(date(1, 3, 2001), date(5, 3, 2001))));
		taskseries1.add(new Task("需求确认", new SimpleTimePeriod(date(9, 3, 2001), date(9, 3, 2001))));
		taskseries1.add(new Task("逻辑视图", new SimpleTimePeriod(date(10, 3, 2001), date(15, 4, 2001))));
		taskseries1.add(new Task("架构设计", new SimpleTimePeriod(date(15, 4, 2001), date(17, 5, 2001))));
		taskseries1.add(new Task("架构确认", new SimpleTimePeriod(date(30, 5, 2001), date(30, 5, 2001))));
		taskseries1.add(new Task("物理视图", new SimpleTimePeriod(date(1, 6, 2001), date(12, 8, 2001))));
		taskseries1.add(new Task("编码", new SimpleTimePeriod(date(12, 8, 2001), date(22, 8, 2001))));
		taskseries1.add(new Task("单元测试", new SimpleTimePeriod(date(25, 8, 2001), date(27, 8, 2001))));
		taskseries1.add(new Task("压力测试", new SimpleTimePeriod(date(27, 8, 2001), date(30, 9, 2001))));
		taskseries1.add(new Task("性能优化", new SimpleTimePeriod(date(31, 9, 2001), date(17, 10, 2001))));
		taskseries1.add(new Task("部署", new SimpleTimePeriod(date(18, 10, 2001), date(5, 11, 2001))));
		taskseries1.add(new Task("上线", new SimpleTimePeriod(date(10, 11, 2001), date(11, 11, 2001))));
		TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
		taskseriescollection.add(taskseries);
		taskseriescollection.add(taskseries1);
		return taskseriescollection;
	}
	private Date date(int day, int month, int year)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date date1 = calendar.getTime();
		return date1;
	}
	class MyLabelGenerator implements CategoryItemLabelGenerator
	{
		DateFormat df;
		public String generateLabel(CategoryDataset categorydataset, int i,int j)
		{
			Number number = null;
			if (categorydataset instanceof IntervalCategoryDataset)
			{
				IntervalCategoryDataset intervalcategorydataset = (IntervalCategoryDataset) categorydataset;
				number = intervalcategorydataset.getEndValue(i, j);
			}
			else
			{
				number = categorydataset.getValue(i, j);
			}
			if (number == null)
			{
				return "null";
			}
			else
			{
				long l = number.longValue();
				Date date1 = new Date(l);
				return df.format(date1);
			}
		}
		
		public String generateColumnLabel(CategoryDataset categorydataset, int i)
		{
			return categorydataset.getColumnKey(i).toString();
		}
		public String generateRowLabel(CategoryDataset categorydataset, int i)
		{
			return categorydataset.getRowKey(i).toString();
		}
		public MyLabelGenerator(DateFormat dateformat)
		{
			df = dateformat;
		}
	}
}
