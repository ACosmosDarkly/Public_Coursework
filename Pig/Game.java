public interface Game {
    abstract void play() throws InterruptedException;
    abstract int takeTurn() throws InterruptedException;
    abstract boolean isGameOver();
}