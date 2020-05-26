# stringsearch
Final project for CS584 Algorithm Design / Analysis (Spring 2020)

### Project Overview
This repo contains an implementation for the Knuth-Morris-Pratt
and Boyer-Moore String Search algorithms, as well as testing 
for those implementations.

#### Developed By: Bradley Thompson

### Technology
- Kotlin
- Gradle

### How to use this program
To run the main program, type:
> gradle run  

To run unit tests:
> gradle clean test

You can manipulate the size of the test sets with environment
variables. To do so, type "N=___" before "gradle run". For example:
> N=150000 gradle run