import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixFileWriter {
    public static void writeMatrixTofile(String filePath, int[][] matrix) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int[] row : matrix) {
                for (int element : row) {
                    writer.write(element + " ");
                }
                writer.newLine();
            }
        }
    }

    public static int[][] readMatrixFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        int[][] matrix = new int[10][10];
        int row = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            for (int col = 0; col < elements.length; col++) {
                matrix[row][col] = Integer.parseInt(elements[col]);
            }
            
        }
        reader.close();

        // Only returning rows that were filled
        int[][] result = new int[row][matrix[0].length];
        for (int i = 0; i < row; i++) {
            result[i] = matrix[i];
        }
        return result;
    }

    // Multiply two matrices
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            // Creating a sample matrix (without dimensions)
            int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };

            // Writing matrix to a file
            writeMatrixTofile("matrix1.txt", matrix1);
            System.out.println("Matrix1 has been written to matrix1.txt");

            // Reading the matrix back from the file
            int[][] matrixRead = readMatrixFromFile("matrix1.txt");
            System.out.println("Matrix1 has been read from file.");

            // Multiplication example with another matrix
            int[][] matrix2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
            };
            writeMatrixTofile("matrix2.txt", matrix2);
            System.out.println("Matrix2 has been written to matrix2.txt");

            // Multiply the matrices
            int[][] resultMatrix = multiplyMatrices(matrixRead, matrix2);
            writeMatrixTofile("matrix3.txt", resultMatrix);
            System.out.println("Result of multiplication written to matrix3.txt");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

