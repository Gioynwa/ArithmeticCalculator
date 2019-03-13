# Arithmetic Calculator (Console Application)

## Description
__Java program that can solve any complex arithmetic expression which may include:__
1. Parenthesis
2. Operators:
	* \+ (addition)
	* \- (subtraction)
	* x or * (multiplication)
	* / (division)
	* ^ (power)
3. Positive Numbers (Integers or Floating Point)
4. Whitespace characters ([(space), \\t])

## Implementation
1. Read a mathematical expression from input
2. Check if the expression is constructed correct:
	* All parenthesis are closed
	* No closed parenthesis symbol without an opening in advance
	* No other characters included except from those referenced in description
	* There are no two operators next to each other (ex. \+\+, \-\-, x\-, /\+, etc.)
3. Construct a binary expression tree that corresponds to the given expression
4. Calculate the result

## Additional Features
This program uses [graphviz](https://www.graphviz.org/) to print the tree diagram in PNG format.

## Compile & Run
To compile just use the command ant (requires [Apache Ant](https://ant.apache.org/) to be installed).
```console
user@pc:~$ ant
```
To run the program type:
```console
user@pc:~$ java -jar dist\ArithmeticCalculator-{TIMESTAMP}.jar
```
{TIMESTAMP} is a variable depending on the compilation date.
