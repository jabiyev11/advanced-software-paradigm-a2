import time

import numpy as np

m = int(input("Rows of A: "))
n = int(input("Columns of A (= rows of B): "))
p = int(input("Columns of B: "))

a = np.random.rand(m, n)
b = np.random.rand(n, p)

start = time.perf_counter()
c = a @ b
end = time.perf_counter()

print(f"Time: {(end - start) * 1000:.3f} ms")
