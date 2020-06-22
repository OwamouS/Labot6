package ServSoft;

import Answers.Reply;

import java.io.*;
import java.util.Arrays;

public class Serializer {

    protected static <T> byte[] serialize(T obj){
        byte[] buff;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            buff = baos.toByteArray();
            return buff;
        }
        catch (IOException e){
            //make
            return null;
        }
    }

    public static Request deserialize(byte[] data){
        try{
            if (data != null) {
                ObjectInput ois = new ObjectInputStream(new ByteArrayInputStream(data));
                return (Request) ois.readObject();
            }
        }
        catch (IOException ioException) {
            System.out.println("Oh no, some IO exception occurs.");
        }
        catch (ClassNotFoundException classNotFoundException){
            System.out.println("An unknown format response was received from the server, " +
                    "please change the connection to the correct server.");
        }
        return null;
    }

}
