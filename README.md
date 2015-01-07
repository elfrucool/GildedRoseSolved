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
