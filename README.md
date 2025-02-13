# Autocorrect-tool

This Java-based autocorrect tool takes a user-inputted word and suggests the closest word from a dictionary file using the Levenshtein Distance algorithm. It reads words from a dictionary file and compares them with the input word to find the best match.

Features
Reads a dictionary file (dictionary.txt)
Uses Levenshtein Distance to determine the closest match
Provides real-time autocorrection suggestions
Simple command-line interface

Compile:
javac Main.java
Run:
java Main


 How It Works
Loads words from dictionary.txt
Asks the user to input a misspelled word
Uses Levenshtein Distance to find the closest match
Suggests a corrected word
