package simplechat;
import java.io.*;
import java.net.*;
public class ChatServer {
    public static void main(String[] agrs){
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true){
                Socket client = server.accept();
                new ClientHandling(client).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
