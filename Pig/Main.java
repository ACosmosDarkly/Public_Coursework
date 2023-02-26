public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		PigGUI applcation = new PigGUI();
		Pig game = new Pig(20, applcation);
		game.play();
	}
}
