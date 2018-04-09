package com.dongnao.serverssocket.parser;

import java.io.IOException;
import java.io.InputStream;

import com.dongnao.serverssocket.ByteBuf;
import com.example.tutorial.MsgProtos.Msg;
import com.google.protobuf.CodedInputStream;

public class ProtobufParser {

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

	static void processCache(ByteBuf cache) throws IOException {
		// 包头最大5个字节
		byte[] buf = new byte[5];
		for (int i = 0; i < buf.length; ++i) {
			// 如果cache中 不可以读取数据了
			if (!cache.isReadable()) {
				cache.resetReadIndex();
				return;
			}
			//1-5个字节都有可能
			// 读取一个字节
			buf[i] = cache.get();
			//解码出长度 
			int length = CodedInputStream.newInstance(buf, 0, i + 1).readRawVarint32();
			//可能就是包头=包体长度
			if (length > 0) {
				//尝试去解析 protobuf 包体
				//如果可读数据小于 包长度 
				if(cache.readableBytes() < length) {
					cache.resetReadIndex();
					return;
				}
				//读取length 个 字节数据
				byte[] b = cache.get(length);
				//处理完的数据 丢掉 不然cache会越来越大
				cache.discardRead();
				//反序列化
				Msg msg = Msg.parseFrom(b);
				System.out.println(msg.getText());
			}

		}
	}
}
