package com.dongnao.serverssocket;

/**
 * Created by Lance on 2018/2/1 0002.
 * <p>
 * 
 * @author Lance nio bytebuffer不好用 自己定义一个 增加自动扩容以及用到的一些小函数
 */
public class ByteBuf {

	private byte[] data;
	// 读写下标 标识已读 已写了多少数据
	//
	private int readIndex;
	private int writeIndex;

	private ByteBuf(byte[] data) {
		this.data = data;
		readIndex = 0;
		writeIndex = 0;
	}

	public static ByteBuf allocate(int len) {
		return new ByteBuf(new byte[len]);
	}

	/**
	 * 重置 读数据的下标
	 */
	public void resetReadIndex() {
		readIndex = 0;
	}

	/**
	 * 可读的数据 就是写了多少数据
	 *
	 * @return
	 */
	public int readableBytes() {
		return writeIndex - readIndex;
	}

	/**
	 * 是否有可读数据
	 * 
	 * @return
	 */
	public boolean isReadable() {
		return writeIndex > readIndex;
	}

	/**
	 * 加入1个字节
	 *
	 * @param b
	 */
	public void put(byte b) {
		// 剩余空间不足以存放这么多数据 扩容
		while (data.length - writeIndex < 1) {
			resize();
		}
		data[writeIndex] = b;
		// 写入数据 +1
		writeIndex += 1;
	}

	/**
	 * 加入数据
	 *
	 * @param bytes
	 * @param offset
	 * @param len
	 */
	public void put(byte[] bytes, int offset, int len) {
		// 剩余空间不足以存放这么多数据 扩容
		while (data.length - writeIndex < len) {
			resize();
		}
		System.arraycopy(bytes, offset, data, writeIndex, len);
		writeIndex += len;
	}

	/**
	 * 读取指定长度的数据
	 *
	 * @param len
	 * @return
	 */
	public byte[] get(int len) {
		byte[] bytes = new byte[len];
		System.arraycopy(data, readIndex, bytes, 0, len);
		readIndex += len;
		return bytes;
	}

	public byte get() {
		byte b = data[readIndex++];
		return b;
	}

	/**
	 * 扩容 写死每次扩容 1024
	 */
	private void resize() {
		// 每次固定扩容
		byte[] bytes = new byte[data.length + 1024];
		System.arraycopy(data, 0, bytes, 0, writeIndex);
		data = bytes;
	}

	/**
	 * 丢弃已读
	 */
	public void discardRead() {
		byte[] d = new byte[writeIndex - readIndex];
		System.arraycopy(data, readIndex, d, 0, d.length);
		data = d;
		readIndex = 0;
		writeIndex = d.length;
	}

}
