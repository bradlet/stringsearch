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

Other environment variables available to alter runtime behavior:  

'pattern' -- the pattern you'd like to search for.  
**Note**: test set is randomly generated, so 
complex patterns may be unlikely to appear.
> Values: Any non-empty string

'x' -- number of times to run the tests.
> Values: Any positive integer

'verbose' -- add text to print all matches found.
> Values: 'true', or 'false'
