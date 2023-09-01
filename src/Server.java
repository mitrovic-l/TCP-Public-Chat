import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            //Ceka da se konektuje neki klijent..
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Novi korisnik se konektovao na chat...");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException error){
            closeServerSocket();
        }
    }

    //Zatvara server ako dodje do neke greske..
    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException error){
            error.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2022);
        System.out.println("Pokrenut SERVER na socket-u 2022");
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
