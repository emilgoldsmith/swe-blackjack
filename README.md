# Blackjack: strategy

## Updates

Update your Blackjack code to support strategies for betting and
hitting; from an API perspective, this can also be thought of as
placing a wager andrequesting a card, respectively. This update should
be done in a manner that is flexible to various betting and hitting
strategies. To this end, updated projects should include code
supporting an abstract framework, along with implementations of two
betting and hitting strategies.

There have been a number of changes to this repository; ideally, you
should bring your current fork inline. See the official GitHub
documentation regarding how to [sync a
fork](https://help.github.com/articles/syncing-a-fork/). If this
process is too complicated, have a look at CHANGELOG.md to see what
you should manually update (or at least things that need attention).

### Branch and document (marks: 10/100)

Create a branch of your existing code called *strategy*. This branch
is where the new implementation should go, and is the branch that will
be graded. Merging this branch with master is fine, but master will
not be looked at when grading.

The *strategy* branch should contain a file called 'RELEASE.md' that
talks about the differences between it and master, and in particular
about the various design decisions (more on that later) that were
made. If you are unfamiliar with
[Markdown](https://guides.github.com/features/mastering-markdown/),
you should use this as an opportunity to learn.

### Framework (marks: 30/100)

Once you have thought through the how your strategy frameworks will
fit in to the existing code base, add them. Specifically, create a new
[package](https://docs.oracle.com/javase/tutorial/java/package/packages.html)
within `src` and add the interfaces or abstract classes that comprise
your new design.

### Implementation (marks: 30/100)

Once the API is in place, add implementations to the package you just
created. There should be two implementations of each strategy; thus
four concrete implementations in the new package.

### Integration (marks: 30/100)

Integration of the new strategies should come with minimal disruption:

* Update `BlackJackPlayer`, and `BlackJackDealer` to use compose-able
  strategies rather than the static versions from the previous
  assignment. How those strategies are provided to individual players
  is up to you. Players should, however, be able to use a mix of
  strategies during the course of gameplay if they choose.

* Notice that `AnotherBlackJackPlayer` is now redundant (why?). You
  are free to do whatever you want with this file so long as the tests
  still pass.

* Add a constructor `BlackJackTable` that takes a collection of
  players (see the updated documentation in `api.Table` as to the
  specifics of this new constructor). This should be the only change
  made to `BlackJackTable`! In particular, the previous constructor
  that creates players should not change. Further, whether players
  change strategies during the course of a round is not the job of the
  table, so the rest of the table code should not change.

* `BlackJackHand` should not change.

## Correctness

### Unit testing

All of the existing tests should still pass. Note that a new test has
been added TableTest ensuring that BlackJackTable conforms to the new
constructor requirement.

### System testing

Create new system test that takes advantage of your new
strategy. Specifically, fill in
src/test/system/BlackJackStrategyGame.java such that

1. it is creates players with different strategies,

2. adds those players to a table,

3. plays the game to its completion.

The build file has been updated such that the test can be run:

```bash
$> ant strategy-test
```

## Expectations

We expect that you will use the Strategy Pattern to make these
changes. In essence, it should be straightforward for someone to come
along and add a new strategy to your framework by first implementing
the strategy based on your interface, and second making the necessary
alterations to plug those strategies in to new players.

With respect to documentation, as previously mentioned an updated
RELEASE.md should contain:

* How was the strategy pattern used to do this?

* For each concrete implementation, how does that implementation work
  from a Blackjack perspective? What is the basis behind its decision?

* How does one add a new betting or hitting strategy to your code?

## Submission

See the assignment listing in NYU Classes for the due date. We will
grade the code that is on your strategy branch at the exact deadline.

## Additional practice

Being able to alter players' strategies at run-time makes your game
more realistic. However, for players like the dealer, such an ability
is probably not a good idea: a rogue dealer could bankrupt your
casino!

We discussed in class ways of making composable strategies static,
however this could get hairy in situations where there are lots of
strategies that must be set (why?). Admittedly, this game is not one
of those situations, but for the sake of practice, you can pretend.

One way of dealing with this -- needing to set several parameters at
construction -- is use to the builder pattern (see
[Wikipedia](https://en.wikipedia.org/wiki/Builder_pattern), as well as
the appendix of our text book for descriptions). As additional
practice, attempt to apply this pattern to your existing code base. If
you decide to make this effort, do so on a second branch called
"strategy-builder" so as not to disrupt a pristine deliverable.

Note that while there are several references online as to how to
implement this pattern, the one outlined in Bloch's [Effective
Java](http://a.co/gb4jPfL) is probably the best. You can find a copy
via the library (which also provides access to the online edition), or
you can read a summary of the relevant chapter
[here](http://www.informit.com/articles/article.aspx?p=1216151&seqNum=2).
