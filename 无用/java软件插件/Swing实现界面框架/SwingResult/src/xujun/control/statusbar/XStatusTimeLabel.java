/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-7 上午09:13:18
 * copyright Anymusic Ltd.
 */
package xujun.control.statusbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;


/**
 * @author 徐骏
 * @data   2010-7-7
 */
public class XStatusTimeLabel extends XStatusLabel
{
	private int delay = 1000;
	public XStatusTimeLabel()
	{
		new Timer(delay,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String dateString = dateFormat.format(new Date());
				setText(dateString);
			}
			
		}).start();
	}
}
