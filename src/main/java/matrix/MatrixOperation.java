package matrix;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 13-3-14
 * Time: 下午11:38
 * To change this template use File | Settings | File Templates.
 */
public class MatrixOperation {

    public static Matrix getMatrixAddition(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (!checkEqualRowColumn(matrix1, matrix2)){
            System.out.println("Row doesn't equals to column!");
            return null;
        }

        int row = matrix1.getMaxRow();
        int column = matrix1.getMaxColumn();
        Matrix matrixResult = new Matrix(row, column);

        for (int i = 0; i < row; ++i){
            for (int j = 0; j < column; ++j){
                matrixResult.setMatrixValue(i, j, matrix1.getMatrixValue(i, j) + matrix2.getMatrixValue(i, j));
            }
        }

        return matrixResult;
    }

    public static Matrix getMatrixSubtraction(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (!checkEqualRowColumn(matrix1, matrix2)){
            System.out.println("Row doesn't equals to column!");
            return null;
        }

        int row = matrix1.getMaxRow();
        int column = matrix1.getMaxColumn();
        Matrix matrixResult = new Matrix(row, column);

        for (int i = 0; i < row; ++i){
            for (int j = 0; j < column; ++j){
                matrixResult.setMatrixValue(i, j, matrix1.getMatrixValue(i, j) - matrix2.getMatrixValue(i, j));
            }
        }

        return matrixResult;
    }

    public static Matrix getMatrixMultiple(Matrix matrix, double x){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return null;
        }

        int row = matrix.getMaxRow();
        int column = matrix.getMaxColumn();
        Matrix matrixResult = new Matrix(row, column);

        for (int i = 0; i < row; ++i){
            for (int j = 0; j < column; ++j){
                matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(i, j) * x);
            }
        }

        return matrixResult;
    }

    public static Matrix getMatrixProduct(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (!checkProductRowColumn(matrix1, matrix2)){
            System.out.println("Matrix1's column doesn't equal to Matrix2's row!");
        }

        int row = matrix1.getMaxRow();
        int column = matrix2.getMaxColumn();
        int product = matrix1.getMaxColumn();

        Matrix matrixResult = new Matrix(row, column);

        for (int i = 0; i < row; ++i){
            for (int j = 0; j < column; ++j){
                double result = 0;
                for (int k = 0; k < product; ++k){
                    result = result + matrix1.getMatrixValue(i, k) * matrix2.getMatrixValue(k, j);
                }
                matrixResult.setMatrixValue(i, j, result);
            }
        }

        return matrixResult;
    }

    public static Matrix getMatrixPower(Matrix matrix, int power){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (power < 0 || !matrix.isSquareMatrix()){
            System.out.println("Power or Matrix is not Valid!");
        }

        int size = matrix.getMaxRow();
        if (power == 0){
            return new Matrix(size);
        }

        Matrix matrixResult = new Matrix(matrix.getMatrix().clone());
        for (int  i = 1; i < power; ++i){
            matrixResult.setMatrix(getMatrixProduct(matrixResult, matrix).getMatrix());
        }
        return matrixResult;
    }

    public static Matrix getTransposeMatrix(Matrix matrix){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return null;
        }

        int row = matrix.getMaxColumn();
        int column = matrix.getMaxRow();

        Matrix matrixResult = new Matrix(row, column);
        for (int i = 0; i < row; ++i){
            for (int j = 0; j < column; ++j){
                matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(j, i));
            }
        }

        return matrixResult;
    }

    public static Matrix getInverseMatrix(Matrix matrix){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (!matrix.isSquareMatrix()){
            System.out.println("Matrix is not a square matrix!");
            return null;
        }

        int size = matrix.getMaxRow();
        double determinant = getDeterminant(matrix);
        if (determinant == 0){
            System.out.println("Inverse matrix doesn't exist!");
            return null;
        }
        Matrix adjointMatrix = getAdjointMatrix(matrix);
        return getMatrixMultiple(adjointMatrix, (1.0 / determinant));
    }

    public static Matrix getCofactorMatrix(Matrix matrix, int row, int column){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return matrix;
        }

        if ((row < 0 || row >= matrix.getMaxRow()) || (column < 0 || column >= matrix.getMaxColumn())){
            System.out.println("Invalid row or column!");
            return null;
        }

        int maxRow = matrix.getMaxRow() - 1;
        int maxColumn = matrix.getMaxColumn() - 1;

        Matrix matrixResult = new Matrix(maxRow, maxColumn);

        for (int i = 0; i < maxRow; ++i){
            if (i < row){
                for (int j = 0; j < maxColumn; ++j){
                    if (j < column){
                        matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(i, j));
                    } else {
                        matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(i, j + 1));
                    }
                }
            } else {
                for (int j = 0; j < maxColumn; ++j) {
                    if (j < column){
                        matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(i + 1, j));
                    } else {
                        matrixResult.setMatrixValue(i, j, matrix.getMatrixValue(i + 1, j + 1));
                    }
                }
            }
        }

        return matrixResult;
    }

    public static double getDeterminant(Matrix matrix){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return 0;
        }
        if (!matrix.isSquareMatrix()){
            System.out.println("Matrix is not a square matrix!");
            return 0;
        }

        int maxRow = matrix.getMaxRow();
        double result = 0;
        double[] subDeterminant = new double[maxRow];
        if (matrix.getMaxRow() == 2){
            return getSecondOrderDeterminant(matrix);
        }
        for (int i = 0; i < maxRow; ++i){
            subDeterminant[i] = matrix.getMatrixValue(0, i) * getDeterminant(getCofactorMatrix(matrix, 0, i));
            if (i % 2 != 0){
                subDeterminant[i] = -1.0 * subDeterminant[i];
            }
        }
        for (double aSubDeterminant : subDeterminant) {
            result = result + aSubDeterminant;
        }

        return result;
    }

    public static Matrix getAdjointMatrix(Matrix matrix){
        if (matrix == null){
            System.out.println("Matrix is null!");
            return null;
        }
        if (!matrix.isSquareMatrix()){
            System.out.println("Matrix is not a square matrix!");
            return null;
        }

        int size = matrix.getMaxRow();
        Matrix matrixAdjoint = new Matrix(size, size);

        for (int i = 0; i < size; ++i){
            for (int j = 0; j < size; ++j){
                if ((i + j) % 2 == 0){
                    matrixAdjoint.setMatrixValue(i, j, getDeterminant(getCofactorMatrix(matrix, i, j)));
                } else {
                    matrixAdjoint.setMatrixValue(i, j, -1 * getDeterminant(getCofactorMatrix(matrix, i, j)));
                }
            }
        }

        return getTransposeMatrix(matrixAdjoint);
    }

    private static double getSecondOrderDeterminant(Matrix matrix){
        return (matrix.getMatrixValue(0, 0) * matrix.getMatrixValue(1, 1)
                - matrix.getMatrixValue(0, 1) * matrix.getMatrixValue(1, 0));
    }

    private static boolean checkProductRowColumn(Matrix matrix1, Matrix matrix2) {
        return matrix1.getMaxColumn() == matrix2.getMaxRow();
    }

    private static boolean checkEqualRowColumn(Matrix matrix1, Matrix matrix2) {
        return matrix1.getMaxRow() == matrix2.getMaxRow() && matrix1.getMaxColumn() == matrix2.getMaxColumn();
    }

}
