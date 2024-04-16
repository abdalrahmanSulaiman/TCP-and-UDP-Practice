import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class server{
    public static void main(String[] args) throws IOException {
    DatagramSocket serverSocket = new DatagramSocket(1111);
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];

        System.out.println("server started. waiting for client to join");

        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("CLIENT MESSAGE:");
            System.out.println(receivedMessage);
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String unrecognized = "this input is unrecognized";
            if (!receivedMessage.equals("date")) {
                sendData = unrecognized.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
                serverSocket.send(sendPacket);
            }else if(receivedMessage.equals("date")){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                sendData = dtf.format(now).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
                serverSocket.send(sendPacket);
            }
        }
    }

}