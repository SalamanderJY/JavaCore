package udpServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
    	String str_send = "Hello UDPclient";
    	byte[] buf = new byte[1024];
    	
    	//服务端在3000端口监听接收到的数据  
        DatagramSocket datagramSocket = new DatagramSocket(3000);
        
        //接收从客户端发送过来的数据  
        DatagramPacket receive = new DatagramPacket(buf, 1024);  
        System.out.println("server is on，waiting for client to send data......");
        
        boolean flag = true;  
        while(flag) {  
            //服务器端接收来自客户端的数据  
            datagramSocket.receive(receive);  
            System.out.println("server received data from client：");  
            String str_receive = new String(receive.getData(), 0, receive.getLength()) +   
                    " from " + receive.getAddress().getHostAddress() + ":" + receive.getPort();  
            System.out.println(str_receive);  
            
            //数据发动到客户端的9000端口  
            DatagramPacket send= new DatagramPacket(
            		str_send.getBytes(),
                    str_send.length(),
                    receive.getAddress(),
                    9000);  
            datagramSocket.send(send);
            
            //由于dp_receive在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数，  
            //所以这里要将dp_receive的内部消息长度重新置为1024  
            receive.setLength(1024);  
        }  
        datagramSocket.close();  
	}
}
