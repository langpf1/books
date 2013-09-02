/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-13 下午01:18:41
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import xujun.control.XContorlUtil;


/**
 * 柱状图图例
 * @author 徐骏
 * @data   2010-7-13
 */
public class BarChart extends XChartPanellet 
{
	//private CategoryDataset dataset;
	//private JLabel loadlLabel = new JLabel();
	private MyBarRenderer barRenderer;
	
	public BarChart()
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("柱子可点击", "Years", "Monery", getDataset(), PlotOrientation.VERTICAL, true, true, false);
		
		//设置自己的barItem的渲染器
		barRenderer = new MyBarRenderer();
		jfreechart.getCategoryPlot().setRenderer(barRenderer);
		
		setChart(jfreechart);
		//注册鼠标事件
		addChartMouseListener(new MyChartMouserListener());
		/*显示进度
		//loadlLabel.setIcon(XContorlUtil.getImageIcon("xujun/control/images/chartpanel/loading.gif"));
		loadlLabel.setIcon(XContorlUtil.getImageIcon("xujun/control/images/loading.gif"));
		add(loadlLabel);
		//new LoadDataWorker().execute();*/
	}
	public JToolBar getCharToolBar() 
	{
		JToolBar toolBar = super.getCharToolBar();
		JComboBox combobox = new JComboBox();
		combobox.setPreferredSize(new Dimension(combobox.getPreferredSize().width, 20));
		combobox.addItem("South");
		combobox.addItem("East");
		combobox.addItem("West");
		combobox.addItem("North");
		toolBar.add(combobox);
		return toolBar;
	}
	private CategoryDataset getDataset()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(52D, "China", new Integer(2005));
		dataset.addValue(75D, "Americ", new Integer(2005));
		dataset.addValue(68D, "Japan", new Integer(2005));
		
		dataset.addValue(68D, "China", new Integer(2006));
		dataset.addValue(82D, "Americ", new Integer(2006));
		dataset.addValue(69D, "Japan", new Integer(2006));
		
		dataset.addValue(78D, "China", new Integer(2007));
		dataset.addValue(108D, "Americ", new Integer(2007));
		dataset.addValue(59D, "Japan", new Integer(2007));
		
		dataset.addValue(89D, "China", new Integer(2008));
		dataset.addValue(92D, "Americ", new Integer(2008));
		dataset.addValue(54D, "Japan", new Integer(2008));
		
		return dataset;
	}
	@Override
	public void run()
	{

	}
	/*Swingworker不同时启动多个线程，性能不够
	class LoadDataWorker extends SwingWorker<CategoryDataset, Void>
	{

		@Override
		protected CategoryDataset doInBackground() throws Exception
		{
			return getCategoryDataset();
		}
		@Override
		protected void done()
		{
			try
			{
				dataset = get();
				JFreeChart jfreechart = ChartFactory.createBarChart("Housing Consume", "Years", "Monery", dataset, PlotOrientation.VERTICAL, true, true, false);
				setChart(jfreechart);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				e.printStackTrace();
			}
		}
	}
	 */
	class MyBarRenderer extends BarRenderer
	{
		private int hightlightRow ;
		private int hightlightCol ;
		
		public MyBarRenderer()
		{
			hightlightRow = -1;
			hightlightCol = -1;
			GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
			GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
			GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
			setSeriesPaint(0, gradientpaint);
			setSeriesPaint(1, gradientpaint1);
			setSeriesPaint(2, gradientpaint2);
			//启用barItem的边框线，注意默认是不可见的
			setDrawBarOutline(true);
		}
		
		public void setHightlightItem(int i,int j)
		{
			if (hightlightRow == i && hightlightCol == j)
			{
				return;
			} 
			else
			{
				hightlightRow = i;
				hightlightCol = j;
				//发送更新事件
				notifyListeners(new RendererChangeEvent(this));

			}
		}
		//设置每个barItem的边线Paint，也可以直接调用setBaseOutlinePaint()
		@Override
		public Paint getItemOutlinePaint(int row, int column)
		{
			if(row == hightlightRow && column == hightlightCol)
			{
				return Color.YELLOW;
			}
			else 
			{
				return super.getItemOutlinePaint(row, column);
			}
		}
		//设置每个barItem的边线Stroke
		@Override
		public Stroke getItemOutlineStroke(int row, int column)
		{
			if(row == hightlightRow && column == hightlightCol)
			{
				return new BasicStroke(2.0f);
			}
			else 
			{
				//可以通过返回一个0的stroke来实现无边线
				return new BasicStroke(0);
				//return super.getItemOutlineStroke(row, column);
			}
		}
		//阴影是否可见
		
		@Override
		public boolean getShadowsVisible()
		{
			return false;
		}
	}
	/* (non-Javadoc)
	 * @see org.jfree.chart.ChartMouseListener#chartMouseClicked(org.jfree.chart.ChartMouseEvent)
	 */
	class MyChartMouserListener implements ChartMouseListener
	{
		@Override
		public void chartMouseClicked(ChartMouseEvent e)
		{
			ChartEntity entity = e.getEntity();
			if(entity instanceof CategoryItemEntity)
			{
				CategoryItemEntity itemEntity = (CategoryItemEntity)entity;
				CategoryDataset dataset = itemEntity.getDataset();
				int rowIndex = dataset.getRowIndex(itemEntity.getRowKey());
				int colIndex = dataset.getColumnIndex(itemEntity.getColumnKey());
				barRenderer.setHightlightItem(rowIndex, colIndex);
				JOptionPane.showMessageDialog(null, "Value is :"+dataset.getValue(rowIndex, colIndex));
			}
			else 
			{
				barRenderer.setHightlightItem(-1,-1);
			}
		}
		/* (non-Javadoc)
		 * @see org.jfree.chart.ChartMouseListener#chartMouseMoved(org.jfree.chart.ChartMouseEvent)
		 */
		@Override
		public void chartMouseMoved(ChartMouseEvent e)
		{
			
		}
	}
	
}

