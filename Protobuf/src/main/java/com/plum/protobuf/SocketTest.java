package com.plum.protobuf;

import android.util.Log;

import com.example.tutorial.MsgProtos;
import com.google.protobuf.CodedOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by mei on 2018/4/9.
 * Description:
 */

public class SocketTest extends Thread {

    private static final String TAG = "SocketTest";
    private byte[] byte1;

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket();
            //连接服务器
            socket.connect(new InetSocketAddress("10.0.2.2", 8888));
            //获取输出流
            OutputStream os = socket.getOutputStream();
            //1、直接发送数据
//            byte[] bytes = "hello".getBytes();
            //2、添加包数据标识位
//            byte[] bytes = getByte();
            //3、使用protobuf
            byte[] bytes = getByte1();
            for (int i = 0; i < 10; ++i) {
                os.write(bytes);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 添加包数据标识位
     * hello = [104, 101, 108, 108, 111]
     * 108,104, 101, 108, 108, 111,108
     */
    static byte[] getByte() {
        //使用 108 作为包与包之间的分割符
        //则 如果包体数据中有108 将其定义为 101,0
        // 101则定义为 101,1
        //所以最终我们需要发送的数据是
        // 108,104,101,1,101,0,101,0,111,108
        byte[] bytes = "hello".getBytes();
        //则数据最大长度可能是原数据的2倍+2 (如果数据全是 0,1)
        // 101 1
        // 101 1 = 101 1 1
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length * 2 + 2);
        //加入包头
        buffer.put((byte) 108);
        //加入包体数据
        for (int i = 0; i < bytes.length; ++i) {
            byte aByte = bytes[i];
            //108 变成 101,0
            if (aByte == 108) {
                buffer.put((byte) 101);
                buffer.put((byte) 0);
            }
            //101 变成 101,1
            else if (aByte == 101) {
                buffer.put((byte) 101);
                buffer.put((byte) 1);
            } else {
                buffer.put(aByte);
            }
        }
        //加入包尾
        buffer.put((byte) 108);
        //
        byte[] data = new byte[buffer.position()];
        buffer.flip();
        buffer.get(data);
        Log.e(TAG, Arrays.toString(data));
        return data;
    }


    /**
     * [x],104, 101, 108, 108, 111
     *
     * @return
     */
    public byte[] getByte1() throws IOException {
        MsgProtos.Msg msg = MsgProtos.Msg.newBuilder().setText("hello").build();
        //在包体前 加入一个包长度字段
        byte[] body = msg.toByteArray();
        int bodyLen = body.length;
        //java int 4个字节
        //protobuf 如果来编码这个int 数据 需要占用多少个字节
        //最大占用 5个 字节
        int headLen = CodedOutputStream.computeUInt32SizeNoTag(bodyLen);
        Log.e(TAG, "包头需要占用：" + headLen + "字节");
        byte[] head = new byte[headLen];
        //将 protobuf 编译的 bodyLen 写入到 head 里面
        CodedOutputStream cos = CodedOutputStream.newInstance(head);
        cos.writeUInt32NoTag(bodyLen);
        cos.flush();

        //head 表示包长度
        // 包总长度 包头+包体长度
        byte[] data = new byte[headLen + bodyLen];
        System.arraycopy(head, 0, data, 0, headLen);
        System.arraycopy(body, 0, data, headLen, bodyLen);
        return data;
    }
}
