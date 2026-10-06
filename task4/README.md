## Task 4. 2D Matrix Slicing: NumPy vs Java

I implemented 2D matrix slicing in Python with NumPy and in Java. To show the results graphically,
I used an image as the matrix, because an image is a 2D matrix of pixels, and slicing the matrix crops the image.
For testing I used `sample.png`, a 400 × 600 image of numbered cells, so it is easy to see which part was cut.

### 4.1 Implementation

In both programs the user enters the image path and the slice parameters, 
and the result is saved as a PNG file.

In Python, the slicing itself is one line:
`image[row_start:row_stop:row_step, col_start:col_stop:col_step]`.
In Java, I first converted the image into an `int[][]` matrix of pixels, then wrote the slicing with two loops,
following the same rules as NumPy: the stop index is not included, a stop larger than the matrix is cut to its size,
and the step selects every k-th row or column. Finally, I wrote `compare.py`, which shows the original image
and both results side by side and checks whether they are identical pixel by pixel.

### 4.2 Results

**Example 1:** rows 100:300, columns 150:450 (a crop)

![Crop comparison](task4/code/results/comparison_crop.png)

**Example 2:** rows 0:400:2, columns 0:600:2 (every 2nd row and column)

![Step comparison](task4/code/results/comparison_step.png)

In both examples the NumPy and Java results have the same size and are identical pixel by pixel.
I also tried a stop larger than the image (rows 50:1000:7, columns 25:590:11),
and both returned the same 50 × 52 result, which shows that my Java version handles the edges the same way as NumPy.

### 4.3 Comparison

The NumPy solution needs 1 line for the slicing, while in Java I needed about 15 lines,
plus code to convert between the image and the matrix. In NumPy, slicing does not even copy the data:
it returns a *view* of the original array. In Java I had to create a new matrix and copy every selected pixel.

### 4.4 Solution by ChatGPT

**Prompts I used:**

1. "generate me 2D matrix image"
2. "how can I process images in java and python"

**How it solved the problem:**
For the first prompt, ChatGPT generated an image of a 2D matrix. Then I asked how images can be processed
in Java and Python, and it explained that an image can be treated as a matrix of pixels.
For Python, it suggested Pillow to open the image and NumPy to convert it into an array
(height × width × RGB), and it showed slicing directly on this array, `pixels[100:200, 100:200]`.
For Java, it suggested the built-in `ImageIO` and `BufferedImage` classes, and showed how to read and write single pixels
with `getRGB` and `setRGB`. It also mentioned OpenCV as a more advanced option for both languages.

**How I led it:**
Compared to Task 3, ChatGPT helped me more in this task. Instead of asking for the full solution,
I first asked it to generate a matrix image, and then asked how images are processed in both languages.
This gave me the main idea of my solution: use an image as the 2D matrix, so the result of slicing
can be seen graphically.

**Comparison with my solution:**
I used the same tools that ChatGPT suggested: Pillow and NumPy in Python, and `ImageIO`,
`BufferedImage` and `getRGB`/`setRGB`. In Java, it only showed how to access one pixel, 
so I wrote the conversion between the image and an `int[][]` matrix
and the slicing with start, stop and step myself. It also did not give a way to check that both results
are the same, so I wrote `compare.py`, which compares the two results pixel by pixel and shows them side by side.

### 4.5 How to Run

```
cd task4
pip install numpy pillow matplotlib
python3 python/slice.py          # enter: sample.png, then the slice values
java java/MatrixSlicer.java      # enter the same values
python3 python/compare.py        # enter: sample.png, python_slice.png, java_slice.png
```