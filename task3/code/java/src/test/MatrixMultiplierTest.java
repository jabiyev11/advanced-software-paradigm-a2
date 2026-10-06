package matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MatrixMultiplierTest {

    @Test
    void multipliesSquareMatrices() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}, {7, 8}};
        double[][] expected = {{19, 22}, {43, 50}};

        assertArrayEquals(expected, MatrixMultiplier.multiply(a, b));
    }

    @Test
    void multipliesNonSquareMatrices() {
        double[][] a = {{1, 2, 3}, {4, 5, 6}};
        double[][] b = {{7, 8}, {9, 10}, {11, 12}};
        double[][] expected = {{58, 64}, {139, 154}};

        assertArrayEquals(expected, MatrixMultiplier.multiply(a, b));
    }

    @Test
    void multiplyingByIdentityReturnsSameMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] identity = {{1, 0}, {0, 1}};

        assertArrayEquals(a, MatrixMultiplier.multiply(a, identity));
    }

    @Test
    void rowTimesColumnGivesSingleValue() {
        double[][] row = {{1, 2, 3}};
        double[][] column = {{4}, {5}, {6}};
        double[][] expected = {{32}};

        assertArrayEquals(expected, MatrixMultiplier.multiply(row, column));
    }

    @Test
    void throwsWhenDimensionsDoNotMatch() {
        double[][] a = {{1, 2, 3}};
        double[][] b = {{1, 2}};

        assertThrows(IllegalArgumentException.class, () -> MatrixMultiplier.multiply(a, b));
    }
}
