# Mastermind code breaker

Just a fun attempt to implement a code breaker for the Mastermind board game.

- Applies a simple **non-optimal** candidate elimination algorithm
- Works for codes of any length, any number of possible colors and with or without replacement

## Algorithm

1. Create a list containing all possible codes
2. Make a guess using the first candidate from the list  
   - If the guess is correct: print the correct code and exit  
   - Else:  
        1. Remove all candidates from the list which are not possible given the hint (black & white pins)
        2. Loop back to step 2