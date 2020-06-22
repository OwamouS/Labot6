package ServSoft;

import Answers.Reply;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Objects;

public class Receiver{
    private static DatagramChannel datagramChannel;
    private static SocketAddress client;
    private boolean exit = false;
    private static SocketAddress serv;
    private static ByteBuffer recievBuffer = ByteBuffer.allocate(1024);


    public static byte[] getReply() {
        System.out.println("Recieving...");

        ByteBuffer buf = ByteBuffer.allocate(1024); //buffer for coming bytes
        byte[] clear = new byte[1024]; //std buffer for "everything OK" reply
        byte[] bad = new byte[1024]; //std buffer for "something went wrong" reply
        clear[0] = 111; // Ok signal
        bad[0] = 22; // Error signal

        try {

            byte[] result = new byte[0];
            while (true) {
                SocketAddress remoteAddr = ServerController.getChannel().receive(buf);
                if (Arrays.equals(buf.array(), new byte[1024])) {
                    break;
                }

                if (PacketFunctions.checkHash(buf.array())) {
                    ServerController.getChannel().send(ByteBuffer.wrap(clear),remoteAddr);
                    result = PacketFunctions.merge(result,Arrays.copyOfRange(buf.array(),0,1012));
                    System.out.println("yes");
                }
                else {
                    ServerController.getChannel().send(ByteBuffer.wrap(bad),remoteAddr);
                    System.out.println("no");
                }
                buf.clear();
            }
            return result;
        } catch(IOException e){
            System.out.println("Error lel");
        }
        return null;
    }
}
