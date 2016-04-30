package tcpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
		// 客户端请求(主机)(端口)建立连接请求
    	Socket client = new Socket("127.0.0.1", 20006);
    	client.setSoTimeout(10000);
    	//获取键盘输入
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	//获取Socket输出流，将数据发送到服务器端
    	PrintStream out = new PrintStream(client.getOutputStream());
    	//获取Socket输入流，获取服务端传输过来的数据
    	BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
    	boolean flag = true;
    	while(flag) {
    		System.out.print("输入信息：");
    		String str = input.readLine();
    		//发送数据到服务器端
    		out.println(str);
    		
    		//断开连接
    		if("bye".equals(str)) {
    			flag = false;
    		} else {
    			
    			try {
    				//从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常  
    				String echo = buf.readLine();
    				System.out.println(echo);
    			} catch (SocketTimeoutException e) {
    				System.out.println("Time out, No response"); 
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	input.close();
    	if(client != null){  
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭  
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭  
        }  
	}
}
