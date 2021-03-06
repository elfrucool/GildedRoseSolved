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
* BITE 13: AGED BRIE ITEM HANDLING THROUGH POLYMORPHISM
* BITE 14: BACKSTAGE ITEM HANDLING THROUGH POLYMORPHISM
* BITE 15: VERIFYING PROGRAM IS WORKING AGAINST OUTPUT

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

## BITE 10: EXTRACTING COMMON TRANSFORMATIONS

We can see that many expressions are repeated among the code, so I'll extract them in common transformation methods.
We can see increments and decrements to quality surrounded by the same _if_ clauses and so on...

I noticed that there is an _if_ block for _backstage_ item (line 32) inside an _if_ for simply increment
the quality (line 29), that block can be easily extracted a level and then we have another repeated code block.

The commits will be identified as:

* BITE 10.1: EXTRACTING IF BLOCK
* BITE 10.2: EXTRACTING COMMON TRANSFORMATIONS

## BITE 11: PREPARING POLYMORPHISM ENVIRONMENT

To replace these ugly if-else blocks that handle de several types of items with an elegant polymorphistic model,
I'll delegate the entire logic of the item transformation to a class named **ItemHandler**, and the original
**GildedRose** class will be responsible only for three things:

1. Holding the items,
1. Iterating through them and
1. Choosing the right **ItemHandler** for each situation.

Later, I'll extend the **ItemHandler** class for dealing specific cases for the different item types.
That will be accomplished in bites 12 to 14.

I'll create a _defaultItemHandler_ property in **GildedRose** class for default Items, and a map named
_specialItemHandlers_ for special cases, the key will be the item name (sulfuras, aged brie, etc) and the
value will be a specific **ItemHandler** for that type of item.

The **GildedRoseFactory** class will be responsible to fill the map with the correct values.
- That's the reason I created a factory at the beginning ;) -

I made an additional parametric test and a spy class named **ItemHandlerSpy** for ensuring the delegation works
correctly.

## BITE 12: SULFURAS ITEM HANDLING THROUGH POLYMORPHISM

This is the easiest polymorphic case, in this case, the **SulfurasItemHandler** class will do nothing when
its method **updateItem** is called.

I will put an entry in the _specialItemHandlers_ map with the full name of "sulfuras" item and _null_,
I expect that every "sulfuras" scenario fails with null pointer exception to ensure the delegation will work correctly.

Later I'll put the new **SulfurasItemHandler** in place.
I'll override the _updateItem_ method and after testing I can safely remove all _if_ sentences related
to "sulfuras item"

I'll repeat this strategy with every delegation later.

We can also remove some unnecessary braces here and there.

## BITE 13: AGED BRIE ITEM HANDLING THROUGH POLYMORPHISM

Aged brie handler simply increments the quality when updating item quality and again when updating an outdated item.

The steps are the same as in "sulfuras" case.

Oops, I noticed that, in **ItemHandler** class, I have to move the _if_ at line 31 outside the method
**updateItemWhenOutdated** (I forgot doing this at _BITE 10.2_). Let's do it.

Now we can safely override the **updateItemQuality** and **updateItemWhenOutdated** methods; test and then remove
every "aged brie" _if_ clause.

## BITE 14: BACKSTAGE ITEM HANDLING THROUGH POLYMORPHISM

Although a bit more complex, it becomes easy if we follow the strategy already used.

After doing that, checking the requirements, we can see that Backstage item is a special type of Aged Brie item,
we probably should make **BackstageItemHandler** class inherit **AgedBrieItemHandler** with the convenience that
the **incrementQuality** method is not needed anymore in **ItemHandler** class.

If for any reason, the behavior of Aged Brie item changes in business but it is still true that Backstage
is a sub type of Aged Brie then we only need to modify in a single place.

## BITE 15: VERIFYING PROGRAM IS WORKING AGAINST OUTPUT

The code looks beautiful and the tests are always passing, now it is time to provide evidence that the main
application is still working.

I'll run the main program and grab the output in a file named **REFACTORED.txt**. Wow! the output is the same as
the original! But, how different the code looks! it is now really maintainable and clean.

# STAGE 4: ADDING THE NEW REQUIREMENT FOR "CONJURED ITEM"

The requirement simply says that _"Conjured" items degrade in Quality twice as fast as normal items_.

First we need to add the Conjured scenarios for the new expected behavior.

Then, using the same strategy as preceding steps, we can create a **ConjuredItemHandler** to deal with it.

The fastest modification is overriding _decrementQuality_ and call _super.decrementQuality()_ twice.

Finally we can avoid code duplication by tweaking here and there.

If we run the Program again, we will notice that Conjured item effectively degrade in quality twice as fast as normal
items. The change was really easy. I put that in a file named: **CONJURED.txt**.

Here are the final bites:

* BITE 16: TEST SCENARIOS FOR CONJURED ITEM
* BITE 17: IMPLEMENTING CONJURED LOGIC TO MAKE THE TESTS PASS
* BITE 18: AVOIDING CODE DUPLICATION

