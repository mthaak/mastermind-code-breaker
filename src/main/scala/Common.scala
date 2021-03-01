import scala.annotation.tailrec

object Common {

  val random = new scala.util.Random()

  @tailrec
  def getHint(guess: String,
              secretCode: String,
              index: Int = 0,
              numBlack: Int = 0,
              numWhite: Int = 0): (Int, Int) = {
    assert(guess.length == secretCode.length)

    if (index == guess.length)
      (numBlack, numWhite)

    else if (guess(index) == secretCode(index))
      getHint(guess, secretCode.updated(index, '0'), index + 1, numBlack + 1, numWhite)

    else if (secretCode.contains(guess(index)))
      getHint(guess, secretCode.updated(secretCode.indexOf(guess(index)), '0'), index + 1, numBlack, numWhite + 1)

    else
      getHint(guess, secretCode, index + 1, numBlack, numWhite)
  }

  def generateRandomCode(size: Int, numColors: Int, canRepeat: Boolean): String = {

    @tailrec
    def addChar(colors: List[Int], size: Int, prefix: String = ""): String = {
      if (size == 0)
        prefix
      else {
        val index = random.nextInt(colors.length)
        val nextColors = if (canRepeat) colors else colors.patch(index, Nil, 1)
        addChar(nextColors, size - 1, prefix + colors(index).toString)
      }
    }

    addChar(List.range(1, numColors + 1), size)
  }

  def showHint(numBlack: Int, numWhite: Int, codeLength: Int): String = {
    "x".repeat(numBlack) ++ "0".repeat(numWhite) ++ "_".repeat(codeLength - numBlack - numWhite)
  }

}
