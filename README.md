# ****************
* Assignment 2: Exception Handling: FormatChecker
* CS 221
* September 12th, 2024
* Kyle Truschel
# ****************

# OVERVIEW
This program tests the validity of a file format in the form of exactly two integers in the first row and each subsequent row should contain one white space separated double value. If the format is invalid, an appropriate exception will be thrown.

# INCLUDED FILES AND FOLDERS
Checker.java - source file
ExceededStartValuesException.java - source file
FormatChecker.java - source file and driver class
PLAN.md - planning file
README.md - this file, contains important information on how to run this program

# COMPILING AND RUNNING
From the directory containing all included files, compile the driver class, FormatChecker.java, with the command:
# javac FormatChecker.java

This program may also be ran in Visual Studio with the same process.

After compiling, the program can be used to test the validity of files, that is the first two values on the first line are integers and the subsequent lines contain integers or doubles, by typing:
# java FormatChecker <file_name>

Where <file_name> is the desired file to check. Note that the files must be in the same directory as this program.

For example, an invalid file looks like:
2 2 3
3 4

Because the first two values must be integers but cannot exceed more than two. If this file was called "testfile" and ran using:

# java FormatChecker testfile

The output would be:
testfile
ExceededStartValuesException: Error: This file had more than two integers for the first line.
INVALID

The program may also take in multiple files at once using the format:
# java FormatChecker <file_name_1> <file_name_2> <file_name_n>

# KNOWN ISSUES
None at this time.

# PROGRAM DESIGN AND IMPORTANT CONCEPTS
This program is designed to teach the fundamentals of exception handling, that is to say, handle every probable scenario when attempting to perform an action. In this case, handle potential issues when opening a file given specific rules for how the file should be formatted and if
invalid, provide the terminal an appropriate informing message.

I decided to have a separate class from the driver class to minimize clutter. The driver class simply takes in string arguments, which are expected to be file names, and calls the constructor from the Checker class to do the actual checking.

Within the Checker class, a Checker object is created taking in the String name from the command line arguments, and calls the crucial readFile method signature, which will either result in being true or false, invalid or valid.

The readFile method signature consists of many important steps to handle exceptions.
- This method essentially acts as a boolean, which will return true if all checks are passed, and return false if an exception is thrown.

- The very first exception is designed to ensure the file that the user enters through the command line argument actually exists, and if not throw a FileNotFoundException message to inform the user the file does not exist.

- The second exception is designed to pass the first check, if the first value on the first line is an integer. If not, a NoSuchElementException exception message is thrown informing the user that the first value is not an integer.

- The third exception is similarly designed as the previous, but checks the second value on the first line and if it is an integer. If not, again a NoSuchElementException exception message is thrown informing the user the second value is not an integer.

- The fourth exception is a custom exception I made, appropriately named ExceededStartValuesException, which ensures that if more than two integer values exist on the first
line, an exception message is thrown informing the user that there were more than two integers, or in fact more than two of anything because I used the hasNext() method, on the first line.

- Before entering the main checking for loop, a conditional statement is used to check if no lines exist after the first line, and if so, a NoSuchElementException exception message will be thrown.

- The method continues by using a nested loop to read the rows and columns, which were retrieved in the previous steps. The first exception within this section is designed to handle
the situation where the user has reverse row and column values. That is, say the first value on the first line is 4, and the second value on the first line is 3, but there are
three columns, and four rows, somewhat similar to an out of bounds error, a NoSuchElementException will be thrown. Directly after this conditional statement, another 
NumberFormatException will be thrown if the next value scanned is not an integer or a double.

- Before leaving the inner for loop, a conditional statement exists and will throw a IllegalStateException if more values exist in a line than specified by the column ammount (The second
integer in the first line)

- Finally, before leaving the outer for loop, a similar conditional statement exists that will also throw an IllegalStateException if more values exists in a line than specified by
the row ammount (The first integer in the first line)

- If at anytime an exception is thrown, an appropriate message to inform the user of the error will be printed, along with the statment "INVALID".

- If all checks are passed, and no exceptions are throw, the input file will be considered valid and call the toString() method and the readFile() method will return true.
the output will inform the user with the statement: "VALID"

The toString() method signature acts as a way to append the "VALID" message, and is only called if the readFile passes all exception checks.

# DISCUSSION
I followed my testing plan and was relatively successfully, but there was some confusion on the test files given to us. For instance, the correct format should be two integers on the first line, and the subsequent lines should contain only one double, or none, and the rest integers... but the valid3.dat file contains two lines that have two doubles on them. I was confused at first what this meant, but in class it was specified that as long as the values in the subsequent lines are doubles (or are numbers that can be casted to a double).

I enjoyed seeing how to make custom exceptions, for given scenario. It demonstrated a proper use of inheritance, since you take a broad parent class, Exception, and make a more defined specific child class, in my case I called it "ExceededStartValuesException", since I could not find a pre-made exception case in Java's documentation to handle the scenario where only two integers should be expected on the first line.

# SOURCES
Kount Learning Center teaching assistants Gabby and Chase
- Troubleshooting

https://www.baeldung.com/java-new-custom-exception
- How to make custom exceptions