import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientcp {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost",1111);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader (new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream(),true);
        String clientInput ;
        while ((clientInput = stdIn.readLine()) != null){
            out.println(clientInput);
            String serverReply = in.readLine();
            if(serverReply != null) {
                System.out.println("the server returned an area of: " + serverReply);
            }else {
                break;
            }
        }
        out.close();
        in.close();
        stdIn.close();
        server.close();
    }
}
