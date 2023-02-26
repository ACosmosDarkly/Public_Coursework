import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ObjectInputStream arrInput;
    private ServerSocket server;
    private Socket connection;
    private int[][] matrix1, matrix2, finalMatrix; // finalMatrix to be used in next part
    boolean killProg = false; // For terminating loop if kill signal is received
    

    private final int portNumber = 60000;

    public void runServer() throws IOException {

		System.out.println("Starting server...");
		server = new ServerSocket(portNumber);
		System.out.println("Server started...");

        while(killProg == false) {
            try {
                waitForConnection();
                getStreams(); // get io streams
                processConnection();
            }
            catch (ClassNotFoundException | InterruptedException e) {
                System.out.println("\nServer terminated connection");
                e.printStackTrace();
            }
            finally {
                closeConnection(); //  close connection
            }
        }
    }

    // wait for connection and display connection info (method modified from 020_client_server_no_GUI)
    private void waitForConnection() throws IOException {
        System.out.println("Waiting for connection...\n");
		
		connection = server.accept();
		
		System.out.println("Now connected with: " + connection.getInetAddress().getHostName());
    }

    // get streams to send and receive data (method modified from 020_client_server_no_GUI)
    private void getStreams() throws IOException, InterruptedException {
        arrInput = new ObjectInputStream(connection.getInputStream());
    }

    private void processConnection() throws IOException, ClassNotFoundException, InterruptedException {
        Object matrix = null;
        int matrixCount = 0, fileRows = 0, fileColumns = 0;

        while(matrixCount < 2) {
            try {
                matrix = arrInput.readObject();
                Thread.sleep(100); // Thread sleep to tell the thread to slow down.
                if(matrix instanceof int[][]) {
                    if(matrixCount < 1) {
                        matrix1 = (int[][]) matrix;
                        fileRows = matrix1.length;
                        fileColumns = matrix1[0].length;
                        finalMatrix = new int[fileRows][fileColumns];
                        matrixCount++;
                    }
                    else {
                        matrix2 = (int[][]) matrix;
                        matrixCount++;
                        //System.out.println("Matrix 1: ");
                        //print2dArray(matrix1);
                        //System.out.println("Matrix 2: ");
                        //print2dArray(matrix2);
                    }
                }
                else if(matrix instanceof String) {
                    if(matrix.equals("TERMINATE")) { // If the kill signal is send by the client, terminate the session.
                        System.out.println("Terminate signal received from client.");
                        killProg = true; // Kills process loop if kill signal received
                        break;
                    }
                }
            }
			catch (IOException e)
			{
				System.out.println("IOException occurred in Server.");
				this.arrInput = null;
			}
			catch (NullPointerException e)
			{
				System.out.println("Client exited.");
                e.printStackTrace();
				break;
			}
        }
        
        if(!matrix.equals("TERMINATE")) {
            ThreadOperation thread1 = new ThreadOperation(matrix1, matrix2, finalMatrix, getQuadrantIndexes(fileRows, fileColumns, 1));
            ThreadOperation thread2 = new ThreadOperation(matrix1, matrix2, finalMatrix, getQuadrantIndexes(fileRows, fileColumns, 2));
            ThreadOperation thread3 = new ThreadOperation(matrix1, matrix2, finalMatrix, getQuadrantIndexes(fileRows, fileColumns, 3));
            ThreadOperation thread4 = new ThreadOperation(matrix1, matrix2, finalMatrix, getQuadrantIndexes(fileRows, fileColumns, 4));

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            // Close threads
            try {
                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
            }
            catch (Exception e) {
                System.out.printf("Thread Interrupted.");
                e.printStackTrace();
            }

            System.out.println("\nFinal summed matrix:");;
            print2dArray(finalMatrix);
        }
    }

    // Close array stream and socket
    private void closeConnection () {
        System.out.println("\nTerminating connection\n");
        try{
            arrInput.close(); // close array input
			connection.close(); // close socket
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getQuadrantIndexes lowest index is 0, so row and column input is subtracted by one since array.length() counts from 1. 
    // To account for uneven division the input is also turned into a double and then typeset back to int at the end
    public static int[] getQuadrantIndexes(int rows, int columns, int targetQuad) {

        double topRow = 0, bottomRow = 0, leftCol = 0, rightCol = 0;
        double doubleRows = rows - 1, doubleCols = columns - 1;

        if(targetQuad == 1) {
            bottomRow = doubleRows / 2;
            rightCol = doubleCols / 2;
            // topRow and leftCol excluded because both equal 0 (default)
        }
        else if(targetQuad == 2) {
            bottomRow = doubleRows / 2;
            leftCol = doubleCols / 2 + 1; // One is added here (and below) to get the middle index plus one to avoid overlap
            rightCol = doubleCols;
            // topRow excluded because it equals 0 (default)
        }
        else if(targetQuad == 3) {
            topRow = doubleRows / 2 + 1; // +1 to avoid overlap
            bottomRow = doubleRows;
            rightCol = doubleCols / 2;
            // leftCol excluded because it equals 0 (default)
        }
        else {
            topRow = doubleRows / 2 + 1; // +1 to avoid overlap
            bottomRow = doubleRows;
            leftCol = doubleCols / 2 + 1; // +1 to avoid overlap
            rightCol = doubleCols;
        }
        int[] posArray = {(int)topRow,(int)bottomRow,(int)leftCol,(int)rightCol}; // Typesetting all variables to int for int array
        return posArray;
    }

    // print 2d array for testing
    public static void print2dArray(int[][] dimensionalArray) {
        for(int i = 0; i < dimensionalArray.length; i++) { 
            for(int j = 0; j < dimensionalArray[i].length; j++) { // Adjusted to remove additional print statement
                System.out.printf("%3s", dimensionalArray[i][j]);
            }
            System.out.println();
        }
    }
}
