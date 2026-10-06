## Task 3. Matrix Multiplication: NumPy vs Java

I implemented matrix multiplication in Python with NumPy and in Java with three nested loops.
In both programs the user enters the matrix sizes, the matrices are filled with random numbers,
and the time of the multiplication is printed. The Java implementation is tested with 5 JUnit tests, and all of them pass.

### 3.1 Code Size

In Python the multiplication is a single operator, because NumPy already implements it internally. So it was faster to 
write it compared to Java and in general to other C like languages. In Java I had to write the loops, allocate the result
matrix and check the dimensions myself. The program is longer mainly because of reading input with `Scanner`
and filling the matrices with random numbers, which NumPy does with `np.random.rand(m, n)`.

### 3.2 Execution Time

I ran both programs 3 times for square n × n matrices and took the average:

```
Size            Java          NumPy       Faster
2x3 by 3x2      0.004 ms      0.060 ms    Java ~15x
128             9.6 ms        0.96 ms     NumPy ~10x
256             27.9 ms       0.97 ms     NumPy ~29x
512             133.7 ms      2.3 ms      NumPy ~57x
1024            1508.7 ms     14.8 ms     NumPy ~102x
```

What I noticed from the results:

- For the very small matrices Java was faster, because every NumPy call has a fixed overhead,
which is larger than the 12 multiplications themselves.
- From n = 128 NumPy was faster for every size, and the gap kept growing: about 10 times for n = 128 and about 100 times for n = 1024.
- For NumPy, n = 128 and n = 256 took almost the same time (0.96 and 0.97 ms), which shows that for small sizes
  its time is mostly the fixed overhead, not the multiplication itself.
- The Java times include JVM warm-up, because each run measures a single multiplication
  before the JIT compiler has fully optimized the code.

### 3.3 Conclusion

NumPy needs much less code and is faster.
Its advantage becomes even bigger as the matrices grow.

### 3.4 Solution by ChatGPT

**Prompts I used:**

1. "can you show different way of matrix multiplication in python?"
2. "why python is faster than java"
3. "how can java be optimized"

**How it solved the problem:**
For the first prompt, ChatGPT suggested NumPy as the standard choice and multiplied two hard-coded 2x2 matrices
with `A @ B`, also mentioning `np.matmul` and `np.dot` as alternatives. It also showed a pure Python version
with three nested loops, and listed other libraries such as SciPy, PyTorch and TensorFlow.
Its multiplication itself is the same as mine (`a @ b`), but the matrices were hard-coded,
there was no user input and no time measurement.

When I asked why Python was faster than Java in my results, it explained that Python is not actually doing
the multiplication: NumPy passes the work to optimized native code (C/Fortran and BLAS libraries).
It also pointed out that Python is faster to *write*, while Java is usually faster to *execute* for plain loops.

When I asked how Java can be optimized, it explained JVM techniques such as JIT compilation of frequently
executed code, garbage collector tuning, and CPU-specific instructions (SIMD).

**How I led it:**
My first prompt only gave a basic example, so I used follow-up questions based on my own measurements
to make it explain the difference between the two implementations, instead of just showing code.

**Comparison with my solution:**
ChatGPT's explanation of why NumPy is fast matches what I found in my experiment.
However, it said that Java's JIT can optimize the triple loop "heavily", while in my measurements
my Java code was still about 100 times slower than NumPy for n = 1024. It also did not mention the loop order
or CPU cache, which I found to be an important reason for the slowdown, and its suggestions were general JVM features
that I cannot directly control from my code. It also did not write a Java implementation or unit tests,
so this part of the task was done only by me.