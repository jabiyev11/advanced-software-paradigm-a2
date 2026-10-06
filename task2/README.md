## Task 2. Size of Tuple vs List in Python

### 2.1 Experiment

When I first ran the code given in the task, I got `SyntaxError: unmatched ')'`.
After removing the extra bracket, I got `NameError: name '__sizeof__' is not defined`.
I realized that `__sizeof__()` is a method, so it has to be called on an object.
I completed the code as follows and ran it on Python 3.13 (64-bit):

```python
tpl = (1, 2, 3)
print(tpl.__sizeof__())   # 48

lst = [1, 2, 3]
print(lst.__sizeof__())   # 72
```

I noticed that both of them hold the same three elements, but the list takes 24 bytes more than the tuple.
Before explaining the difference, I checked what `__sizeof__()` actually measures. It returns
the size of the container itself, not the elements. The tuple and the list do not store the numbers
`1, 2, 3` directly, they store references (pointers) to them, and each reference takes 8 bytes on a 64-bit system.

### 2.2 Why the Tuple Takes 48 Bytes

A tuple is immutable, so its size is known at creation and never changes.
Because of this, Python stores it as one block of memory:
a 24-byte header (reference count, type pointer and number of elements)
and the 3 references right after it, 3 × 8 = 24 bytes. In total 24 + 24 = **48 bytes**.

### 2.3 Why the List Takes 72 Bytes

A list is mutable and can grow, so its header has two extra fields:
a pointer to a separate array where the references are kept, and the capacity of that array.
This makes the header 40 bytes, which I confirmed by checking the size of an empty list.
The remaining 32 bytes is the array, and what is interesting is that it has 4 slots, not 3 (4 × 8 = 32).
This is called **over-allocation**: Python reserves extra slots in advance,
so that `append()` can use a free slot instead of allocating new memory every time.
To see this, I appended elements to an empty list one by one and printed its size after each step.
I noticed that the size changes only when the capacity runs out:

```
len = 0   size = 40    capacity = 0
len = 1   size = 72    capacity = 4   <- resized
len = 2   size = 72    capacity = 4
len = 3   size = 72    capacity = 4
len = 4   size = 72    capacity = 4
len = 5   size = 104   capacity = 8   <- resized
len = 6   size = 104   capacity = 8
len = 7   size = 104   capacity = 8
len = 8   size = 104   capacity = 8
len = 9   size = 168   capacity = 16  <- resized
```

### 2.4 Conclusion

From this experiment, I concluded that the tuple is smaller because it does not need the extra fields
and never reserves spare slots, while the list pays for them in exchange for fast `append()`.
So I would choose a tuple for fixed data, and a list for data that grows or changes.
These numbers are specific to CPython on a 64-bit system, other implementations may give different sizes.