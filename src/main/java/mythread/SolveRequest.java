package mythread;
import serverandclient.BaseServer;
import utils.DataPackage;
import java.io.*;
import java.net.Socket;

public class SolveRequest extends Thread{
    private final Socket clt_sock;
    //private final BaseServer;

    public SolveRequest(Socket clt_sock) {
        this.clt_sock = clt_sock;
        //this.baseServer = baseServer;
    }


    public void run(){
        try {
            //设置输入输出流
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.clt_sock.getOutputStream()),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(this.clt_sock.getInputStream()));

            //读取客户端发来的报文
            String requestData = br.readLine();
            System.out.println("从客户端接受的数据:"+ requestData);

            //对得到的报文进行解析，得到里面包含的域名
            String mess = DataPackage.analysisReport(requestData);
            System.out.println("解析客户端发来的数据得到:" + mess);

            //从高速缓冲cache中查询是否存在
            String result = BaseServer.cache.getIP(mess);
            if(result == null){
                result = searchResult(mess);
                if(result.charAt(result.length() - 1) == '*') {
                    int pos = result.indexOf(':');
                    String ip = result.substring(pos + 1, result.length() - 1);
                    BaseServer.cache.addIP(mess, ip);
                    result = DataPackage.requestReport(result);
                    pw.println(result);
                }
                else
                    System.out.println("domain name is illegality!");
            }else{
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String searchResult(String mess){
        return mess + ":224.4.1.101*";
    }
}
