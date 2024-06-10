// File Name GreetingClient.java

import java.io.*;
import java.net.*;

public class GreetingClient {
    public static void main(String[] args) {

        // Aqui nos coletamos o server name e a porta que o cliente vai se conectar.
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            // Aqui nos criamos um socket para o cliente se conectar ao servidor.
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            // nesta etapa, o cliente se conectou ao servidor e agora pode enviar e receber dados, podendo fazer transações de dados.
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            // Aqui ele envia uma mensagem para o servidor.
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);


            // E aqui encerra a conexão com o servidor.
            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}