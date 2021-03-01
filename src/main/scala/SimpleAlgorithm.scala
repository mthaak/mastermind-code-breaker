class SimpleAlgorithm(codeLength: Int, numColors: Int, canRepeat: Boolean) extends GuessAlgorithm {

  val candidates = new SimpleListCandidates(codeLength, numColors, canRepeat)

  override def getNextGuess: String = {
    println(s"${candidates.count} candidates")
    candidates.getBestCandidate
  }

  override def giveHint(code: String, numBlack: Int, numWhite: Int): Unit = {
    candidates.eliminateCandidates(code, numBlack, numWhite)
  }

}
