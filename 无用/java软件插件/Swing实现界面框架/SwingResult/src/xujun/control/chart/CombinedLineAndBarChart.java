/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-16 下午01:37:22
 * copyright Anymusic Ltd.
 */
package xujun.control.chart;

import java.awt.Color;
import java.awt.Paint;
import java.text.SimpleDateFormat;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


/**
 * 
 * @author 徐骏
 * @data   2010-7-16
 */
public class CombinedLineAndBarChart extends XChartPanellet
{
	public CombinedLineAndBarChart()
	{
		XYPlot linePlot = new XYPlot(getLineDataset(),null,new NumberAxis("水位"),new XYLineAndShapeRenderer(true,false));
	
		XYBarRenderer barRenderer = new XYBarRenderer() {

			public Paint getItemPaint(int i, int j)
			{
				XYDataset xydataset = getPlot().getDataset();
				if (xydataset.getYValue(i, j) >= 0.0D)
					return Color.red;
				else
					return Color.green;
			}

		};
		barRenderer.setSeriesPaint(0, Color.red);
		barRenderer.setDrawBarOutline(false);
		barRenderer.setShadowVisible(false);//阴影
		barRenderer.setBarPainter(new StandardXYBarPainter());// 设置bar使用普通的painter，即扁平bar
		barRenderer.setMargin(0.1f);//bar之间的间隔
		XYPlot barPlot = new XYPlot(getBarDataset(),null,new NumberAxis("雨量"),barRenderer);

		//创建Domain坐标
		DateAxis dateaxis = new DateAxis("月份");
		dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM"));
		dateaxis.setLowerMargin(0.01D);//图形在左右两边的留白，默认比较大，设置一下好看一点 0.01=1%
		dateaxis.setUpperMargin(0.01D);

		CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(dateaxis);
		//在聚集坐标中加入Plot，3表示Plot高度比例
		combineddomainxyplot.add(linePlot,3);
		combineddomainxyplot.add(barPlot,2);
		combineddomainxyplot.setGap(5D);//设置plot之间的空白间隔大小
		combineddomainxyplot.setDomainPannable(true);//只要设置combin plot，所有combin中的plot在pan时候都会联动
	
		
		JFreeChart chart = new JFreeChart("组合图",combineddomainxyplot);
		setChart(chart);
	}
	private  XYDataset getLineDataset()
	{
		TimeSeries timeseries = new TimeSeries("水位线");
		timeseries.add(new Month(1, 1990), 2974.5839999999998D);
		timeseries.add(new Month(2, 1990), 2994.3539999999998D);
		timeseries.add(new Month(3, 1990), 3051.9560000000001D);
		timeseries.add(new Month(4, 1990), 3061.0129999999999D);
		timeseries.add(new Month(5, 1990), 3095.172D);
		timeseries.add(new Month(6, 1990), 3143.7539999999999D);
		timeseries.add(new Month(7, 1990), 3168.7719999999999D);
		timeseries.add(new Month(8, 1990), 3211.6909999999998D);
		timeseries.add(new Month(9, 1990), 3233.3130000000001D);
		timeseries.add(new Month(10, 1990), 3274.9499999999998D);
		timeseries.add(new Month(11, 1990), 3330.6849999999999D);
		timeseries.add(new Month(12, 1990), 3364.8200000000002D);
		timeseries.add(new Month(1, 1991), 3411.4090000000001D);
		timeseries.add(new Month(2, 1991), 3458.6370000000002D);
		timeseries.add(new Month(3, 1991), 3441.3670000000002D);
		timeseries.add(new Month(4, 1991), 3445.0590000000002D);
		timeseries.add(new Month(5, 1991), 3497.232D);
		timeseries.add(new Month(6, 1991), 3537.9879999999998D);
		timeseries.add(new Month(7, 1991), 3576.8270000000002D);
		timeseries.add(new Month(8, 1991), 3614.3989999999999D);
		timeseries.add(new Month(9, 1991), 3665.3029999999999D);
		timeseries.add(new Month(10, 1991), 3717.1060000000002D);
		timeseries.add(new Month(11, 1991), 3747.3629999999998D);
		timeseries.add(new Month(12, 1991), 3801.6979999999999D);
		timeseries.add(new Month(1, 1992), 3809.3339999999998D);
		timeseries.add(new Month(2, 1992), 3829.0590000000002D);
		timeseries.add(new Month(3, 1992), 3881.288D);
		timeseries.add(new Month(4, 1992), 3891.9740000000002D);
		timeseries.add(new Month(5, 1992), 3934.4349999999999D);
		timeseries.add(new Month(6, 1992), 3984.6559999999999D);
		timeseries.add(new Month(7, 1992), 4010.6120000000001D);
		timeseries.add(new Month(8, 1992), 4048.9380000000001D);
		timeseries.add(new Month(9, 1992), 4064.6210000000001D);
		timeseries.add(new Month(10, 1992), 4067.3290000000002D);
		timeseries.add(new Month(11, 1992), 4132.5249999999996D);
		timeseries.add(new Month(12, 1992), 4177.009D);
		timeseries.add(new Month(1, 1993), 4167.1999999999998D);
		timeseries.add(new Month(2, 1993), 4197.0039999999999D);
		timeseries.add(new Month(3, 1993), 4230.5799999999999D);
		timeseries.add(new Month(4, 1993), 4254.0839999999998D);
		timeseries.add(new Month(5, 1993), 4296.2780000000002D);
		timeseries.add(new Month(6, 1993), 4351.9499999999998D);
		timeseries.add(new Month(7, 1993), 4350.2610000000004D);
		timeseries.add(new Month(8, 1993), 4403.3130000000001D);
		timeseries.add(new Month(9, 1993), 4411.4889999999996D);
		timeseries.add(new Month(10, 1993), 4422.5110000000004D);
		timeseries.add(new Month(11, 1993), 4493.5349999999999D);
		timeseries.add(new Month(12, 1993), 4535.6869999999999D);
		timeseries.add(new Month(1, 1994), 4526.308D);
		timeseries.add(new Month(2, 1994), 4559.5410000000002D);
		timeseries.add(new Month(3, 1994), 4575.8689999999997D);
		timeseries.add(new Month(4, 1994), 4568.7039999999997D);
		timeseries.add(new Month(5, 1994), 4609.2960000000003D);
		timeseries.add(new Month(6, 1994), 4645.8019999999997D);
		timeseries.add(new Month(7, 1994), 4636.3620000000001D);
		timeseries.add(new Month(8, 1994), 4691.991D);
		timeseries.add(new Month(9, 1994), 4692.75D);
		timeseries.add(new Month(10, 1994), 4734.1670000000004D);
		timeseries.add(new Month(11, 1994), 4778.5200000000004D);
		timeseries.add(new Month(12, 1994), 4800.1499999999996D);
		timeseries.add(new Month(1, 1995), 4815.8270000000002D);
		timeseries.add(new Month(2, 1995), 4854.2979999999998D);
		timeseries.add(new Month(3, 1995), 4864.116D);
		timeseries.add(new Month(4, 1995), 4852.3270000000002D);
		timeseries.add(new Month(5, 1995), 4903.9260000000004D);
		timeseries.add(new Month(6, 1995), 4951.3720000000003D);
		timeseries.add(new Month(7, 1995), 4960.152D);
		timeseries.add(new Month(8, 1995), 4970.7560000000003D);
		timeseries.add(new Month(9, 1995), 4973.9830000000002D);
		timeseries.add(new Month(10, 1995), 4985.2619999999997D);
		timeseries.add(new Month(11, 1995), 4989.3299999999999D);
		timeseries.add(new Month(12, 1995), 4988.665D);
		timeseries.add(new Month(1, 1996), 4987.4359999999997D);
		timeseries.add(new Month(2, 1996), 5017.0410000000002D);
		timeseries.add(new Month(3, 1996), 5117.7860000000001D);
		timeseries.add(new Month(4, 1996), 5102.049D);
		timeseries.add(new Month(5, 1996), 5128.509D);
		timeseries.add(new Month(6, 1996), 5161.076D);
		timeseries.add(new Month(7, 1996), 5188.8890000000001D);
		timeseries.add(new Month(8, 1996), 5208.3029999999999D);
		timeseries.add(new Month(9, 1996), 5224.8109999999997D);
		timeseries.add(new Month(10, 1996), 5247.3199999999997D);
		timeseries.add(new Month(11, 1996), 5296.549D);
		timeseries.add(new Month(12, 1996), 5323.1719999999996D);
		timeseries.add(new Month(1, 1997), 5313.9970000000003D);
		timeseries.add(new Month(2, 1997), 5349.9369999999999D);
		timeseries.add(new Month(3, 1997), 5380.8900000000003D);
		timeseries.add(new Month(4, 1997), 5353.9709999999995D);
		timeseries.add(new Month(5, 1997), 5344.9610000000002D);
		timeseries.add(new Month(6, 1997), 5376.1509999999998D);
		timeseries.add(new Month(7, 1997), 5373.2309999999998D);
		timeseries.add(new Month(8, 1997), 5404.4200000000001D);
		timeseries.add(new Month(9, 1997), 5413.1459999999997D);
		timeseries.add(new Month(10, 1997), 5427.2250000000004D);
		timeseries.add(new Month(11, 1997), 5462.6220000000003D);
		timeseries.add(new Month(12, 1997), 5502.3879999999999D);
		timeseries.add(new Month(1, 1998), 5490.0640000000003D);
		timeseries.add(new Month(2, 1998), 5520.6679999999997D);
		timeseries.add(new Month(3, 1998), 5542.4260000000004D);
		timeseries.add(new Month(4, 1998), 5499.8950000000004D);
		timeseries.add(new Month(5, 1998), 5506.3559999999998D);
		timeseries.add(new Month(6, 1998), 5547.9350000000004D);
		timeseries.add(new Month(7, 1998), 5527.7380000000003D);
		timeseries.add(new Month(8, 1998), 5564.5529999999999D);
		timeseries.add(new Month(9, 1998), 5526.1930000000002D);
		timeseries.add(new Month(10, 1998), 5559.2550000000001D);
		timeseries.add(new Month(11, 1998), 5591.9790000000003D);
		timeseries.add(new Month(12, 1998), 5614.2169999999996D);
		timeseries.add(new Month(1, 1999), 5610.1170000000002D);
		timeseries.add(new Month(2, 1999), 5621.9459999999999D);
		timeseries.add(new Month(3, 1999), 5651.6149999999998D);
		timeseries.add(new Month(4, 1999), 5585.8400000000001D);
		timeseries.add(new Month(5, 1999), 5604.1980000000003D);
		timeseries.add(new Month(6, 1999), 5638.7799999999997D);
		timeseries.add(new Month(7, 1999), 5638.6559999999999D);
		timeseries.add(new Month(8, 1999), 5672.3860000000004D);
		timeseries.add(new Month(9, 1999), 5656.2709999999997D);
		timeseries.add(new Month(10, 1999), 5679.7269999999999D);
		timeseries.add(new Month(11, 1999), 5693.6000000000004D);
		timeseries.add(new Month(12, 1999), 5776.0910000000003D);
		timeseries.add(new Month(1, 2000), 5711.2849999999999D);
		timeseries.add(new Month(2, 2000), 5735.3329999999996D);
		timeseries.add(new Month(3, 2000), 5773.3919999999998D);
		timeseries.add(new Month(4, 2000), 5685.1080000000002D);
		timeseries.add(new Month(5, 2000), 5647.1700000000001D);
		timeseries.add(new Month(6, 2000), 5685.9380000000001D);
		timeseries.add(new Month(7, 2000), 5658.8069999999998D);
		timeseries.add(new Month(8, 2000), 5677.8220000000001D);
		timeseries.add(new Month(9, 2000), 5674.1779999999999D);
		timeseries.add(new Month(10, 2000), 5657.3280000000004D);
		timeseries.add(new Month(11, 2000), 5709.6989999999996D);
		timeseries.add(new Month(12, 2000), 5662.2160000000003D);
		timeseries.add(new Month(1, 2001), 5716.0709999999999D);
		timeseries.add(new Month(2, 2001), 5735.8590000000004D);
		timeseries.add(new Month(3, 2001), 5773.7399999999998D);
		timeseries.add(new Month(4, 2001), 5661.348D);
		timeseries.add(new Month(5, 2001), 5656.1819999999998D);
		timeseries.add(new Month(6, 2001), 5726.8149999999996D);
		timeseries.add(new Month(7, 2001), 5718.3029999999999D);
		timeseries.add(new Month(8, 2001), 5769.8760000000002D);
		timeseries.add(new Month(9, 2001), 5807.4629999999997D);
		timeseries.add(new Month(10, 2001), 5815.9830000000002D);
		timeseries.add(new Month(11, 2001), 5888.8969999999999D);
		timeseries.add(new Month(12, 2001), 5943.4390000000003D);
		timeseries.add(new Month(1, 2002), 5937.2290000000003D);
		timeseries.add(new Month(2, 2002), 6003.4530000000004D);
		timeseries.add(new Month(3, 2002), 6006.0320000000002D);
		timeseries.add(new Month(4, 2002), 5984.6769999999997D);
		timeseries.add(new Month(5, 2002), 6019.3320000000003D);
		timeseries.add(new Month(6, 2002), 6126.4690000000001D);
		timeseries.add(new Month(7, 2002), 6159.741D);
		timeseries.add(new Month(8, 2002), 6210.482D);
		timeseries.add(new Month(9, 2002), 6228.2359999999999D);
		timeseries.add(new Month(10, 2002), 6282.5280000000002D);
		timeseries.add(new Month(11, 2002), 6343.46D);
		timeseries.add(new Month(12, 2002), 6405.7070000000003D);
		timeseries.add(new Month(1, 2003), 6401.3770000000004D);
		timeseries.add(new Month(2, 2003), 6445.79D);
		timeseries.add(new Month(3, 2003), 6460.7759999999998D);
		timeseries.add(new Month(4, 2003), 6460.3810000000003D);
		timeseries.add(new Month(5, 2003), 6558.1469999999999D);
		timeseries.add(new Month(6, 2003), 6670.1210000000001D);
		timeseries.add(new Month(7, 2003), 6751.1949999999997D);
		timeseries.add(new Month(8, 2003), 6790.0410000000002D);
		timeseries.add(new Month(9, 2003), 6783.2309999999998D);
		timeseries.add(new Month(10, 2003), 6872.6760000000004D);
		timeseries.add(new Month(11, 2003), 6925.0649999999996D);
		timeseries.add(new Month(12, 2003), 6997.9639999999999D);
		timeseries.add(new Month(1, 2004), 7009.2349999999997D);
		timeseries.add(new Month(2, 2004), 7091.9430000000002D);
		timeseries.add(new Month(3, 2004), 7131.0680000000002D);
		timeseries.add(new Month(4, 2004), 7133.7889999999998D);
		timeseries.add(new Month(5, 2004), 7196.3829999999998D);
		timeseries.add(new Month(6, 2004), 7274.335D);
		timeseries.add(new Month(7, 2004), 7316.5680000000002D);
		timeseries.add(new Month(8, 2004), 7350.9499999999998D);
		timeseries.add(new Month(9, 2004), 7379.0529999999999D);
		timeseries.add(new Month(10, 2004), 7429.6769999999997D);
		timeseries.add(new Month(11, 2004), 7525.21D);
		timeseries.add(new Month(12, 2004), 7596.1440000000002D);
		timeseries.add(new Month(1, 2005), 7627.7430000000004D);
		timeseries.add(new Month(2, 2005), 7713.1379999999999D);
		timeseries.add(new Month(3, 2005), 7776.9390000000003D);
		timeseries.add(new Month(4, 2005), 7764.5370000000003D);
		timeseries.add(new Month(5, 2005), 7777.8800000000001D);
		timeseries.add(new Month(6, 2005), 7836.4960000000001D);
		timeseries.add(new Month(7, 2005), 7887.6180000000004D);
		timeseries.add(new Month(8, 2005), 7926.933D);
		timeseries.add(new Month(9, 2005), 7932.71D);
		timeseries.add(new Month(10, 2005), 8027.1229999999996D);
		timeseries.add(new Month(11, 2005), 8092.3220000000001D);
		timeseries.add(new Month(12, 2005), 8170.4139999999998D);
		timeseries.add(new Month(1, 2006), 8196.0699999999997D);
		timeseries.add(new Month(2, 2006), 8269.8860000000004D);
		timeseries.add(new Month(3, 2006), 8371.1560000000009D);
		timeseries.add(new Month(4, 2006), 8355.7180000000008D);
		timeseries.add(new Month(5, 2006), 8356.777D);
		timeseries.add(new Month(6, 2006), 8420.0419999999995D);
		timeseries.add(new Month(7, 2006), 8444.3469999999998D);
		timeseries.add(new Month(8, 2006), 8515.0339999999997D);
		timeseries.add(new Month(9, 2006), 8506.9740000000002D);
		timeseries.add(new Month(10, 2006), 8584.3289999999997D);
		timeseries.add(new Month(11, 2006), 8633.2459999999992D);
		timeseries.add(new Month(12, 2006), 8680.2240000000002D);
		timeseries.add(new Month(1, 2007), 8707.5609999999997D);
		return new TimeSeriesCollection(timeseries);
	}

	private XYDataset getBarDataset()
	{
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		TimeSeries timeseries = new TimeSeries("降雨量");
		timeseries.add(new Month(1, 1990), 276.62700000000001D);
		timeseries.add(new Month(2, 1990), 271.50900000000001D);
		timeseries.add(new Month(3, 1990), 311.05799999999999D);
		timeseries.add(new Month(4, 1990), 304.34500000000003D);
		timeseries.add(new Month(5, 1990), 317.63200000000001D);
		timeseries.add(new Month(6, 1990), 343.83100000000002D);
		timeseries.add(new Month(7, 1990), 368.31700000000001D);
		timeseries.add(new Month(8, 1990), 375.26600000000002D);
		timeseries.add(new Month(9, 1990), 375.88200000000001D);
		timeseries.add(new Month(10, 1990), 373.73099999999999D);
		timeseries.add(new Month(11, 1990), 407.096D);
		timeseries.add(new Month(12, 1990), 411.82600000000002D);
		timeseries.add(new Month(1, 1991), 436.82499999999999D);
		timeseries.add(new Month(2, 1991), 464.28300000000002D);
		timeseries.add(new Month(3, 1991), 389.411D);
		timeseries.add(new Month(4, 1991), 384.04599999999999D);
		timeseries.add(new Month(5, 1991), 402.06D);
		timeseries.add(new Month(6, 1991), 394.23399999999998D);
		timeseries.add(new Month(7, 1991), 408.05500000000001D);
		timeseries.add(new Month(8, 1991), 402.70800000000003D);
		timeseries.add(new Month(9, 1991), 431.99000000000001D);
		timeseries.add(new Month(10, 1991), 442.15600000000001D);
		timeseries.add(new Month(11, 1991), 416.678D);
		timeseries.add(new Month(12, 1991), 436.87799999999999D);
		timeseries.add(new Month(1, 1992), 397.92500000000001D);
		timeseries.add(new Month(2, 1992), 370.42200000000003D);
		timeseries.add(new Month(3, 1992), 439.92099999999999D);
		timeseries.add(new Month(4, 1992), 446.91500000000002D);
		timeseries.add(new Month(5, 1992), 437.20299999999997D);
		timeseries.add(new Month(6, 1992), 446.66800000000001D);
		timeseries.add(new Month(7, 1992), 433.78500000000003D);
		timeseries.add(new Month(8, 1992), 434.53899999999999D);
		timeseries.add(new Month(9, 1992), 399.31799999999998D);
		timeseries.add(new Month(10, 1992), 350.22300000000001D);
		timeseries.add(new Month(11, 1992), 385.16199999999998D);
		timeseries.add(new Month(12, 1992), 375.31099999999998D);
		timeseries.add(new Month(1, 1993), 357.86599999999999D);
		timeseries.add(new Month(2, 1993), 367.94499999999999D);
		timeseries.add(new Month(3, 1993), 349.29199999999997D);
		timeseries.add(new Month(4, 1993), 362.11000000000001D);
		timeseries.add(new Month(5, 1993), 361.84300000000002D);
		timeseries.add(new Month(6, 1993), 367.29399999999998D);
		timeseries.add(new Month(7, 1993), 339.649D);
		timeseries.add(new Month(8, 1993), 354.375D);
		timeseries.add(new Month(9, 1993), 346.86799999999999D);
		timeseries.add(new Month(10, 1993), 355.18200000000002D);
		timeseries.add(new Month(11, 1993), 361.00999999999999D);
		timeseries.add(new Month(12, 1993), 358.678D);
		timeseries.add(new Month(1, 1994), 359.108D);
		timeseries.add(new Month(2, 1994), 362.53699999999998D);
		timeseries.add(new Month(3, 1994), 345.28899999999999D);
		timeseries.add(new Month(4, 1994), 314.62D);
		timeseries.add(new Month(5, 1994), 313.01799999999997D);
		timeseries.add(new Month(6, 1994), 293.85199999999998D);
		timeseries.add(new Month(7, 1994), 286.101D);
		timeseries.add(new Month(8, 1994), 288.678D);
		timeseries.add(new Month(9, 1994), 281.26100000000002D);
		timeseries.add(new Month(10, 1994), 311.65600000000001D);
		timeseries.add(new Month(11, 1994), 284.98500000000001D);
		timeseries.add(new Month(12, 1994), 264.46300000000002D);
		timeseries.add(new Month(1, 1995), 289.51900000000001D);
		timeseries.add(new Month(2, 1995), 294.75700000000001D);
		timeseries.add(new Month(3, 1995), 288.24700000000001D);
		timeseries.add(new Month(4, 1995), 283.62299999999999D);
		timeseries.add(new Month(5, 1995), 294.63D);
		timeseries.add(new Month(6, 1995), 305.56999999999999D);
		timeseries.add(new Month(7, 1995), 323.79000000000002D);
		timeseries.add(new Month(8, 1995), 278.76499999999999D);
		timeseries.add(new Month(9, 1995), 281.233D);
		timeseries.add(new Month(10, 1995), 251.095D);
		timeseries.add(new Month(11, 1995), 210.81D);
		timeseries.add(new Month(12, 1995), 188.51499999999999D);
		timeseries.add(new Month(1, 1996), 171.60900000000001D);
		timeseries.add(new Month(2, 1996), 162.74299999999999D);
		timeseries.add(new Month(3, 1996), 253.66999999999999D);
		timeseries.add(new Month(4, 1996), 249.72200000000001D);
		timeseries.add(new Month(5, 1996), 224.583D);
		timeseries.add(new Month(6, 1996), 209.70400000000001D);
		timeseries.add(new Month(7, 1996), 228.73699999999999D);
		timeseries.add(new Month(8, 1996), 237.547D);
		timeseries.add(new Month(9, 1996), 250.828D);
		timeseries.add(new Month(10, 1996), 262.05799999999999D);
		timeseries.add(new Month(11, 1996), 307.21899999999999D);
		timeseries.add(new Month(12, 1996), 334.50700000000001D);
		timeseries.add(new Month(1, 1997), 326.56099999999998D);
		timeseries.add(new Month(2, 1997), 332.89600000000002D);
		timeseries.add(new Month(3, 1997), 263.10399999999998D);
		timeseries.add(new Month(4, 1997), 251.922D);
		timeseries.add(new Month(5, 1997), 216.452D);
		timeseries.add(new Month(6, 1997), 215.07499999999999D);
		timeseries.add(new Month(7, 1997), 184.34200000000001D);
		timeseries.add(new Month(8, 1997), 196.11699999999999D);
		timeseries.add(new Month(9, 1997), 188.33500000000001D);
		timeseries.add(new Month(10, 1997), 179.905D);
		timeseries.add(new Month(11, 1997), 166.07300000000001D);
		timeseries.add(new Month(12, 1997), 179.21600000000001D);
		timeseries.add(new Month(1, 1998), 176.06700000000001D);
		timeseries.add(new Month(2, 1998), 170.73099999999999D);
		timeseries.add(new Month(3, 1998), 161.536D);
		timeseries.add(new Month(4, 1998), 145.92400000000001D);
		timeseries.add(new Month(5, 1998), 161.39500000000001D);
		timeseries.add(new Month(6, 1998), 171.78399999999999D);
		timeseries.add(new Month(7, 1998), 154.50700000000001D);
		timeseries.add(new Month(8, 1998), 160.13300000000001D);
		timeseries.add(new Month(9, 1998), 113.047D);
		timeseries.add(new Month(10, 1998), 132.03D);
		timeseries.add(new Month(11, 1998), 129.357D);
		timeseries.add(new Month(12, 1998), 111.82899999999999D);
		timeseries.add(new Month(1, 1999), 120.053D);
		timeseries.add(new Month(2, 1999), 101.27800000000001D);
		timeseries.add(new Month(3, 1999), 109.18899999999999D);
		timeseries.add(new Month(4, 1999), 85.944999999999993D);
		timeseries.add(new Month(5, 1999), 97.841999999999999D);
		timeseries.add(new Month(6, 1999), 90.844999999999999D);
		timeseries.add(new Month(7, 1999), 110.91800000000001D);
		timeseries.add(new Month(8, 1999), 107.833D);
		timeseries.add(new Month(9, 1999), 130.078D);
		timeseries.add(new Month(10, 1999), 120.47199999999999D);
		timeseries.add(new Month(11, 1999), 101.621D);
		timeseries.add(new Month(12, 1999), 161.874D);
		timeseries.add(new Month(1, 2000), 101.16800000000001D);
		timeseries.add(new Month(2, 2000), 113.387D);
		timeseries.add(new Month(3, 2000), 121.777D);
		timeseries.add(new Month(4, 2000), 99.268000000000001D);
		timeseries.add(new Month(5, 2000), 42.972000000000001D);
		timeseries.add(new Month(6, 2000), 47.158000000000001D);
		timeseries.add(new Month(7, 2000), 20.151D);
		timeseries.add(new Month(8, 2000), 5.4359999999999999D);
		timeseries.add(new Month(9, 2000), 17.907D);
		timeseries.add(new Month(10, 2000), -22.399000000000001D);
		timeseries.add(new Month(11, 2000), 16.099D);
		timeseries.add(new Month(12, 2000), -113.875D);
		timeseries.add(new Month(1, 2001), 4.7859999999999996D);
		timeseries.add(new Month(2, 2001), 0.52600000000000002D);
		timeseries.add(new Month(3, 2001), 0.34799999999999998D);
		timeseries.add(new Month(4, 2001), -23.760000000000002D);
		timeseries.add(new Month(5, 2001), 9.0120000000000005D);
		timeseries.add(new Month(6, 2001), 40.877000000000002D);
		timeseries.add(new Month(7, 2001), 59.496000000000002D);
		timeseries.add(new Month(8, 2001), 92.054000000000002D);
		timeseries.add(new Month(9, 2001), 133.285D);
		timeseries.add(new Month(10, 2001), 158.655D);
		timeseries.add(new Month(11, 2001), 179.19800000000001D);
		timeseries.add(new Month(12, 2001), 281.22300000000001D);
		timeseries.add(new Month(1, 2002), 221.15799999999999D);
		timeseries.add(new Month(2, 2002), 267.59399999999999D);
		timeseries.add(new Month(3, 2002), 232.292D);
		timeseries.add(new Month(4, 2002), 323.32900000000001D);
		timeseries.add(new Month(5, 2002), 363.14999999999998D);
		timeseries.add(new Month(6, 2002), 399.654D);
		timeseries.add(new Month(7, 2002), 441.43799999999999D);
		timeseries.add(new Month(8, 2002), 440.60599999999999D);
		timeseries.add(new Month(9, 2002), 420.77300000000002D);
		timeseries.add(new Month(10, 2002), 466.54500000000002D);
		timeseries.add(new Month(11, 2002), 454.56299999999999D);
		timeseries.add(new Month(12, 2002), 462.26799999999997D);
		timeseries.add(new Month(1, 2003), 464.14800000000002D);
		timeseries.add(new Month(2, 2003), 442.33699999999999D);
		timeseries.add(new Month(3, 2003), 454.74400000000003D);
		timeseries.add(new Month(4, 2003), 475.70400000000001D);
		timeseries.add(new Month(5, 2003), 538.81500000000005D);
		timeseries.add(new Month(6, 2003), 543.65200000000004D);
		timeseries.add(new Month(7, 2003), 591.45399999999995D);
		timeseries.add(new Month(8, 2003), 579.55899999999997D);
		timeseries.add(new Month(9, 2003), 554.995D);
		timeseries.add(new Month(10, 2003), 590.14800000000002D);
		timeseries.add(new Month(11, 2003), 581.60500000000002D);
		timeseries.add(new Month(12, 2003), 592.25699999999995D);
		timeseries.add(new Month(1, 2004), 607.85799999999995D);
		timeseries.add(new Month(2, 2004), 646.15300000000002D);
		timeseries.add(new Month(3, 2004), 670.29200000000003D);
		timeseries.add(new Month(4, 2004), 673.40800000000002D);
		timeseries.add(new Month(5, 2004), 638.23599999999999D);
		timeseries.add(new Month(6, 2004), 604.21400000000006D);
		timeseries.add(new Month(7, 2004), 565.37300000000005D);
		timeseries.add(new Month(8, 2004), 560.90899999999999D);
		timeseries.add(new Month(9, 2004), 595.822D);
		timeseries.add(new Month(10, 2004), 557.00099999999998D);
		timeseries.add(new Month(11, 2004), 600.14499999999998D);
		timeseries.add(new Month(12, 2004), 598.17999999999995D);
		timeseries.add(new Month(1, 2005), 618.50800000000004D);
		timeseries.add(new Month(2, 2005), 621.19500000000005D);
		timeseries.add(new Month(3, 2005), 645.87099999999998D);
		timeseries.add(new Month(4, 2005), 630.74800000000005D);
		timeseries.add(new Month(5, 2005), 581.49699999999996D);
		timeseries.add(new Month(6, 2005), 562.16099999999994D);
		timeseries.add(new Month(7, 2005), 571.04999999999995D);
		timeseries.add(new Month(8, 2005), 575.98299999999995D);
		timeseries.add(new Month(9, 2005), 553.65700000000004D);
		timeseries.add(new Month(10, 2005), 597.44600000000003D);
		timeseries.add(new Month(11, 2005), 567.11199999999997D);
		timeseries.add(new Month(12, 2005), 574.26999999999998D);
		timeseries.add(new Month(1, 2006), 568.327D);
		timeseries.add(new Month(2, 2006), 556.74800000000005D);
		timeseries.add(new Month(3, 2006), 594.21699999999998D);
		timeseries.add(new Month(4, 2006), 591.18100000000004D);
		timeseries.add(new Month(5, 2006), 578.89700000000005D);
		timeseries.add(new Month(6, 2006), 583.54600000000005D);
		timeseries.add(new Month(7, 2006), 556.72900000000004D);
		timeseries.add(new Month(8, 2006), 588.101D);
		timeseries.add(new Month(9, 2006), 574.26400000000001D);
		timeseries.add(new Month(10, 2006), 557.20600000000002D);
		timeseries.add(new Month(11, 2006), 540.92399999999998D);
		timeseries.add(new Month(12, 2006), 509.81D);
		timeseries.add(new Month(1, 2007), 511.49099999999999D);
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
}
