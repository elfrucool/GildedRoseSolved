GILDED ROSE SOLVED
==================

ABSTRACT
--------

I want to share the way I solved the Gilded Rose kata using TDD. I divided the solution in four stages,
each one can be identified with a tag in the repository.

STAGES
------

1. Decoupling iteration from item transformation and improving general instantiation using a factory (tag: **stage-1**).
1. Generating all test scenarios (tag: **stage-2**).
1. Refactoring using polimorphism instead of if-else (tag: **stage-3**).
1. Adding the new requirement for "Conjured item" (tag: **stage-4**).

# STAGE 1: DECOUPLING ITERATION FROM ITEM TRANSFORMATION (AND ALSO USING A FACTORY)

Legacy code is like a rounded piece of cheese. Imagine a mice trying to eat a big piece of perfectly rounded piece of
cheese! It simply cannot start because the cheese is exactly the same at all its parts... this is frustrating.

The advice to the little mouse and for the developer is this: start biting in the place you are exactly now.
Then the bite will be your starting point and everything will be easier.

So let's start biting this rounded cheese

## BITE 1: GET AN OUTPUT SNAPSHOT

I first will grab the program output in a file named: **ORIGINAL.txt**, this will be my reference for the
following refactor activities.


## BITE 2: INSTANTIATION REFACTORED

I will create a factory for the GildedRose class named GildedRoseFactory, the only external dependency is the list
of items to process. To do that I'll start with a test named GildedRoseTest.

By using a factory instead of the constructor directly I can play with the object configuration independently from the
program.

I ran again the program and grabbed the output in a file named: **FACTORY.txt**; it's exactly the same content as
**ORIGINAL.txt**.

## BITE 3: DECOUPLING ITERATON FROM ITEM TRANSFORMATION

Viewing the code I notice that there is a big for loop through the items, and then, inside there are all
the transformations. I will extract the transformations in an inner method.

Prior to extract the transformations from the iteration loop, I'll create the tests. For this kata I realized that
the best way to do that is using a parametric test. I'll use **junitparams** framework because it is very cool
for writing parametric tests.

I'll write a test named **canIterate** with the following parameters: label, items and _expected_.
The _expected_ variable will contain the expected result of concatenating the name of every item being iterated.
The _actual_ iteration result will be put in a property named _capturedOutput_.
So for no items, _expected_ value should be the empty string ("").

Talking about the rounded cheese, I'll do some ugly things to make the code a little testable: I'll act on the code
using the IDE capabilities to make it testable by extracting a method named **updateItem**.

To make the test pass, I'll override the **updateItem** method by forcing it to simply concatenate the item name
to the _capturedOutput_ property. The new class is an inner class inside my test class and it is named
**GildedRoseSpy**.

Finally I ran again the main program and grabbed the output in a file named **ITERATION.txt**; it's exactly the same
content as **ORIGINAL.txt**.