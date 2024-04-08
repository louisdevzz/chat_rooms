package simplechat;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ClientHandling extends Thread{
    private Socket socket;
    private static HashMap<String,String> data = new HashMap<>();
    public ClientHandling(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
            out.println("Hello! This is the Java EchoSever. \nEnter BYE to exit.");
            out.flush();
            out.println("Enter name: ");
            out.flush();
            String names = in.readLine();
            while (true) {
                out.println("Enter message: ");
                out.flush();
                String str = in.readLine();
                data.put(names,str);
//                write response to client
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    out.println(entry.getKey() + ": " + entry.getValue());
                }
//                flush output stream
                out.flush();

                if (str.toUpperCase().trim().equals("BYE")) {
                    break;
                }
            }

            in.close();
            out.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
