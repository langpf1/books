/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-8 下午01:35:57
 * copyright Anymusic Ltd.
 */
package xujun.control;

import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

/**
 * 系统启动时的进度条
 * @author 徐骏
 * @data   2010-7-8
 */
public class XLoadingDialog extends JDialog
{

	private ImageIcon imageIcon;
	private int imageWidth;
	private int imageHeight;
	private static XLoadingDialog instance = null;

	public XLoadingDialog(Dialog parent)
	{
		super(parent);
		imageIcon = XContorlUtil.getImageIcon("xujun/control/images/loading.gif");
		imageWidth = imageIcon.getIconWidth();
		imageHeight = imageIcon.getIconHeight();
		init();
		parent.setVisible(false);
	}

	public XLoadingDialog(Frame parent)
	{
		super(parent);
		imageIcon = XContorlUtil.getImageIcon("xujun/control/images/loading.gif");
		imageWidth = imageIcon.getIconWidth();
		imageHeight = imageIcon.getIconHeight();
		init();
		parent.setVisible(false);
	}

	private void init()
	{
		//setModal(true);//使用模式的话，线程会被堵塞
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		JLabel label = new JLabel(imageIcon);
		getContentPane().add(label, "Center");
		setDefaultCloseOperation(2);
		setSize(imageWidth, imageHeight);
		setLocationRelativeTo(null);
		
	}

	public static XLoadingDialog showInstance(Dialog parent)
	{
		if (instance != null && instance.isShowing())
			instance.dispose();
		instance = new XLoadingDialog(parent);
		instance.setVisible(true);
		return instance;
	}

	public static XLoadingDialog createInstance(Frame parent)
	{
		if (instance != null && instance.isShowing())
			instance.dispose();
		instance = new XLoadingDialog(parent);
		return instance;
	}
}
