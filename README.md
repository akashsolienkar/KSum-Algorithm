K-Sum Algorithm Implementation

Overview

This project provides a generic recursive solution to find 'K' numbers in an array that sum up to a given target using a HashMap for efficient lookups.

Features:

Supports any 'K' sum problem efficiently.

Utilizes recursion with memoization to optimize performance.

Ensures unique index selection to avoid duplicate usage of elements.

How It Works

The algorithm follows these key steps:

Initialization: At the first recursion level, a HashMap stores initial elements and their complements.

Recursive Computation: The method iterates through previous sums and updates them with new elements while ensuring no duplicate indices are used.

Validation: If the required number of elements is found and the sum matches the target, the indices are returned.

Usage

Prerequisites

Java Development Kit (JDK) 8 or later

Any Java-compatible IDE (e.g., IntelliJ, Eclipse, or VS Code)

Running the Code

Clone the repository:

git clone-https://github.com/akashsolienkar/KSum-Algorithm.git

Navigate to the project directory:

cd your-repo-name

Compile and run the program:

javac KSum.java
java arraysandstrings.KSum

Example Input & Output

Input:

int[] nums = {-50, -48, -47, ..., 50}; // Example array
int target = 50;
int k = 10;

Output:

Indices: 32, 2, 1, 0, 3, 98, 75, 76, 78, 77
Computed Sum: 50 (Target: 50)
Execution Time: 30.245208 ms

Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

License

This project is open-source and available under the MIT License.
