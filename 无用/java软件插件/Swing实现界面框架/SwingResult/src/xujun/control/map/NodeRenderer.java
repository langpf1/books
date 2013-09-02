/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-29 上午09:36:51
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Shape;


/**
 * Node的渲染器接口
 * @author 徐骏
 * @data   2010-7-29
 */
public interface NodeRenderer 
{
	public void drawEntity(Graphics g,NodeEntity nodeEntity);
}
