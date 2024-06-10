// Vamos destrinchar o codigo para entender cada parte dele
// Primeiramente, importamos as bibliotecas necessarias, O Java.io e o Java.net. O primeiro serve para manipulação de entradas e saidas de dados, e o segundo para manipulação de redes (*net*work).

import java.io.*;
import java.net.*;

// Porque extendendo a classe Thread? Porque queremos que o servidor seja executado em uma thread separada, para que o programa principal possa continuar executando outras tarefas enquanto o servidor está esperando por conexões. É um tipo de programação concorrente.

// Tem Programação assincrona em Java? Sim, mas não é tão simples quanto em outras linguagens. A programação assincrona em Java é feita através de Threads, que são basicamente processos que rodam em paralelo com o programa principal.

public class GreetingServer extends Thread {
    private ServerSocket serverSocket;

    // Primeiro, nos vemos que ele lança uma um erro de IO, pois pode existir um erro ao tentar criar o socket do servidor. Se acontecer, ele vai printar o erro na tela.
    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        // Este método é chamado quando a thread é iniciada. Ele cria um loop infinito que espera por conexões de clientes. Quando um cliente se conecta, ele cria um novo socket para lidar com a conexão e cria um DataInputStream e um DataOutputStream para ler e escrever dados para o cliente. Conversando com o tópico anterior, o DataInputStream e o DataOutputStream são usados para ler e escrever dados primitivos e coletar a situação de um objeto.
        while(true) {
            try {

                //Aqui vemos a questão do método accept() que é chamado no socket do servidor. Este método bloqueia a execução do programa até que um cliente se conecte ao servidor. Quando um cliente se conecta, ele retorna um novo socket que é usado para se comunicar com o cliente.
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                //Aqui vemos que ele printa na tela que um cliente se conectou ao servidor.
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                //Aqui vemos que ele printa na tela o que o cliente enviou.
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");

                //E por fim, ele fecha a conexão com o cliente.
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Aqui tem nada demais, apenas pega a porta que o servidor vai rodar e inicia o servidor.
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new GreetingServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}