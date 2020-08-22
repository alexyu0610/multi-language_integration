# Multi-language Integration

* Traditional multi-language project is usually done in integration of C because C is fast.
    * Examples: Emacs (C / Lisp), Adobe Lightroom (C++ / Lua), NRAO Telescope (C / Python), Google Android (C / Java), most games (C++ / Lua), etc.
    * Use C for performance-critical kernels and low-level OS or hardware interfacing

* Bytecode multi-language project.
    * Python bytecode, Java Bytecode, Java Virtual Machine, Java Runtime Environment
    * Java, Scala, JRuby, JScheme, Jython, Fortress, etc.

* Choosing the best tool for the job

| Task                  | Language / Framework |
| --------------------- | -------------------- |
| Concurrency           | F#/OCaml/Ruby        |
| Low-level OS/Hardware | C or C++             |
| Rapid prototyping     | Python or Lua        |

* Sample walkthrough
    * Background: One of the building blocks of modern cryptography is the one-time pad.
    * To encrypt plaintext P with a key K (the one time pad) you produce cyphertext C as follows:
        * cyphertext[i]  = plaintext[i] XOR keytext[i] (XOR is convenient way in simplified crytography. A lot of low power implementation of security algorithms uses XOR rather than more sophisticated algorithms)
        * A constant key mask may be also used for testing
    * Steps:
        1. Check whether or not the parameter passed in is a keytext(string) or a mask(integer) for testing.
        1. Apply changes according to type of argument
        1. Return cyphertext. 

* Example included is in Java, implementation in python can be done via cpython
 and duck typing as intermediate language helper function. 