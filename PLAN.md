# TASK OVERVIEW
This assignment will have me create a program that reads in the contents of a given file and ensures the data is formatted correctly

# INDIVIDUAL TASKS
1. Create a driver class that will evaluate the format of one or more input files, 
as specified for command line arguments

2. Each file that is in the correct format, will be declared valid, 
otherwise it will be declared invalid

2.1 The correct format is as follows:
The first row contains two white space separated positive integers,
where the first value specifies the rows in a grid and the second value
specifies the columns in a grid.
Each subsequent row will contain exactly one white space separated
double value in the grid column, with the remaining being integers.

3. An exception should be handled starting with the most specific
to the most general, such as two doubles in the first row (invalid
data type), and file not found. The exception should print out
and specify the error.

# INDIVIDUAL TASK BREAKDOWN
1. I am planning on creating two classes, a driver and a checker class
which will provide the method signature to perform valid checks
on a file, and if invalid will throw the appropriate exception.

2. I am thinking about using a nested for loop that will parse through
a file and use the hasNextInt() and hasNextDouble(), since if these
two conditionals do not ever access, it is safe to assure the file will
be invalid due to the format rules.

3. I will need to provide general exceptions to specific exceptions. A general
exception that can be thrown is a file not found. Then, I can provide an exception
for an improper number format, such as a char or string value in a spot where an integer
or exactly one double should exist.

# TESTING PLAN
I will start by testing invalid values first, since they are likely the most easy to catch, ex. if a double exists more than twice in a line, the file is automatically invalid.
I will also utilize the debugger since I can see exactly where the compiler is grabbing values to ensure the appropriate exception is thrown.