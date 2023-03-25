package mythread;
import java.net.ServerSocket;
import java.net.Socket;

public class BaseServerListener extends Thread{
    private final ServerSocket ser_sock;
    //private final BaseServer;

    public BaseServerListener(ServerSocket ser_sock) {
        this.ser_sock = ser_sock;
        //this.baseServer = baseServer;
    }


    public void run(){
        String clt_add;
        try{
            System.out.println("本地域名服务器开始监听");
            while(true){
                try{
                    Socket clt_sock = ser_sock.accept();    //程序阻塞并等待连接请求
                    clt_add = clt_sock.getRemoteSocketAddress().toString();
                    System.out.println("从 " + clt_add + " 收到建立连接的请求");
                    System.out.println("connect " + clt_add + " successfully");

                    SolveRequest sr = new SolveRequest(clt_sock);
                    sr.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
