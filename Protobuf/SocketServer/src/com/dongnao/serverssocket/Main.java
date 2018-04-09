package com.dongnao.serverssocket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 创建socket服务
			serverSocket = new ServerSocket(8888);
			while (true) {
				System.out.println("wait client...");
				// 客户端的socket对象
				Socket client = null;
				try {
					// 阻塞 等待客户端的连接
					client = serverSocket.accept();
					System.out.println("client connect......");
					// 客户端数据读取线程
					new ClientReadTask(client).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
