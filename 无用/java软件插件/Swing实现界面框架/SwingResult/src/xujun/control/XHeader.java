/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-2 上午09:41:46
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;



/**
 * @author 徐骏
 * @data   2010-7-2
 */
public class XHeader extends JPanel
{
	public static final ImageIcon RIGHT_ARROW_ICON = XContorlUtil.getImageIcon("xujun/control/images/shrink_handler_right.png");
	public static final ImageIcon LEFT_ARROW_ICON = XContorlUtil.getImageIcon("xujun/control/images/shrink_handler_left.png");
	private Color titleColor;
	private boolean shrinked;
	private ImageIcon backgroundImageIcon;
	private Image backgroundLeftImage;
	private Image backgroundRightImage;
	private TexturePaint paint;
	private int preferredHeight;
	private JLabel lbResizeHandler;
	private JLabel lbShrinkHandler;
	private JLabel lbTitle;
	private int normalPreferredWidth;
	private XListSplitListener splitListener;
	private MouseListener shrinkListener;

	public XHeader()
	{
		super();
		titleColor = new Color(215, 215, 216);
		shrinked = false;
		backgroundImageIcon = XContorlUtil.getImageIcon("xujun/control/images/header_background.png");
		backgroundLeftImage = XContorlUtil.getImage("xujun/control/images/header_background_left.png");
		backgroundRightImage = XContorlUtil.getImage("xujun/control/images/header_background_right.png");
		paint = XContorlUtil.createTexturePaint("xujun/control/images/header_background.png");
		preferredHeight = backgroundImageIcon.getIconHeight();
		lbResizeHandler = new JLabel(XContorlUtil.getImageIcon("xujun/control/images/resize_handler.png"));
		lbShrinkHandler = new JLabel(getShrinkIcon(shrinked));
		lbTitle = new JLabel();
		normalPreferredWidth = 0;
		splitListener = createSplitListener();
		shrinkListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				changeShrink();
			}
		};
		init();
	}

	protected XListSplitListener createSplitListener()
	{
		return new XListSplitListener(this);
	}

	protected Border createBorder()
	{
		return BorderFactory.createEmptyBorder(4, 7, 0, 0);
	}

	private void init()
	{
		setBorder(createBorder());
		setOpaque(false);
		setLayout(new BorderLayout());
		add(lbResizeHandler, getResizeHandlerLayoutConstraint());
		add(lbShrinkHandler, getShrinkHandlerLayoutConstraint());
		JComponent centerComponent = getCenterComponent();
		if (centerComponent != null)
			add(centerComponent, "Center");
		lbResizeHandler.addMouseMotionListener(splitListener);
		lbResizeHandler.addMouseListener(splitListener);
		lbShrinkHandler.addMouseListener(shrinkListener);
		lbTitle.setFont(XContorlUtil.FONT_14_BOLD);
		lbTitle.setForeground(titleColor);
		lbTitle.setBorder(BorderFactory.createEmptyBorder(2, 8, 0, 0));
		updateCursor();
		lbShrinkHandler.setBorder(BorderFactory.createEmptyBorder(0, 8, 2, 5));
	}

	protected JComponent getCenterComponent()
	{
		return lbTitle;
	}

	protected Object getResizeHandlerLayoutConstraint()
	{
		return "West";
	}

	protected Object getShrinkHandlerLayoutConstraint()
	{
		return "East";
	}

	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(backgroundLeftImage, 0, 0, null);
		int x = getWidth() - backgroundRightImage.getWidth(null);
		int y = 0;
		g2d.drawImage(backgroundRightImage, x, y, null);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(super.getPreferredSize().width, preferredHeight);
	}

	public void revalidateParent()
	{
		if (getParent() instanceof JComponent)
			((JComponent)getParent()).revalidate();
	}

	public void changeShrink()
	{
		setShrink(!isShrinked());
	}

	public void setShrink(boolean shrinked)
	{
		if (shrinked != this.shrinked)
		{
			Container parent = getParent();
			Dimension size = parent.getPreferredSize();
			if (shrinked)
			{
				normalPreferredWidth = size.width;
				size = new Dimension(getShrinkedWidth(), size.height);
			} else
			{
				int width = normalPreferredWidth;
				int height = parent.getPreferredSize().height;
				size = new Dimension(width, height);
			}
			parent.setPreferredSize(size);
			lbShrinkHandler.setIcon(getShrinkIcon(shrinked));
			revalidateParent();
			this.shrinked = shrinked;
			updateCursor();
			lbTitle.setVisible(!shrinked);
			lbResizeHandler.setVisible(!shrinked);
		}
	}

	protected ImageIcon getShrinkIcon(boolean shrinked)
	{
		if (shrinked)
			return LEFT_ARROW_ICON;
		else
			return RIGHT_ARROW_ICON;
	}

	private void updateCursor()
	{
		if (shrinked)
			lbResizeHandler.setCursor(Cursor.getDefaultCursor());
		else
			lbResizeHandler.setCursor(Cursor.getPredefinedCursor(10));
	}

	public boolean isShrinked()
	{
		return shrinked;
	}

	public void setTitle(String title)
	{
		lbTitle.setText(title);
	}

	public String getTitle()
	{
		return lbTitle.getText();
	}

	protected int getShrinkedWidth()
	{
		return 37;
	}
}
