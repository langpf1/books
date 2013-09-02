/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-8-3 上午09:03:44
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import layout.TableLayout;

/**
 * 地图控制层
 * @author 徐骏
 * @data   2010-8-3
 */
public class MapControlPanel extends JInternalFrame
{
	private JTextField quickSearch = new JTextField();
	private JSlider pSlider = new JSlider(0, 35, 0);
	private JLabel pLabel = new JLabel((new StringBuilder()).append(pSlider.getValue()).append("  公里").toString());
	private JSlider bSlider = new JSlider(0, 100, 100);
	private JLabel bLabel = new JLabel("背景 1");
	private JSlider eSlider = new JSlider(0, 100, 100);
	private JLabel eLabel = new JLabel("节点 1");
	private JCheckBox movable = new JCheckBox("启用节点移动");
	//private boolean visible ;//是否可见，自己维护状态
	private Map map;
	public MapControlPanel(String title,Map map)
	{
		this.map = map;
		pSlider.setPaintLabels(true);
		pSlider.setPaintTicks(true);
		pSlider.setMinorTickSpacing(1);
		pSlider.setMajorTickSpacing(5);
		pSlider.setSnapToTicks(true);
		
		double size[][] =
        {{-2D,5, -1D},  // Columns
         {-2D, -2D, -2D, -2D, -2D}}; // Rows
		TableLayout layout = new TableLayout(size);
		
		JPanel controlPane = new JPanel(layout);
		controlPane.add(new JLabel("快速查询"), "0,0");
		controlPane.add(quickSearch, "2,0");
		controlPane.add(pLabel, "0,1");
		controlPane.add(pSlider, "2,1");
		controlPane.add(bLabel, "0,2");
		controlPane.add(bSlider, "2,2");
		controlPane.add(eLabel, "0,3");
		controlPane.add(eSlider, "2,3");
		controlPane.add(movable, "2,4");
		controlPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
		getContentPane().add(controlPane,"Center");
		setTitle(title);
		pack();
		setLocation(30, 30);
		//setVisible(true);
		
		setActionListener();
	}
	private void setActionListener()
	{
		//Node移动
		movable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map.setNodeEnableMove(movable.isSelected());
			}
		});
	}
	
}
