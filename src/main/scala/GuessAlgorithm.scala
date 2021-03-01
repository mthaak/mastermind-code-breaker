trait GuessAlgorithm {

  def getNextGuess: String

  def giveHint(code: String, numBlack: Int, numWhite: Int)

}
