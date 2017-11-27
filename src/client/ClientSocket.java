package client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8878);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream("e:\\1.jpg");
        byte[] buf = new byte[1024];
        int len;
        while ((len=fis.read(buf))!=-1){
            os.write(buf,0,len);
        }
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        byte[] bufMsg = new byte[1024];
        int num = is.read(bufMsg);
        String Msg = new String(bufMsg,0,num);
        System.out.println(Msg);
        fis.close();
        socket.close();
    }
}
