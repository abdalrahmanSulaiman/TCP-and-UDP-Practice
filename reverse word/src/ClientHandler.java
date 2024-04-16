import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    public ClientHandler(Socket sock){
        clientSocket = sock;
    }
    @Override
    public void run() {
        PrintWriter out;
        StringBuilder finalWord = new StringBuilder();
        BufferedReader in;
        try{
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage;
            while ((clientMessage = in.readLine()) != null){
                System.out.println("the client sent the following message: \n"+clientMessage);
                finalWord.append(clientMessage);
                finalWord.reverse();
                out.println(finalWord.toString());
                finalWord = new StringBuilder();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
