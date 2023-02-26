import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
//Extra imports for improved interface readability (reused from SuggestionGUI)
import java.awt.Font;
import java.awt.Color;

public class Client extends JFrame {
    
    // Network specific variables
    private ObjectOutputStream arrOut;
	private Socket client;
    private final String host = "127.0.0.1";
    private final int portNumber = 60000;
    private String killSignal = " ";

    // GUI specific variables
    private JTextField userInput;
    private JButton readButton;
    private String fileName = " ";
    int[][] matrix1, matrix2, finalMatrix;

    public Client() {

        super("Client-Server MultiThreaded Processor");

		setLayout(null);
		
		userInput = new JTextField("matrix1.txt");
		userInput.setFont(new Font("Serif", Font.PLAIN, 28));
		userInput.setBackground(Color.LIGHT_GRAY);
		userInput.setForeground(Color.BLACK);
		userInput.setBounds(10,10,500,60);
		add(userInput);

        readButton = new JButton("Read Text"); // button with text
		readButton.setBounds(100, 100, 100,30);
		add(readButton); // add plainJButton to JFrame

        // ButtonHandler objects for handling button presses
		ButtonHandler handler = new ButtonHandler();
		readButton.addActionListener(handler);

		setSize(600, 300); // set size of window
		setVisible(true);  // show window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // inner class for button event handling
	private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String buttonInput = userInput.getText();
            if(buttonInput.equals("TERMINATE")) {
                // If "TERMINATE" is entered into the field without sending a matrix then terminate
                // the session on both ends. ProcessConnection uses killSignal in it's loop.
                killSignal = "TERMINATE"; 
                sendTerminate(killSignal); // Send signal to server
            }
            else {
                fileName = buttonInput;
                File fileData = new File("./"+fileName); // !!!!!!!!!!!!!!!!!! YOU MAY NEED TO REPLACE THE "./" WITH AN ABSOLUTE PATH !!!!!!!!!!!!!!!!!!!!!!
                try {
                    // Try to scan data from the file and send two matrices to the server.
                    Scanner scan = new Scanner(fileData);
                    int fileRows = scan.nextInt();
                    int fileColumns = scan.nextInt();                    
                    matrix1 = matrixFromFile(fileRows, fileColumns, scan);
                    matrix2 = matrixFromFile(fileRows, fileColumns, scan);
                    sendMatrix(matrix1);
                    sendMatrix(matrix2);
                    killSignal = "TERMINATE"; // Terminate the session after sending data. Server closes on it's end after processing.
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Connect to server and process messages from server (method modified from 020_client_server_no_GUI)
    // this method runs it's task once then breaks out of the loop and closes the session and GUI.
	public void runClient() {
        try {
            connectToServer();
            getStreams();
            processConnection();
        }
        catch (EOFException e)  {
            System.out.println("\nClient terminated connection");
        } 
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        closeConnection(); // close client-server session
        closeGUI(); // close GUI.
	}

    // connect to server (method modified from 020_client_server_no_GUI)
	private void connectToServer() throws IOException {
		System.out.println("Attempting to connect...\n");
		client = new Socket(InetAddress.getByName(host), portNumber);
		System.out.println("Connected to server.");
	}

    private void getStreams() throws IOException {
        arrOut = new ObjectOutputStream(client.getOutputStream());
        arrOut.flush();
    }

    // Processes the input from the GUI once then exits. Sends the GUI text information
    // to the service which then further processes the data.
    private void processConnection() throws InterruptedException {
        do {
            Thread.sleep(100);
        } while(killSignal != "TERMINATE"); // kill signal check
    }
    
    // Close array stream and socket
	private void closeConnection() {
		System.out.println("\nClosing connection");
		try
		{
            arrOut.close();
			client.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    // Sends the matrix object to the server for processing.
    private void sendMatrix(int[][] matrix) {
        try {
            arrOut.writeObject(matrix);
        } 
        catch (IOException e) {
            System.out.println("\nError sending matrix");
            e.printStackTrace();
        }
    }

    // Sends the kill signal to the server if session is terminated.
    private void sendTerminate(String signal) {
        try {
            arrOut.writeObject(signal);
        } 
        catch (IOException e) {
            System.out.println("\nError sending connection terminate signal");
            e.printStackTrace();
        }
    }

    // If the word "TERMINATE" is entered in the text box, or when the session finishes, this will close the GUI.
    private int closeGUI() {
        super.setVisible(false);
		super.dispose();
        return 0;
    }

    // matrixFromFile takes in the number of rows and columns for a text file matrix, reads it in using a scanner,
    // and inserts the numbers into a 2d array which is then returned
    public static int[][] matrixFromFile(int rows, int columns, Scanner fileReader) {

        int[][] matrixArray = new int[rows][columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrixArray[i][j] = fileReader.nextInt();
            }
            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }
        }
        return matrixArray;
    }
}
