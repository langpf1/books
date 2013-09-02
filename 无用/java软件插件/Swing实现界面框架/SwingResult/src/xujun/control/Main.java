/**   
 * @Title: Main.java
 * @Package xujun.control
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐骏  
 * @date 2010-7-1 下午08:23:53
 * @version V1.0   
 */

package xujun.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.TabbedPaneUI;
import javax.xml.parsers.ParserConfigurationException;

import xujun.control.chart.ChartPanelTest1;
import xujun.control.chart.ChartPanelTest2;
import xujun.control.chart.XChartPanel;
import xujun.control.map.Map;
import xujun.control.map.XMap;
import xujun.control.menu.XMenuBar;
import xujun.control.outlookpanel.XOutlookPanel;
import xujun.control.outlookpanel.XOutlookPanelListItem;
import xujun.control.shortcut.XShortcutItem;
import xujun.control.shortcut.XShortcutItemClickListenter;
import xujun.control.shortcut.XShortcutPanel;
import xujun.control.statusbar.XStatusBar;
import xujun.control.tab.XTabPage;
import xujun.control.tab.XTabPanel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceGreen;




/**
 * @ClassName: Main
 * @Description: 开源SWING框架
 * @date 2010-7-1 下午08:23:53
 * @author xujun
 */
public class Main extends JFrame
{
	private String menuBarXML;
	private String outlookPanelXML;
	
	private XContentPanel contentPanel;
	private XMenuBar menubar;
	private XOutlookPanel outlookPanel;
	private XTabPanel tabPanel;
	private XStatusBar statusBar;
	private XShortcutPanel shortcutPanel;
	
	public Main()
	{
		menuBarXML = "xujun/control/menubar.xml";
		outlookPanelXML = "xujun/control/outlook.xml";
		menubar = XContorlUtil.loadMenuBar(menuBarXML, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				System.out.println("Menu:"+command);
			}
		});
		contentPanel = new XContentPanel();
		initSwing();
	//	XContorlUtil.setupLookAndFeel();
	}
	private void initSwing()
	{
		setTitle("Swing Result");
		setDefaultCloseOperation(3);
		setSize(1024, 768);
		setIconImage(XContorlUtil.getImage("xujun/control/Images/logo.png"));
		setContentPane(contentPanel);
		contentPanel.add(menubar, "North");
		//除却菜单栏之外的面板
		JPanel centerPane = new JPanel(new BorderLayout());
		centerPane.setOpaque(true);
		centerPane.setBackground(XContorlUtil.CONTENT_PANE_BACKGROUND);
		centerPane.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		contentPanel.add(centerPane, "Center");
		
		initTab();
		initOutlookPanel();
		initStatusbar();
		initShortcutPanel();
		
		centerPane.add(outlookPanel, "West");
		centerPane.add(tabPanel,"Center");
		centerPane.add(shortcutPanel, "East");
		
		contentPanel.add(statusBar,BorderLayout.SOUTH);
	}
	
	private void initOutlookPanel()
	{
		outlookPanel = new XOutlookPanel(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				System.out.println("Head:"+command);
			}
			
		}
		,
		new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				boolean adjust = e.getValueIsAdjusting();
				 if(!adjust)
				 {
					  JList list = (JList) e.getSource();
			          Object selectionValues[] = list.getSelectedValues();
			          XOutlookPanelListItem item = (XOutlookPanelListItem)selectionValues[0];
			          String command = item.getActionCommand();
			          if(command.equals("Chart1"))
			          {
			        	  //如果TabPanel不存在就create，否则就show
			        	  if(!tabPanel.isSelectTabComponents("图形模版一"))
			        	  {
			        		  tabPanel.addTab("图形模版一", createPage(new ChartPanelTest1().getChartPanel()));
			        		  tabPanel.isSelectTabComponents("图形模版一");
			        	  }
			          }
			          else if(command.equals("Chart2"))
			          {
			        	  if(!tabPanel.isSelectTabComponents("图形模版二"))
			        	  {
			        		  tabPanel.addTab("图形模版二", createPage(new ChartPanelTest2().getChartPanel()));
			        		  tabPanel.isSelectTabComponents("图形模版二");
			        	  }
			          }
			          else if(command.equals("Map"))
			          {
			        	  if(!tabPanel.isSelectTabComponents("区域地图模版"))
			        	  {
			        		  tabPanel.addTab("区域地图模版", createPage(new XMap()));	
			        		  tabPanel.isSelectTabComponents("区域地图模版");
			        	  }
			          }
				 }
			}
			
		}
		);
		XContorlUtil.loadOutlookPanel(outlookPanelXML, outlookPanel);
	}
	private void initTab()
	{
		tabPanel = new XTabPanel();
		tabPanel.addMouseListener(new MouseAdapter()
		{
			private boolean isMaximized()
			{
				return outlookPanel.isShrinked();
			}
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() > 1)
				{
					TabbedPaneUI ui = tabPanel.getUI();
					int tabIndex = ui.tabForCoordinate(tabPanel, e.getX(), e.getY());
					if (tabIndex != -1)
					{
						boolean maxed = isMaximized();
						outlookPanel.setShrink(!maxed);
						shortcutPanel.setShrink(!maxed);
					}
				}
			}
		});
		tabPanel.addTab("图形模版一", createPage(new ChartPanelTest1().getChartPanel()));
		tabPanel.addTab("图形模版二", createPage(new ChartPanelTest2().getChartPanel()));	
		tabPanel.addTab("区域地图模版", createPage(new XMap()));	
	}
	private XTabPage createPage(JComponent pageContent)
	{
		XTabPage page = new XTabPage(pageContent);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/home.png"), "home", "home", false);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/left.png"), "left", "left", true);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/right.png"), "right", "right", true);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/add.png"), "add", "add", true);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/update.png"), "update", "update", true);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/refresh.png"), "refresh", "refresh", true);
		page.getToolBar().addButton(XContorlUtil.getImageIcon("xujun/control/images/toolbar/print.png"), "print", "print", true);
		return page;
	}
	
	private void initStatusbar()
	{
		statusBar = new XStatusBar();
	}
	private void initShortcutPanel()
	{
		XShortcutItem[] items = new XShortcutItem[8];
		items[0] = new XShortcutItem();
		items[0].setGroup(true);
		items[0].setText("我的工作台");
		items[0].setToolTip("hello");
		items[0].setActionCommand("shortcut Group1");
		
		items[1] = new XShortcutItem();
		items[1].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[1].setText("代办事宜");
		items[1].setToolTip("hello");
		items[1].setActionCommand("1-1");
		
		items[2] = new XShortcutItem();
		items[2].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[2].setText("紧急通知");
		items[2].setToolTip("hello");
		items[2].setActionCommand("1-2");
		
		items[3] = new XShortcutItem();
		items[3].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[3].setText("邮件");
		items[3].setToolTip("hello");
		items[3].setActionCommand("1-3");
		
		items[4] = new XShortcutItem();
		items[4].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[4].setText("应发公文");
		items[4].setToolTip("hello");
		items[4].setActionCommand("1-4");
		
		items[5] = new XShortcutItem();
		items[5].setGroup(true);
		items[5].setText("工作流引擎");
		items[5].setToolTip("hello");
		items[5].setActionCommand("croup2");
		
		items[6] = new XShortcutItem();
		items[6].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[6].setText("当前节点所处流程位置");
		items[6].setToolTip("hello");
		items[6].setActionCommand("2-1");
		
		items[7] = new XShortcutItem();
		items[7].setIcon(XContorlUtil.getImageIcon("xujun/control/images/email.png"));
		items[7].setText("前置和拦截条件");
		items[7].setToolTip("hello");
		items[7].setActionCommand("2-1");
		
		shortcutPanel = new XShortcutPanel();
		shortcutPanel.setData(items, new XShortcutItemClickListenter(){

			@Override
			public void ItemClick(String actionCommand)
			{
				System.out.println(actionCommand);
			}
			
		});

		shortcutPanel.setTitle("快捷工作台");
	}
	
	
	
	public static void main(String args[]) throws ParserConfigurationException
	{
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				XContorlUtil.setupLookAndFeel();
				Main ui = new Main();
				ui.setVisible(true);
			}
		});
	}
}
