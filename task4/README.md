# Assignment Report

## Task 1. Endianness (Big / Little Endian)

### 1.1 How Numbers Are Stored in Memory

Main memory, which we know as RAM, is byte-addressable. This means
that it behaves like a long array of cells which are modifiable,
each cell holds one byte (8 bits) and has its own numeric address (e.g 0xFFFFFF).
Based on the data type of the variable, it can hold several cells.
For example *long* data type in java holds 8 bytes which means it is spread across 8 consecutive cells.
An interesting question might arise such that 8 cells represent 8 different addresses,
but only one address shown for the variables while referencing memory. The answer is that variable is identified 
by the address of its first (lowest) cell, and the rest are implied by its type size
(a long at 0x1000 occupies 0x1000 to 0x1007). However, the type does not define in which order the bytes of the number
are placed across these cells, and this order is called endianness.
