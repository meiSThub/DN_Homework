package com.dongnao.serverssocket.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dongnao.serverssocket.ByteBuf;

public class NormalParser {

	public static void readMsg(InputStream is) throws IOException {
		ByteBuf cache = ByteBuf.allocate(1024);
		byte[] buffer = new byte[1024];
		int len = 0;
		// 读取数据
		while ((len = is.read(buffer)) != -1) {
			// 把数据放入缓存区 并处理
			cache.put(buffer, 0, len);
			processCache(cache);
		}
	}

	static void processCache(ByteBuf cache) {
		List<ByteBuf> packages = new ArrayList<>();
		// 可读的数据
		int readableBytes = cache.readableBytes();
		// 108,104,101,1,101,0,101,0,111,108
		
		//108,104,101,1,101
		//0,101,0,111,108 
		// 首先找到第一 108
		boolean findHead = false;
		ByteBuf pack = null;
		for (int i = 0; i < readableBytes; ++i) {
			byte b = cache.get();
			// 已经找到包头
			if (findHead) {
				// 下一个108是包尾
				if (b == 108) {
					cache.discardRead();
					findHead = false;
					packages.add(pack);
					continue;
				}
				// 数据
				if (b == 101) {
					//101  还原转义 
					++i;
					byte next = cache.get();
					if (next == 1) {
						// 101,1 = 101
						pack.put((byte) 101);
					} else if (next == 0) {
						//101,0 = 108
						pack.put((byte) 108);
					}
				} else {
					pack.put(b);
				}
			} else {
				//读到包开始标识 一个包数据开始了
				if (b == 108) {
					pack = ByteBuf.allocate(1024);
					findHead = true;
				} else {
					// 丢弃已读
					cache.discardRead();
				}
			}
		}
		cache.resetReadIndex();
		// packages: 所有完整的包数据
		for (ByteBuf p : packages) {
			byte[] bytes = p.get(p.readableBytes());
			// 获得数据
			System.out.println("recv message:" + new String(bytes));
		}
	}
	
	public static void main(String[] args) {
		ByteBuf buf = ByteBuf.allocate(1024);
		//108,104,101,1,101
		//0,101,0,111,108 
		buf.put(new byte[] {108,104,101,1,101}, 0, 5);
		System.out.println("1、read ======================");
		processCache(buf);
		System.out.println("2、read ======================");
		buf.put(new byte[] {0,101,0,111,108 }, 0, 5);
		processCache(buf);
	}
}
