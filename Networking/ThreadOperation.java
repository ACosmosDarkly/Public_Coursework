public class ThreadOperation extends Thread {
    
    private int[][] matrix1, matrix2, finalMatrix;
    private int[] quadrant;

    public ThreadOperation (int matrix1[][], int matrix2[][], int[][] finalMatrix, int[] quadrant) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.finalMatrix = finalMatrix;
        this.quadrant = quadrant;
    }

    public void run() {
        for(int i = this.quadrant[0]; i <= this.quadrant[1]; i++) {
            for(int j = this.quadrant[2]; j <= this.quadrant[3]; j++) {
                this.finalMatrix[i][j] = this.matrix1[i][j] + this.matrix2[i][j];
            }
        }
    }
}
