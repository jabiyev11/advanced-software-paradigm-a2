import numpy as np
from PIL import Image


def read_int(prompt, minimum):
    while True:
        text = input(prompt)
        if text.strip().lstrip("-").isdigit() and int(text) >= minimum:
            return int(text)
        print(f"Please enter a whole number >= {minimum}.")


def main():
    path = input("Image path: ")
    image = np.array(Image.open(path).convert("RGB"))
    print(f"Image size: {image.shape[0]} rows x {image.shape[1]} columns")

    row_start = read_int("Row start: ", 0)
    row_stop = read_int("Row stop: ", 0)
    row_step = read_int("Row step: ", 1)
    col_start = read_int("Column start: ", 0)
    col_stop = read_int("Column stop: ", 0)
    col_step = read_int("Column step: ", 1)

    sliced = image[row_start:row_stop:row_step, col_start:col_stop:col_step]

    if sliced.size == 0:
        print("The slice is empty.")
        return
    Image.fromarray(sliced).save("python_slice.png")
    print(f"Saved python_slice.png ({sliced.shape[0]} rows x {sliced.shape[1]} columns)")


if __name__ == "__main__":
    main()
