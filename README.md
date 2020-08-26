![](.common/joels-private-stock.png?raw=true)

# The Dependency Game

What is all of this stuff? Dependency injection! Inversion of control, loose vs tight coupling, the list
goes on and on.
The dependency game is a dive into understanding the important concept of dependency injection, and all the other stuff that touches on it:
Uncle Bob Martin's dependency inversion principle, and his single responsibility, open/closed, and Liskov substitution
principles. That's four of his five SOLID principles right there.
Johnson and Foote's Inversion of Control (1988), used by Mattsson (1996) and then popularized by Uncle Bob and Martin Fowler (2004),
Craig Larman's GRASP patterns including the creator, low coupling, high cohesion, and pure fabrication patterns.

There are plenty of scholarly articles that you can go find and read. As often as you would like.
Start with a search for SOLID and GRASP at wikipedia.org.
That stuff is great, but we want to see how this works in a pratical sense:
why does it make sense to use dependency injection in real programming?
How do we use it in a real program?
What is so bad in our programming that we needed a way to fix it?
At the same time we will keep track of which principles are supported by what we do,
so then you know what you have to look for.

## License

The code is licensed under the MIT license. You may use and modify all or part of it as you choose, as long as attribution to the source is provided per the license. See the details in the [license file](./LICENSE.md) or at the [Open Source Initiative](https://opensource.org/licenses/MIT)

### Software Configuration

The library code targets Java 1.8 and above. The JUnit tests are built with JUnit 5 and Jupiter.

### Dependencies

* com.wonderfulwidgets.retail:credit-card-validator
* com.wonderfulwidgets.retail:the-bank-of-random-creit-authorizer
* com.wonderfulwidgets.retail:everyone-is-authorized

### Setup

The dependency projects are not in the Maven repository, so you have to do a little more work to get them
onto your computer. 

1. You will need an IDE configured for Java, and with Maven, such as Eclipse or InteliJ IDEA.
2. Clone the following three projects from GitHub (change `git@github.com:jmussman` to
   https://github.com/jmussman if you want to use https instead of ssh):
    * `git clone git@github.com:jmussman/credit-card-validator.git`
    * `git clone git@github.com:jmussman/The-Bank-of-Random-Credit-Authorizer.git` 
    * `git clone git@github.com:jmussman/Everyone-is-Authorized-Client.git`
3. Run a Maven install for the each project:
    * With the project open in your IDE use the Maven commands to run the "install" goal
    * Or, at the command line in the project run "mvn install" (you have to have Maven installed as a command on your computer)
4. Clone this project (The Dependency Game) from GitHub to your computer:
    * `git clone git@github.com:jmussman/The-Dependency-Game.git` 
5. Open this project in your IDE as a Maven project (import as Maven, add as Maven, etc.).
6. You are sitting on the starting point for the project steps in the master branch.
   The solution code (in three parts) is on the "solution" branch.

### Project Steps

Note: you will have to import packages for many of these steps in the Java files.
The steps/instructions for doing that is different in each IDE, and are not provided in the steps below to keep them simple.

#### Part 1

1. Tour the SalesOrderItem, SalesOrder, and SalesOrderManager classes.
2. Tour and run SalesOrderTests: everything will work.
3. Tour and run SalesOrderManagerTests several times.
    * The test results are not consistent, because the BankOfRandomCreditAuthorizer is randomly authorizing cards.
4. Our manager wants us to change the service to the Everyone is Authorized service, their client is “AlwaysAuthorize”.
5. First look at the SalesOrderManager class to fix some basic "code smells":
    * How can we make this have a single return statement?
    Change the code to use a boolean “result” variable initially set to false,
    and change it to true if the sale is completed.
    Return the result variable as the last step in the method.
    * How can we reduce the if statements?
    Reverse the logic of checking for ZERO to see if it does NOT return true, and && it with the authorizer call
    from the second if statement.
    Then,  get rid of the variable entirely and simply return the result of the expression.
    * Don’t initialize member variables in static space.
    Make a no-argument constructor that initializes the authorizer member, that is what constructors
    are for.
6. Now change the data type of the authorizer to AlwaysAuthorize.
7. Fix the initialization of the authorizer in the constructor to make a new AlwaysAuthorize.
8. Change the method call for the authorizer:
   the name changes from "purchase" to "authorize" call, put the amount first and change it from BigDecimal to a double.
9. Run the test, everything runs true because this authorizer always returns true.
10.	Unfortunately our manager has realized that we’re not getting paid from the new service,
    because all those authorizations are not getting paid by the banks.
    We need to go back to the previous authorizer!
    
Editing the SalesOrderManager every time to change this is a clear violatation of the SOLID open/closed principle.
So we are going to fix it right, by using an interface and subsititution! 	

#### Part 2

12.	In part one we realized we cannot keep changing the code.
Make an interface, ICreditCardAuthorizer, that has a single method "authorize" that takes a BigDecimal amount
and a String card number.
Then in the SalesOrderManager class change it to use the new interface for the authorizer data type.
13. The authorization classes from the two services are in JAR files and cannot be changed. Create a new "BRCAdapter" class that implements in the interface we just made.
14. In BRCAdapter implement the authorize method. Create an instance of BankOfRamdonCreditAuthorizer. In this case the tight coupling with the dependency is OK.
15. Call the "purchase" method of the BankOfRandomCreditAuthorizer passing it the amount and card number, and return the String token.
16. Make a new "EAAdapter" that does a similar job to sit in front of the AlwaysAuthorized class.
17.	Change the constructor in SalesOrderManager to initialize the authorizer with a new BRCAdapter.
18. Run the SalesManagerTests.
    You should see random success and failure, but we simplified the solution to change the code
    for a new authorizer.
    
These steps implemented the "Adapter Pattern", which you can find in the book
"Design Patterns: Elements of Reusable Object-Oriented Software", Gamma (1994).

It falls in line with Uncle Bob's SOLID principles:
open/closed principle because we minimize editing the code to change the authorizer,
the Liksov substitution principle becuase all authorizers have to exhibit similar behavior,
and most importantly the dependency inversion principle because now the
SalesOrderManager depends on the abstraction (the interface).
And also Larman's low coupling, because the class is less tightly bound to the authorizer class.

But, the SalesOrderManager violates the SOLID single responsibility principle:
it handles both the authorization and deciding what authorizer to use.
And, it is not so good with Larman's low coupling:
it still creates the authorizer and therefore is tightly bound to that class.

This will all be fixed with dependency injection!

#### Part 3

20. In the SalesOrderManager change the constructor to expect an ICreditCardAuthorizer instance.
21. Assign the parameter to the authorizer member variable, replacing the statement that instantiated the authorizer.
22. In the SalesOrderManagerTests, initialize an ICreditCardAuthorizer variable in the setup method.
23. Change the instantiation of the SalesOrderManager and pass it the ICreditCardAuthorizer.

    This is "dependency injection", the SalesOrderManager no longer decides what authorizer it needs,
    it depends on something else providing it.

    Two great ways to do dependency injection: pass the value through a constructor,
    or pass it to a setter method.
    And, wherever you use dependency injection you are practicing Uncle Bob's dependency inversion principle, Larman's
    low coupling, and inversion of control (IoC).
    The SalesOrderManager only knows about the interface!

24. Run the tests, but the results are still random.
    But, at least we have dependency injection now and we can make it better!
25. In the SalesOrderManagerTests setup method remove the instantiation of the ICreditCardAuthorizer.
26. Annotate the test class with "@ExtendWith(MockitoExtension.class)".
    This makes the tests in this class execute with the Mockito test runner instead of the regular JUnit 5 runner.
27. Annotate the ICreditCardAuthorizer member with "@Mock".
    This annotation tells Mockito to assign to the variable a test-double object that implements the interface.
28. In the orderIsCompleted test method add a Mockito statement to define the behavior of the test-double (the mock)
    when the interface method is called with a good card number:
    
    when(authorizer.authorize("378282246310005")).thenReturn(true);
    
29. The statement just added "programs" the test-double to behave a certain way given a certain input.
    The test-double is already injected into the SalesOrderManager via the constructor in the setup method.
    Run the tests, the test-double is deterministic and the test will always pass.

### Conclusion

This example accomplished three things:
it cleaned up some code smells, it established the Adapter Pattern 
to manage the external service classes,
and it set up "dependency injection" to pass the authorizer
to the SalesOrderManager when it is created.

Dependency injection is crucial for changing dependencies without violating principles.
It makes the code more adaptable to future changes like adding another authorizer and adapter,
and it is the only way to allow unit tests to inject test-doubles to build deterministic tests.

<hr>
Copyright © 2020 Joel Mussman. All rights reserved.