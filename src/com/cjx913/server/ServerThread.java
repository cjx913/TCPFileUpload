package com.cjx913.server;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        String ip = socket.getInetAddress().getHostAddress();
        int count = 1;
        try {
            InputStream is = socket.getInputStream();
            File parentFile = new File("e:\\upload\\");
            if(!parentFile.exists()){
                parentFile.mkdir();
            }
            File file = new File(parentFile,ip+"_"+count+".jpg");
            while (file.exists()){
                file = new File(parentFile,ip+"_"+(count++)+".jpg");
            }

            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len=is.read(buf))!=-1){
                fos.write(buf,0,len);
            }
            OutputStream os = socket.getOutputStream();
            os.write("上传成功".getBytes());
            fos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
