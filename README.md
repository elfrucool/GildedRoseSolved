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
1. Refactoring using polymorphism instead of if-else (tag: **stage-3**).
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

# STAGE 2: GENERATING ALL TEST SCENARIOS

Legacy code brings its own challenge, it is fragile, you could move a very little thing and think it won't hurt
but later probably some weeks after the change is in production, some clients will be calling or e-mailing
saying that they are very angry because their very cool stuff is not working anymore.

We could mix testing and fixing at a melodious pace, but I feel like in this case (legacy code has lots of feelings,
don't despise them) it is better having the full set of tests first (well at least I'll do my best effort to do that
and I'll try to arrive to an optimal situation before making changes into production code).

This is because we will have confidence that our changes really won't hurt some unforeseen situations.

I'll use parametric tests grouping them by the item type they represent; it means four groups (I'll also name them
bites to follow the previous convention ;) ). The main idea is that, for a single Item, when the **updateQuality**
method runs then the item state should be transformed to an expected one.

The properties that we will assess are _sellIn_ and _quality_; the name property is irrelevant for the comparison
because the Item's name never changes so the expected item can have an empty string name and it is more readable.

At a lower level of detail, I'll follow every if/else/for and so on to be sure every line of code is executed
through the tests. I will make the tests intentionally fail to ensure the tests are right and then I'll make them pass
to reflect the program behavior.

The difference among refactoring legacy code and making new code is that legacy code (at least in theory) is already
working, so at this stage the main effort is in producing accurate tests for that code.
I'll follow a from-simple-to-complex approach to drive the tests so I can keep coding in a good pace avoiding
getting stuck (I hope).

Last but not least, we need to keep in mind the requirements, we will need them.

You can track my changes of this stage with the following commit messages:

* BITE 4: NORMAL ITEM SCENARIOS
* BITE 5: SULFURAS ITEM SCENARIOS
* BITE 6: AGED BRIE SCENARIOS
* BITE 7: BACKSTAGE SCENARIOS

# STAGE 3: REFACTORING USING POLYMORPHISM INSTEAD OF IF-ELSE

**NOTE:** fixed typo: polimorphism => polymorphism :$

Now that we have the shield to protect us against breaking something we are confident to transform this ugly piece
of spaghetti into an elegant piece of good code!

We have eaten the half of the cheese, we will continue biting until we get a small piece for the last stage.

Currently here are the next bites:

* BITE 8: SIMPLIFYING OBVIOUS OPERATIONS
* BITE 9: SPLITTING BIG METHOD IN SMALLER PIECES
* BITE 10: EXTRACTING COMMON TRANSFORMATIONS
* BITE 11: PREPARING POLYMORPHISM ENVIRONMENT
* BITE 12: SULFURAS ITEM HANDLING THROUGH POLYMORPHISM
* BITE 12: AGED BRIE ITEM HANDLING THROUGH POLYMORPHISM
* BITE 12: BACKSTAGE ITEM HANDLING THROUGH POLYMORPHISM
* BITE 13: VERIFYING PROGRAM IS WORKING AGAINST OUTPUT

## BITE 8: SIMPLIFYING OBVIOUS OPERATIONS

This step will only cover reducing obvious operations to its minimal expression, a good IDE could be very helpful.

Also it could be useful temporally commenting the line prior to refactoring it just to ensure the tests
are covering it.

## BITE 9: SPLITTING BIG METHOD IN SMALLER PIECES

We can see that the **updateQuality** method has three blocks inside:

. Updating quality block
. Updating sellIn block
. Dealing with outdated item

So I'll extract three methods for that, (and comment them to ensure they are covered by the tests).
Deliberatedly most of the methods I'm creating are protected, this is for doing polymorphism later ;).

