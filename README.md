# Blackjack

## Introduction

The objective of this assignment is to build multi-player Blackjack by
implementing several card-related interfaces, including some abstract
classes. It is also a chance to get your hands dirty working with a
complete Java system: implementation, build, and execution. If you are
not familiar with Blackjack, it is worth taking a look at the
[Wikipedia page](https://en.wikipedia.org/wiki/Blackjack) to
understand its game play. Fortunately, only a basic understanding is
required at this point.

## Implementation

Your job is to implement classes within the `impl` package (directory)
based on interface specifications in the `api` package. By default,
these files are all empty. Please see code in the `api` package for
documentation on the purpose of each interface/abstract class.

The only code that should be altered is code in the `impl` package
(src/impl). All other code should not be changed. If you feel there is
a bug in non-`impl` code, or that non-`impl` code should in someway
change, please file an issue.

## Java package basics

This project uses the Java packaging system; thus, the first line in
each implementation file should be

```java
package impl;
```

which informs the build system that this class resides in the `impl`
package. See complete Java files in the other directories for more
examples.

To use other Java libraries, the `import` statement should be
employed:

```java
import java.util.List;
```

This also holds for class and interfaces developed within this
project. To use the `Card` class, for example, your implementation
must first import it:

```java
import api.Card;
```

Notice that the first thing `BlackJackGame` imports is a package
defined in your `impl` directory.

## Class layout

Package | Class<sup>*</sup> | Implements | Extends
--- | --- | --- | ---
api | Table | |
| | Hand | Comparable |
| | Card | Comparable |
| | Dealer | |
| | Player | Comparable |
impl | BlackJackTable | | Table
| | BlackJackHand | Hand |
| | BlackJackPlayer | Player |
| | AnotherBlackJackPlayer | Player |
| | BlackJackDealer | Dealer | BlackJackPlayer

<sup>*</sup>"Class" is used as a catch-all for interfaces, abstract
classes, and classes.

### Summary

Classes in `api` (and `test`) are provided; classes in `impl` are what
should be implemented for this assignment. Their definitions -- what
they extend and implement -- should follow this table exactly.

### The two players

`BlackJackPlayer` and `AnotherBlackJackPlayer` should both be
*concrete* Player types. They should both provide constructors based
on the instructions in the Player API. They should differ, however, in
how they decide to place wagers and request cards. With the exception
of the constructors, they can differ in other ways as well if you
like.

How you combine these classes is up to you. You are free to add other
files to your `impl` package to make your decision(s) possible.

### The table

The game is setup within the Table, where you should create the
dealer, along with a *mix* of players. As part of its constructor,
concrete tables should take the number of players to create. If that
number of more than one, then you should create a mix of
BlackJackPlayer and AnotherBlackJackPlayer's.

## Build

Using the Java command line tools directly -- as we have done
thus far -- is nice, but for larger projects build systems are
preferable. One well established system is
[Ant](http://ant.apache.org), a project managed by the Apache
Foundation. Ant interprets commands in an XML file to essentially run
the Java command line tools on your behalf. This project comes with a
simple Ant file you can use.

For those using the command line, enter the directory containing the
`build.xml` file and run

```bash
$> ant compile
```

to compile the code (again, rather than using `javac` directly).

For those using Eclipse, you create a new project in the IDE based on
an Ant file. Select

```
File->New->Project...
```

In the wizard, select

```
Java->Java Project from Existing Ant Buildfile
```

then click Next. Populate the "Ant buildfile" box with the path to the
build file from this homework (use "Browse" to find it on your
file system). If the "Link to the buildfile in the file system" option
is unchecked, a copy of the Java files will be created. From there,
you can "play" the files like normal.

## Correctness

### Running the program

This project expects the user to specify the number of players. You
can also specify the number of decks, but if you do not the default is
one. Thus, the project expects command line arguments (see
`BlackJackGame` for details). Fortunately, Ant takes care of this for
you; in order to run the game, type

```bash
$> ant test
```

and a game with five players and a single deck is run. If you would
like to test with a different number of players, you can do so by
running another Ant "target":

```bash
$> ant play -Dplayers=10 -Ddecks=5
```

would play a game with 10 players and five decks. Substitute any
positive integers you like to alter these values. If you are curious
about the details, see the `build.xml` to deduce where and how this is
specified. As an aside, `play` depends on `compile`, so by running
`play` the code is automatically compiled first (if compilation fails,
execution will not commence).

For those using Eclipse, command line arguments are set in the "Run
Configurations" dialogue:

```
Run->Run Configurations...
```

In the left pane, double-click `Java Application`. From there, a new
dialogue appears; by default it is likely called
"New_configuration". In the "Arguments" tab, there is a large box
called "Program Arguments" -- put a numeric value in the box and click
"Apply".

### Passing the tests

This repository comes with a number of unit tests (see the `test.unit`
package). If you are not familiar with unit testing that is okay, we
will devote a lecture to it at some point. The tests that exist test
various aspects of your Hand, Player, and Dealer implementation; feel
free to look at the respective implementations in
src/test/unit. Essentially, if you follow the comments in the API
files, you should pass.

You can run the tests via:

```
$> ant unit-test
```

The ideal out put is

```bash
unit-test:
    [junit] Running test.unit.DealerTest
    [junit] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 sec
    [junit] Running test.unit.HandTest
    [junit] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.051 sec
    [junit] Running test.unit.PlayerInitializationTest
    [junit] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 sec
    [junit] Running test.unit.PlayerInteractionTest
    [junit] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.018 sec
    [junit] Running test.unit.TableTest
    [junit] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 sec
```

The "time elapsed" may be different, but the number of failures,
errors, and skipped should not (they should all be zero). If this is
not the case, take a look at the output in the log directory to see
what happened.

## Setup

You should use GitHub judiciously! To start, fork -- **do not clone!**
-- this repository into your own account.

Because it is private, its subsequent fork will also be
private. However, because of the way GitHub permissions work, keeping
it private requires some maintenance:

1. Once you have forked the project, go the project "settings" (upper
   right).

2. Select "collaborators" (left).

3. Remove all collaborators except the course instructors: `jsw7` and
   `kqm1`.

## Grading

You will be graded along the following dimensions:

1. The program should run without error, and the run should be
   non-trivial. There should be some evidence of rounds taking place
   and bets being made. With rare exception, all players should
   eventually run out of credit and the game should come to an end.

2. Correctness of the implementation:
   * The game itself should be based on some accepted version of
     BlackJack
   * API implementations should respect their commented intentions.
   * All tests should pass.

3. Good general habits:
   * A single top-level class per Java file.
   * No [wild card](http://stackoverflow.com/q/147454) imports.
   * Programming to interfaces where appropriate. Any non-primitive
     variables should be the most abstract type possible.

4. Using parent classes correctly. In general, using the parent
   classes requires you implement anything that is abstract, and
   override methods and attributes where necessary.

5. Implementing interfaces correctly. In places where the interface
   method has a well-defined contract, that contract should be
   completely attempted.

6. Usage of GitHub:
   * There should be evidence of continued GitHub usage as you
     develop. An extreme case that is bad: a single commit just before
     the deadline. While some people are prone to lots of commits,
     there is no excuse for just a few (or less).

   * With the exception of new Java files in src/impl (if your
     two-player design decision requires such), there should be no new
     files added to the repository. In particular, files generated by
     your IDE or files generated by the Java compiler, should stay on
     your local machine.

## Notes

* Aces can count as either 1 or 11. Handle this whatever way you think
  is reasonable.

* The decision to hit can be as complicated as you like. There must be
  some logic involved, and don't worry about all players using that
  same logic.

* Initial player attributes (name, wager, bank) can be whatever you
  like. Further, the wager amount does not have to be constant
  throughout.

* Before calling yourself done, it's prudent to make a fresh clone of
  your repository and run the various ant commands within that
  clone. This ensures two things:

  1. Everything you need to run your code exists in the repo.

  2. Your code can be run via the command line. Using an IDE to
     develop is fine, but we grade via the command line, so it's good
     to ensure that things work there.

## Submission

See the assignment listing in NYU Classes for the due date. We will
grade the code that is on your master branch at the exact deadline.
