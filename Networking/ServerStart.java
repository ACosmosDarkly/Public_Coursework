import java.io.IOException;

public class ServerStart {
    public static void main(String[] args) {
        Server newServer = new Server();
        try {
            newServer.runServer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
