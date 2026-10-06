package matrix;

import java.util.Random;
import java.util.Scanner;

public class MatrixMultiplier {

    public static double[][] multiply(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        int p = b[0].length;

        if (n != b.length) {
            throw new IllegalArgumentException("Columns of A must equal rows of B");
        }

        double[][] c = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rows of A: ");
        int m = scanner.nextInt();
        System.out.print("Columns of A (= rows of B): ");
        int n = scanner.nextInt();
        System.out.print("Columns of B: ");
        int p = scanner.nextInt();

        double[][] a = randomMatrix(m, n);
        double[][] b = randomMatrix(n, p);

        long start = System.nanoTime();
        multiply(a, b);
        long end = System.nanoTime();

        System.out.printf("Time: %.3f ms%n", (end - start) / 1e6);
    }

    private static double[][] randomMatrix(int rows, int cols) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }
}
