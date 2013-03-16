package matrix;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 13-3-15
 * Time: 上午12:22
 * To change this template use File | Settings | File Templates.
 */
public class MatrixProcessor {

    public static void main(String args[]){
        double[] arr = new double[]{1, 2, 3, 4, 5};
        Matrix matrix = new Matrix(arr);

        MatrixOperation.matrixPower(matrix, 3).printMatrix();

        MatrixOperation.matrixTranspose(matrix).printMatrix();
        double[][] arr2 = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        Matrix matrix1 = new Matrix(arr2);
        matrix1.printMatrix();
        MatrixOperation.matrixTranspose(matrix1).printMatrix();
    }

}
