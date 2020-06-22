package ServSoft;

import Answers.Reply;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Sender{
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private static SocketAddress client;

    static {
        try {
            client = ServerController.getChannel().receive(sendBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sender() throws IOException {
    }

    public static void send (byte[] data){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer handle = ByteBuffer.allocate(1024);
            byte[] approver;

            while(true) {
                buffer.clear();
                if (data.length > 1012) {
                    buffer.put(PacketFunctions.formatData(Arrays.copyOfRange(data, 0, 1012)));
                }
                else {
                    buffer.put(PacketFunctions.formatData(Arrays.copyOf(data,1012)));
                }

                buffer.flip();
                ServerController.getChannel().send(buffer,client);

                approver = new byte[1024];
                handle.put(approver);
                ServerController.getChannel().receive(handle);

                if ( handle.array()[0] == 111 ){
                    if ( data.length > 1012 ) {
                        data = Arrays.copyOfRange(data, 1012, data.length);
                    }
                    else break;
                }
            }

            buffer.put(new byte[1024]);
            ServerController.getChannel().send(buffer,client);

        }
        catch (IOException e){
            System.out.println("Error");
        }
    }
}