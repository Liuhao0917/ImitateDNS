package utils;
import java.nio.charset.StandardCharsets;
public class DataPackage {

    //解析请求报文
    public static String analysisReport(String mess) {
        byte[] result = mess.getBytes(StandardCharsets.UTF_8);
        int len = result.length;
        /*for(int count = 0; count < len; count++){
            System.out.print(result[count]+ " ");
        }
        System.out.println(" ");*/
        short value;
        value = (short) ((result[len - 4] & 0xFF)
                | ((result[len - 3] & 0xFF)<<8));
        //System.out.println(value + " " + (short)result[len - 2] + " " + (short)result[len - 1]);
        return mess.substring(0, value);
    }


    //设置报文
    public static String requestReport(String str) {
        short len = (short) str.length();
        byte[] byteLen = new byte[4];
        byteLen[3] = 0;
        byteLen[2] = 0;
        byteLen[1] = (byte)((len >> 8) & 0xFF);
        byteLen[0] = (byte)(len & 0xFF);
        //System.out.println(byteLen[0] + " " + byteLen[1] + " " + byteLen[2] + " " + byteLen[3] + " ");
        byte[] result = new byte[4+len];

        /*for(int count = 0; count < len; count++){
            System.out.print(str.charAt(count)+ " ");
        }
        System.out.println(" ");*/

        System.arraycopy(str.getBytes(), 0, result, 0, len);
        System.arraycopy(byteLen, 0, result, len, 4);


        /*for(int count = 0; count < len + 4; count++){
            System.out.print(result[count]+ " ");
        }
        System.out.println(" ");

        short value;
        value = (short) ((result[len] & 0xFF)
                | ((result[len+1] & 0xFF)<<8));
        System.out.println(value + " " + (int)result[len + 2] + " " + (int)result[len + 1]);*/


        return new String(result, StandardCharsets.UTF_8);
    }


    //判断是否查到
    public static String responseReport(String mess) {
        String data = analysisReport(mess);

        if(data.charAt(data.length() - 1) == '@'){
            return "";
        }else{
            return data;
        }
    }
}
