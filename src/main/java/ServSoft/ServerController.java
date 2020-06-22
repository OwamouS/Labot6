package ServSoft;

import Answers.Reply;
import Control.cmdLists.CommandList;
import Control.cmdLists.StdCommandList;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;

public class ServerController {

    private static DatagramChannel channel;
    private static SocketAddress serv;
    private static SocketAddress client;
    private static final int port = 1337;
    private static boolean running = false;
    private static CommandList cmdList =  new StdCommandList();
    private static InetAddress hostIP;
    private static Reply rep = null;

    public static Request handleReply(Reply reply){
        byte[] serializedRequest = Serializer.serialize(reply);
        assert serializedRequest != null;
        byte[] request = null;
        Sender.send(serializedRequest);
        request = Receiver.getReply();
        return Serializer.deserialize(request);
    }

    public static DatagramChannel getChannel() {
        return channel;
    }

    public static void setHostIP(InetAddress hostIP) {
        ServerController.hostIP = hostIP;
    }

    public static void setAdress(SocketAddress adress) {
        ServerController.serv = adress;
    }

    public static void setChannel(DatagramChannel channel) {
        ServerController.channel = channel;
    }

    public static void start(){
        running = true;
        try{
            hostIP = InetAddress.getLocalHost();
            serv = new InetSocketAddress(port);
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(serv);
            System.err.println("bound to " + serv);
            System.out.println("Server awaiting connections...");
            while (running){
                Request request = ServerController.handleReply(rep);
                cmdList.getCommands().get(request.command).execute(request.args);
                if(request.args.length == 0){
                    rep = new Reply(null,request.command.execute(request.args));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketAddress getRemoteAddress() throws IOException {
        return channel.getRemoteAddress();
    }

}
