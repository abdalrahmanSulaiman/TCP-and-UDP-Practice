import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client2 {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost",2300);

        PrintWriter out = new PrintWriter(server.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            String response = in.readLine();
            System.out.println("THE SERVER REPLIED: \n"+response);
        }
        out.close();
        in.close();
        stdIn.close();
        server.close();
    }
}
