titles = """
01-Ultimate Java Part1 -1.1 Fundamentals
	02-Java Part1 -1.2 Types
	03-Java Part1 -1.3 Control Flow
	04-Java Part1 -1.4 Clean Coding
	05-Java Part1 -1.5 Debugging and Deploying Applications
	06-Ultimate Java Part 2 -2.1 Object-oriented Programming
	07-Java Part 2 -2.2 Classes
	08-Java Part 2 -2.3 Refactoring Towards an Object-oriented Design
	09-Java Part 2 -2.4 Inheritance
	10-Java Part 2 -2.5 Interfaces
	11-Ultimate Java Part 3 -3.1 Advanced Topics
	12-Java Part3 -3.2  Exceptions
	13-Java Part3 -3.3 Generics
	14-Java Part3 -3.4 Collections
	15-Java Part3 -3.5 Lambda Expressions and Functional Interfaces
	16-Java Part3 -3.6 Streams
	17-Java Part3 -3.7 Concurrency and Multi-threading
	18-Java Part3 -3.8 The Executive Framework
"""
title_list = titles.split('\n')
n = len(title_list)
for i in range(n):
    title_list[i] = f"### {title_list[i].strip()}\n"
with open("test.md", "w", encoding="utf-8") as f:
    f.write("\n".join(title_list))
