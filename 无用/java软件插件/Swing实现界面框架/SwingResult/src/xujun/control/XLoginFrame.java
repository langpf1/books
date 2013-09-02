/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-8 下午01:50:48
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import xujun.control.tab.XTabPanel;
import layout.TableLayout;

import com.sun.awt.AWTUtilities;

/**
 * @author 徐骏
 * @data   2010-7-8
 */
public class XLoginFrame extends JFrame
{
	private int width;
	private int height;
	private ImageIcon buttonIcon;
	private ImageIcon buttonRoverIcon;
	private ImageIcon buttonPressedIcon;
	private ImageIcon logoIcon;
	private ImageIcon logoRoverIcon;
	private JLabel logoLabel;
	private JButton btnLogin;
	private JPanel inputPane;
	private MouseAdapter moveWindowListener;
	
	public XLoginFrame()
	{
		width = 332;
		height = 344;
		buttonIcon = XContorlUtil.getImageIcon("xujun/control/images/login_button.png");
		buttonRoverIcon = XContorlUtil.getImageIcon("xujun/control/images/login_button_rover.png");
		buttonPressedIcon = XContorlUtil.getImageIcon("xujun/control/images/login_button_pressed.png");
		logoIcon = XContorlUtil.getImageIcon("xujun/control/images/login_logo.png");

		logoRoverIcon = XContorlUtil.getImageIcon("xujun/control/images/login_logo_rover.png");
		logoLabel = createDraggableLabel(logoIcon);
		btnLogin = new JButton();
		inputPane = new JPanel() {
			private TexturePaint paint = XContorlUtil.createTexturePaint("xujun/control/images/login_background.png");
	
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D)g;
				g2d.setPaint(paint);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		moveWindowListener = new MouseAdapter() {

			private Point lastPoint;
			public void mousePressed(MouseEvent e)
			{
				lastPoint = e.getLocationOnScreen();
			}

			public void mouseDragged(MouseEvent e)
			{
				Point point = e.getLocationOnScreen();
				int offsetX = point.x - lastPoint.x;
				int offsetY = point.y - lastPoint.y;
				Rectangle bounds = getBounds();
				bounds.x += offsetX;
				bounds.y += offsetY;
				setBounds(bounds);
				lastPoint = point;
			}

			public void mouseEntered(MouseEvent e)
			{
				if (e.getSource() == logoLabel)
					logoLabel.setIcon(logoRoverIcon);
			}

			public void mouseExited(MouseEvent e)
			{
				if (e.getSource() == logoLabel)
					logoLabel.setIcon(logoIcon);
			}
		};
		init();
		
	}

	private void init()
	{
		setDefaultCloseOperation(2);
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		JPanel centerPane = new JPanel(new BorderLayout());
		centerPane.add(btnLogin, "South");
		setContentPane(centerPane);
		setSize(width, height);
		//窗体屏幕居中
		setLocationRelativeTo(null);
		btnLogin.setBorder(null);
		btnLogin.setMargin(null);
		btnLogin.setOpaque(false);
		btnLogin.setIcon(buttonIcon);
		btnLogin.setRolloverEnabled(true);
		btnLogin.setRolloverIcon(buttonRoverIcon);
		btnLogin.setPressedIcon(buttonPressedIcon);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setRequestFocusEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				login();
			}
		});
		JPanel topPane = new JPanel(new BorderLayout());
		logoLabel.setOpaque(false);
		topPane.setOpaque(false);
		logoLabel.addMouseListener(moveWindowListener);
		logoLabel.addMouseMotionListener(moveWindowListener);
		topPane.addMouseListener(moveWindowListener);
		topPane.addMouseMotionListener(moveWindowListener);
		topPane.add(logoLabel, "Center");
		topPane.add(createDraggableLabel(XContorlUtil.getImageIcon("xujun/control/images/login_left_top.png")), "West");
		topPane.add(createDraggableLabel(XContorlUtil.getImageIcon("xujun/control/images/login_right_top.png")), "East");
	

		centerPane.add(createDraggableLabel(XContorlUtil.getImageIcon("xujun/control/images/login_left.png")), "West");
		centerPane.add(createDraggableLabel(XContorlUtil.getImageIcon("xujun/control/images/login_right.png")), "East");
		centerPane.add(topPane, "North");
		centerPane.add(inputPane, "Center");
		inputPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		int rowHeight = 18;
		int sepHeight = 20;
		 double size[][] =
         {{0.3,0.1, 0.6},  // Columns
          {50,rowHeight,sepHeight,rowHeight,sepHeight,22}}; // Rows
		inputPane.setLayout(new TableLayout(size));
		inputPane.add (createInputLabel("Username:"), "0, 1"); // 第一列第二行
		inputPane.add(new XTextField("xujun"),"2,1"); // 第三列第二行
		inputPane.add(createInputLabel("Password:"),"0,3"); // 第一列第四行
		inputPane.add(new XTextField(),"2,3"); // 第二列第四行
		
		JCheckBox cbRememberMe = new JCheckBox("Remember me");
		cbRememberMe.setOpaque(false);
		setupComponent(cbRememberMe);
		inputPane.add(cbRememberMe,"2,5");
		inputPane.addMouseListener(moveWindowListener);
		inputPane.addMouseMotionListener(moveWindowListener);
	}

	private JLabel createDraggableLabel(ImageIcon icon)
	{
		JLabel label = new JLabel(icon);
		label.addMouseListener(moveWindowListener);
		label.addMouseMotionListener(moveWindowListener);
		return label;
	}

	private JLabel createInputLabel(String text)
	{
		JLabel label = new JLabel(text);
		setupComponent(label);
		return label;
	}

	private void setupComponent(JComponent c)
	{
		c.setFont(XContorlUtil.FONT_14_BOLD);
		c.setForeground(XContorlUtil.DEFAULT_TEXT_COLOR);
	}

	protected void login()
	{
		XLoadingDialog loadingDialog = XLoadingDialog.createInstance(this);
		new LoadWorker(loadingDialog).execute();
	}
	//参数1是最终结果类型，参数2是中间结果类型
	class LoadWorker extends SwingWorker<JFrame, Integer>{
		private XLoadingDialog loadingDialog;
		public LoadWorker(XLoadingDialog loadingDialog)
		{
			this.loadingDialog = loadingDialog;
		}
		@Override
		protected JFrame doInBackground() throws Exception
		{
			loadingDialog.setVisible(true);
			//如果用户取消该线程，就不继续执行。因为本界面上并没有加入Cancel按钮，其实不需要该判断
			if(!isCancelled())
			{
				//模拟一个耗时工作
				Thread.sleep(1000);
				publish(10);
				Thread.sleep(1000);
				publish(30);
				Thread.sleep(1000);
				publish(70);
				Thread.sleep(1000);
				publish(100);
				return new Main();
			}
			return null;
		}
		/* 
		 * 处理线程的中间结果，该处理是在EDT上执行的，所以可以调用EDT上的控件,
		 * 我这里只是模拟了一个进度显示，实际上可以显示在进度条上，从而实现象eclipse那样的启动加载界面
		 */
		@Override
		protected void process(List<Integer> midData)
		{
			if(!isCancelled())
			{
				System.out.println(midData);
			}
		}
		/*
		 *  处理线程的最终结果
		 * */
		@Override
		protected void done()
		{
			JFrame mainFrame = null;
			try
			{
				mainFrame = get();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				e.printStackTrace();
			}
			this.loadingDialog.setVisible(false);
			this.loadingDialog.dispose();
			mainFrame.setVisible(true);
		}
	}
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable() {

			public void run()
			{
				XLoginFrame ui = new XLoginFrame();
				ui.setVisible(true);
			}

		});
	}


}
