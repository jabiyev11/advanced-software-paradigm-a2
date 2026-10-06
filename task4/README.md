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

### 1.2 Big Endian vs Little Endian

There are two main ways of ordering the bytes of a multi-byte value:

- **Big endian** stores the most significant byte (the "big end") in the lowest address, so the bytes appear
in the same order as we write the number.
- **Little endian** stores the least significant byte (the "little end") in the lowest address,
so the bytes appear in reverse order.

For example, the long value 0x1122334455667788 stored at address 0x1000 is placed as follows:

```
Address:        0x1000 ................ 0x1007
Big endian:     11 22 33 44 55 66 77 88
Little endian:  88 77 66 55 44 33 22 11
```

What is interesting is that only the order of the bytes is reversed,
not the bits inside each byte (`88` stays `88`). This structure of the memory can only 
create a problem when bytes move between systems with different orders, for example through a file or a network.

### 1.3 Use Cases

I made some research on the use cases, and both byte orders
are still in use today, usually in different layers of a system:

- **Little endian** is used by most modern processors: Intel and AMD (x86 / x86-64),
ARM as used in phones and Apple Silicon Macs, and RISC-V. Some file formats,
such as BMP images and WAV audio, also store their numbers in little endian.
- **Big endian** is used mainly in data that travels between systems.
The TCP/IP protocols store their header fields in big endian. Also, older or specialised 
processors such as IBM mainframes (z/Architecture) and SPARC are big endian,
and file formats such as PNG and JPEG use it as well.

In Java, the byte order of the CPU is hidden from the programmer: a long always behaves the same on any machine.
It becomes visible only when values are turned into raw bytes.