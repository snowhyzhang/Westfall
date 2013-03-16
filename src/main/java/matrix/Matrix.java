package matrix;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 13-3-14
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
public class Matrix {
    private double[][] matrix;
    private int maxRow = -1;
    private int maxColumn = -1;

    public Matrix(int maxRow, int maxColumn){
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.matrix = new double[maxRow][maxColumn];
    }

    public Matrix(double[][] matrix){
        setMatrix(matrix);
    }

    public Matrix(double[] diag){
        buildDiagonal(diag);
    }

    public Matrix(int size){
        buildUnitMatrix(size);
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.maxRow = matrix.length;
        this.maxColumn = matrix[0].length;
        this.matrix = matrix;
    }

    public void setMatrixValue(int row, int column, double value){
        if ((row >=0 && row < maxRow) && (column >=0 && column < maxColumn)){
            matrix[row][column] = value;
        } else {
            System.out.println("Invalid ROW or COLUMN!");
        }
    }

    public void buildUnitMatrix(int size){
        this.maxRow = size;
        this.maxColumn = size;
        this.matrix = new double[size][size];
        for (int i = 0; i < size; ++i){
            this.matrix[i][i] = 1;
        }
    }

    public void buildDiagonal(double[] diag){
        this.maxRow = diag.length;
        this.maxColumn = diag.length;
        this.matrix = new double[this.maxRow][this.maxColumn];
        for (int i = 0; i < diag.length; ++i){
            this.matrix[i][i] = diag[i];
        }
    }

    public void printMatrix(){
        if (matrix == null){
            return;
        }
        for (int i = 0; i < maxRow; ++i){
            for (int j = 0; j < maxColumn; ++j){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public double getMatrixValue(int row, int column) {
        if ((row >=0 && row < maxRow) && (column >=0 && column < maxColumn)){
            return matrix[row][column];
        } else {
            System.out.println("Invalid ROW or COLUMN!");
            return 0;
        }
    }

    public boolean isSquareMatrix(){
        return this.maxRow == this.maxColumn;
    }
}
