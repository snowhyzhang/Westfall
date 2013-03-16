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

        System.out.println("************");
        MatrixOperation.getMatrixPower(matrix, 3).printMatrix();
        System.out.println("************");

        System.out.println("************");
        MatrixOperation.getTransposeMatrix(matrix).printMatrix();
        System.out.println("************");
        double[][] arr1 = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        Matrix matrix1 = new Matrix(arr1);
        matrix1.printMatrix();
        System.out.println("************");
        MatrixOperation.getTransposeMatrix(matrix1).printMatrix();
        System.out.println("************");

        System.out.println("************");
        MatrixOperation.getCofactorMatrix(matrix, 1, 1).printMatrix();
        System.out.println("************");

        double[][] arr2 = new double[][]{{1, 0, 0}, {1, 1, -1}, {-2, 0, 3}};
        Matrix matrix2 = new Matrix(arr2);
        System.out.println("************");
        System.out.println("Determinant: " + MatrixOperation.getDeterminant(matrix2));
        System.out.println("************");
        System.out.println("************");
        MatrixOperation.getAdjointMatrix(matrix2).printMatrix();
        System.out.println("************");
        System.out.println("************");
        MatrixOperation.getInverseMatrix(matrix2).printMatrix();
        System.out.println("************");

    }

}
