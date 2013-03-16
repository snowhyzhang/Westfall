package matrix;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 13-3-14
 * Time: 下午11:38
 * To change this template use File | Settings | File Templates.
 */
public class MatrixOperation {

    public static Matrix matrixAddition(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null || !checkEqualRowColumn(matrix1, matrix2)){
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

    public static Matrix matrixSubtraction(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null || !checkEqualRowColumn(matrix1, matrix2)){
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

    public static Matrix matrixMultiple(Matrix matrix, double x){
        if (matrix == null){
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

    public static Matrix matrixProduct(Matrix matrix1, Matrix matrix2){
        if (matrix1 == null || matrix2 == null || !checkProductRowColumn(matrix1, matrix2)){
            return null;
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

    public static Matrix matrixPower(Matrix matrix, int power){
        if (matrix == null || power < 0 || !matrix.isSquareMatrix()){
            return null;
        }

        int size = matrix.getMaxRow();
        if (power == 0){
            return new Matrix(size);
        }

        Matrix matrixResult = new Matrix(matrix.getMatrix().clone());
        for (int  i = 1; i < power; ++i){
            matrixResult.setMatrix(matrixProduct(matrixResult, matrix).getMatrix());
        }
        return matrixResult;
    }

    public static Matrix matrixTranspose(Matrix matrix){
        if (matrix == null){
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

    private static boolean checkProductRowColumn(Matrix matrix1, Matrix matrix2) {
        return matrix1.getMaxColumn() == matrix2.getMaxRow();
    }

    private static boolean checkEqualRowColumn(Matrix matrix1, Matrix matrix2) {
        return matrix1.getMaxRow() == matrix2.getMaxRow() && matrix1.getMaxColumn() == matrix2.getMaxColumn();
    }

}
