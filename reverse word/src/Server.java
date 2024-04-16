import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        ServerSocket server = null;
        try {
            server = new ServerSocket(2300);
            System.out.println("THE SERVER HAS BEEN INITIATED.... WAITING FOR CLIENTS....");

            while (true) {
                Socket client = server.accept();
                System.out.println("client + " + client.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(client);
                new Thread(handler).start();


            }
        }catch (IOException i ){
            i.printStackTrace();
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
