package com.mei.test.optimize.handler;

/**
 * Created by mei on 2018/3/8.
 * Description:
 */

public class MessageQueue {

    Message mMessage;

    boolean isQuit;//无限循环的退出标志

    /**
     * 将消息加入到queue
     *
     * @param message
     */
    public void enqueueMessage(Message message) {
        synchronized (this) {
            if (isQuit) {//如果退出了，就不在接收数据了
                return;
            }
            Message p = mMessage;
            if (p == null) {//表示队列还是空的
                mMessage = message;
            } else {
                //循环判断，是否在链表尾端
                //将新加入的Message加入到队尾
                Message prev;
                for (; ; ) {
                    prev = p;
                    //将p指向下一条消息
                    p = p.next;
                    if (null == p) {//说明是最后一个元素了
                        break;
                    }
                }
                prev.next = message;
            }

            //通知获取message，解除阻塞
            notify();
        }
    }

    /**
     * 从消息队列中，获取消息
     *
     * @return
     */
    Message next() {
        synchronized (this) {
            Message p;
            for (; ; ) {
                //如果调用过quit()，就直接返回null
                if (isQuit) {
                    return null;
                }
                //如果获取到message就可以return
                p = this.mMessage;
                if (p != null) {
                    break;
                }
                try {//阻塞
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将队列的表头移动到下一个节点
            mMessage = mMessage.next;
            return p;

        }
    }

    /**
     * 退出
     */
    public void quit() {
        synchronized (this) {
            isQuit = true;
            Message message = mMessage;
            while (null != message) {
                //获得下一个message
                Message next = message.next;
                //释放message 清空
                message.recycle();
                //将message指向下一条message
                message = next;
            }

            notify();//如果是阻塞的话，就先解除阻塞

        }
    }
}
