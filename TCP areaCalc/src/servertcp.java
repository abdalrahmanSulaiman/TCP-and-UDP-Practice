import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servertcp{
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1111);
        System.out.println("the server started.... waiting for client");
        Socket client = server.accept();
        System.out.println("the client has joined");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        String radius;
        while((radius = in.readLine()) != null){
            if (radius.equals("exit")){
                break;
            }else {
                System.out.println("the radius sent by the client is " + radius);
                float actRadius = Float.parseFloat(radius);
                float result = (float) (Math.PI * Math.pow(actRadius, 2));
                out.println(result);
            }


        }
        in.close();
        out.close();
        client.close();
        server.close();

    }
}