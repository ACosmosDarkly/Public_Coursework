import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Extra imports for improved interface readability
import java.awt.Font;
import java.awt.Color;


public class PigGUI extends JFrame {
    // text areas
    protected JTextArea playerOnePoints;
    protected JTextArea playerTwoPoints;
	protected JTextArea currentPlay;
    // labels
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel currentPlayLabel;
	// buttons
	private JButton rollButton;
	private JButton holdButton;
	private JButton quitButton;
    // variables
    public String buttonSelection;

    public PigGUI() {

		super("The Pig dice game");

		setLayout(null);

        playerOnePoints = new JTextArea();
        playerOnePoints.setFont(new Font("Serif", Font.PLAIN, 32));
        playerOnePoints.setBounds(100,80,300,400);
        playerOnePoints.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        add(playerOnePoints);

        playerTwoPoints = new JTextArea();
        playerTwoPoints.setFont(new Font("Serif", Font.PLAIN, 32));
        playerTwoPoints.setBounds(450,80,300,400);
        playerTwoPoints.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        add(playerTwoPoints);

		currentPlay = new JTextArea();
        currentPlay.setFont(new Font("Serif", Font.PLAIN, 32));
		currentPlay.setBounds(100,580,650,50);
        currentPlay.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        currentPlay.setBackground(Color.LIGHT_GRAY);
		add(currentPlay);

        playerOneLabel = new JLabel("Player One");
        playerOneLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        playerOneLabel.setBounds(100,20,200,50);
        add(playerOneLabel);

        playerTwoLabel = new JLabel("Player Two");
        playerTwoLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        playerTwoLabel.setBounds(450,20,200,50);
        add(playerTwoLabel);

        currentPlayLabel = new JLabel("Current Play");
        currentPlayLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        currentPlayLabel.setBounds(100,520,200,50);
        add(currentPlayLabel);

        rollButton = new JButton("roll");
		rollButton.setBounds(100, 700, 100,30);
		add(rollButton);

        holdButton = new JButton("hold");
		holdButton.setBounds(240, 700, 100,30);
		add(holdButton);

        quitButton = new JButton("quit");
		quitButton.setBounds(650, 700, 100,30);
		add(quitButton);

		// ButtonHandler objects for handling button presses
		ButtonHandler handler = new ButtonHandler();
		rollButton.addActionListener(handler);
		holdButton.addActionListener(handler);
		quitButton.addActionListener(handler);

        setSize(860, 820); // set size of window
		setVisible(true);  // show window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    // Called from the Pig class when the game is over
    public void quitGUI() {
        super.setVisible(false);
        super.dispose();
    }

    // For button event handling
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			buttonSelection = event.getActionCommand();
		}
	}
}
