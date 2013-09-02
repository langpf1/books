/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-7-28 下午02:58:31
 * copyright Anymusic Ltd.
 */
package xujun.control.map;

import java.util.EventListener;

/**
 * Map的鼠标事件监听器,将原始的鼠标事件包装处理
 * @author 徐骏
 * @data   2010-7-28
 */
public interface MapMouseListener extends EventListener
{
    void MapMouseClicked(MapMouseEvent event);
}
