import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        Scanner reader = new Scanner(System.in);

        while(true) {
            System.out.println("CLIENT MESSAGE:");
            String message = reader.nextLine();
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,address,1111);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(receivePacket);
            String date = new String(receiveData,0,receiveData.length);

            System.out.println("THE SERVER SAYS: "+ date.trim());
            receiveData = new byte[1024];
        }
    }
}
