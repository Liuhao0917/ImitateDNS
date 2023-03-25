package serverandclient;
import cache.Cache;
import utils.DataPackage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private final static String ip = "127.0.0.1";                //本地域名服务器的ip地址
    private final static int port = 49999;                       //本地域名服务器所在端口号
    public final static Cache cache = new Cache(7);     //高速缓存


    public static void main(String[] args) throws IOException, NullPointerException {
        while(true){
            System.out.println();
            System.out.println("Please input domain name:");
            BufferedReader out = new BufferedReader(new InputStreamReader(System.in));
            String report = out.readLine();
            String result = cache.getIP(report);
            if(result == null){
                result = transmitAndReceive(report);
                if(result.charAt(result.length() - 1) == '*') {
                    int pos = result.indexOf(':');
                    String ip = result.substring(pos + 1, result.length() - 1);
                    cache.addIP(report, ip);
                    System.out.println(report + " 对应的ip地址为:" + ip);
                }
                else
                    System.out.println("domain name is illegality!");
            }else{
                System.out.println(report + " 对应的ip地址为:" + result);
            }
        }

    }

    public static String transmitAndReceive(String report) throws IOException{
        try{
            Socket clt_sock = new Socket(ip, port);    //执行完毕就建立了连接
            System.out.println("connect success");
            //构建IO
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(clt_sock.getOutputStream()),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clt_sock.getInputStream()));
            // BufferedReader out = new BufferedReader(new InputStreamReader(System.in));


            //向服务器端发送一条消息
            // String report = out.readLine();
            report = DataPackage.requestReport(report);
            pw.println(report);
            //pw.flush();


            /*byte[] result = str.getBytes(StandardCharsets.UTF_8);
            int len = result.length;
            for(int count = 0; count < len; count++){
                System.out.print(result[count]+ " ");
            }
            System.out.println(" ");
            short value;
            value = (short) ((result[len - 4] & 0xFF)
                    | ((result[len - 3] & 0xFF)<<8));
            System.out.println(value + " " + (short)result[len - 2] + " " + (short)result[len - 1]);*/


            //读取服务器返回的消息
            String mess = br.readLine();
            System.out.println("服务器返回的应答消息为:" + mess);
            String response = DataPackage.responseReport(mess);
            System.out.println("解析后的应答消息为:" + response);

            return response;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

}
