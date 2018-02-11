# Changes in strategy branch

The biggest change is of course in applying the strategy pattern. In order to do this I deleted my class AbstractBlackJackPlayer that used to be eliminating duplicated code, but was no longer needed, all logic is now simply placed in BlackJackPlayer. I also removed all logic from AnotherBlackJackPlayer, and only kept it around to pass unit tests as it would be enough to only have BlackJackPlayer left.

## Strategy Pattern Details

The way I chose to implement the strategy pattern is via interfaces. In the `strategy` package there are two interfaces `BettingStrategy` and `HittingStrategy`, these outline the function that needs to be implemented for the respective strategy. The BlackJackPlayer then has had the two attributes `hittingStrategy` and `bettingStrategy` added which outlines which strategy the player should play with. By default the BlackJackPlayer plays with the cautious strategies, and the dummy `AnotherBlackJackPlayer` which is simply an extension of BlackJackPlayer now but overwriting the default strategies plays with risky strategies. I also implemented a `HouseHittingStrategy` which is the one the dealer uses (also modularizing the Override method that was previously used in the `BlackJackDealer` class), and I overrode the `placeWager` method in the dealer to throw an exception since that function should never be called from the dealer despite it being available since it extends `BlackJackPlayer`.

## Details of implemented strategies

I implemented 3 hitting strategies and 2 bidding strategies which work as follows:

### Hitting Strategies

* `CautiousBettingStrategy`: This strategy is focused on never wanting to bust, and therefore only hits if it either has an ace, or if the current total value of its hand is less than 12.

* `AggressiveHittingStrategy`: This strategy doesn't settle for anything but getting the top score, and therefore hits as long as the value of their hand isn't 21

* `HouseHittingStrategy`: This is the strategy that I through research found that the dealers usually have to play strictly by. The strategy is simply to hit until you reach a value of your hand that is at least 17. There are apparently variations whether the dealer will hit on a "soft 17" (which means that you have a total value of 17 but have an ace in your hand), and according to my research it is often the case that dealers indeed do this so I implemented this variation (to actually hit on a "soft 17" to be precise).

### Betting Strategies

My implemented betting strategies are all based on randomness as I don't think there is too much strategy in betting since in the implementation of a round in `Table.java` in the `api` package collects bets before the hand is dealt (which I believe is contradictory to casinoes where you bet after you know your initial cards), and the cards are shuffled each round so you can't count cards. They go as follows

* `CautiousBettingStrategy`: If you don't have any money left bid 0, else pick a random number between `1`, and `1/8` of the current amount of money you have and bid this.

* `RiskyBettingStrategy`: If you don't have any money left bid 0, else pick a random number between `25%` and `75%` of the money you currently have left (but bid at least 1), and bid this.

## How to add a new strategy

One simply adds a new class in the `strategies` package that implements either `HittingStrategy` or `BettingStrategy` respectively depending on whether you are adding a strategy for hitting or betting. Now using the table constructor that takes a `Collection` of `Player`s, you can construct such a `Collection`, setting the strategies explicitly on the `Player`s as seen in the system test `BlackJackStrategyGame`.