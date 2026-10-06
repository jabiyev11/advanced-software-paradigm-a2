import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class MatrixSlicer {

    public static int[][] slice(int[][] matrix, int rowStart, int rowStop, int rowStep,
                                int colStart, int colStop, int colStep) {
        int rows = count(rowStart, Math.min(rowStop, matrix.length), rowStep);
        int cols = count(colStart, Math.min(colStop, matrix[0].length), colStep);

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[rowStart + i * rowStep][colStart + j * colStep];
            }
        }
        return result;
    }

    private static int count(int start, int stop, int step) {
        return start >= stop ? 0 : (stop - start + step - 1) / step;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Image path: ");
        int[][] image = toMatrix(ImageIO.read(new File(scanner.nextLine().trim())));
        System.out.printf("Image size: %d rows x %d columns%n", image.length, image[0].length);

        int rowStart = readInt(scanner, "Row start: ", 0);
        int rowStop = readInt(scanner, "Row stop: ", 0);
        int rowStep = readInt(scanner, "Row step: ", 1);
        int colStart = readInt(scanner, "Column start: ", 0);
        int colStop = readInt(scanner, "Column stop: ", 0);
        int colStep = readInt(scanner, "Column step: ", 1);

        int[][] sliced = slice(image, rowStart, rowStop, rowStep, colStart, colStop, colStep);

        if (sliced.length == 0 || sliced[0].length == 0) {
            System.out.println("The slice is empty.");
            return;
        }
        ImageIO.write(toImage(sliced), "png", new File("java_slice.png"));
        System.out.printf("Saved java_slice.png (%d rows x %d columns)%n", sliced.length, sliced[0].length);
    }

    private static int readInt(Scanner scanner, String prompt, int minimum) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(text);
                if (value >= minimum) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Problem arised");
            }
            System.out.println("Please enter a whole number >= " + minimum + ".");
        }
    }

    private static int[][] toMatrix(BufferedImage image) {
        int[][] matrix = new int[image.getHeight()][image.getWidth()];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = image.getRGB(col, row);
            }
        }
        return matrix;
    }

    private static BufferedImage toImage(int[][] matrix) {
        BufferedImage image = new BufferedImage(matrix[0].length, matrix.length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                image.setRGB(col, row, matrix[row][col]);
            }
        }
        return image;
    }
}
