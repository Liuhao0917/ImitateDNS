package serverandclient;
import cache.Cache;
import mythread.BaseServerListener;
import java.io.*;
import java.net.*;


public class BaseServer{
    private final static String ip = "127.0.0.1";                   //根域名服务器的ip地址
    private final static int port = 48888;                          //根域名服务器所在端口号
    public final static Cache cache = new Cache(7);        //高速缓存

    public static void main(String[] args) {
        try{
            ServerSocket ser_sock = new ServerSocket(49999);
            BaseServerListener baseServerListener = new BaseServerListener(ser_sock);
            baseServerListener.start();

            /*Socket clt_sock = ser_sock.accept();    //程序阻塞并等待连接请求
            System.out.println("connect success");

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(clt_sock.getOutputStream()),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clt_sock.getInputStream()));

            String requestData = br.readLine();
            System.out.println("从客户端接受的数据:"+ requestData);

            String mess = DataPackage.analysisReport(requestData);
            System.out.println(mess);

            System.out.println(br.readLine());*/


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getIp(){return ip;}

    public static int getPort(){return port;}
}
