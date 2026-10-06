import matplotlib.pyplot as plt
import numpy as np
from PIL import Image


def main():
    original_path = input("Original image path: ")
    python_path = input("Python result path: ")
    java_path = input("Java result path: ")

    original = np.array(Image.open(original_path).convert("RGB"))
    python_result = np.array(Image.open(python_path).convert("RGB"))
    java_result = np.array(Image.open(java_path).convert("RGB"))

    identical = np.array_equal(python_result, java_result)
    print(f"Python result: {python_result.shape[0]} x {python_result.shape[1]}")
    print(f"Java result:   {java_result.shape[0]} x {java_result.shape[1]}")
    print(f"Identical: {identical}")

    fig, axes = plt.subplots(1, 3, figsize=(15, 5))
    for ax, img, title in zip(axes, [original, python_result, java_result],
                              ["Original", "NumPy slice", "Java slice"]):
        ax.imshow(img)
        ax.set_title(f"{title} ({img.shape[0]} x {img.shape[1]})")
    fig.suptitle(f"Results are identical: {identical}")
    fig.tight_layout()
    fig.savefig("comparison.png")
    print("Saved comparison.png")


if __name__ == "__main__":
    main()
