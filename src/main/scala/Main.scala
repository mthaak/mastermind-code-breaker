import Common.{generateRandomCode, getHint, showHint}


object Main extends App {

  val CODE_LENGTH = 5
  val NUM_COLORS = 8
  val CAN_REPEAT = true
  val MAX_GUESSES = 20

  val secretCode = generateRandomCode(CODE_LENGTH, NUM_COLORS, CAN_REPEAT)
  println(s"Secret code: $secretCode")
  println()

  val algorithm = new SimpleAlgorithm(CODE_LENGTH, NUM_COLORS, CAN_REPEAT)

  var guess = algorithm.getNextGuess
  println(s"Initial guess (1): $guess")

  var numGuesses = 1
  while (guess != secretCode && numGuesses < MAX_GUESSES) {
    val (numBlack, numWhite) = getHint(guess, secretCode)
    println(s"Hint: ${showHint(numBlack, numWhite, CODE_LENGTH)}")
    algorithm.giveHint(guess, numBlack, numWhite)

    println()

    guess = algorithm.getNextGuess
    numGuesses += 1
    println(s"Next guess ($numGuesses): $guess")
  }

  println()

  if (guess == secretCode)
    println(s"Guessed in ${numGuesses} tries!")
  else
    println(s"Failed to guess $secretCode")
}
