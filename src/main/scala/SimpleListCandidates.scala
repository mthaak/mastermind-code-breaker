import scala.annotation.tailrec

class SimpleListCandidates(codeLength: Int, numColors: Int, canRepeat: Boolean) extends Candidates {

  /** Simple implementation of candidates using a list. */

  private var candidates = generateAllCandidates(codeLength, numColors).to(List): List[String]

  override def eliminateCandidates(code: String, numBlack: Int, numWhite: Int): Unit = {
    this.candidates = this.candidates.filter(candidate => canBeCandidate(candidate, code, numBlack, numWhite))
  }

  override def getBestCandidate: String = candidates.last // TODO this could be optimized

  override def count: Int = candidates.size

  def generateAllCandidates(codeLength: Int, numColors: Int): List[String] = {

    def generate(codes: List[String], codeLength: Int, colors: List[Int]): List[String] = {
      if (codeLength == 0)
        return codes
      colors.flatMap(color => {
        val newCodes = codes.map(code => code ++ color.toString)
        val nextColors = if (canRepeat) colors else colors.patch(colors.indexOf(color), Nil, 1)
        generate(newCodes, codeLength - 1, nextColors)
      })
    }

    generate(List(""), codeLength, List.range(0 + 1, numColors + 1))
  }

  @tailrec
  final def canBeCandidate(candidate: String,
                           guess: String,
                           numBlack: Int,
                           numWhite: Int,
                           index: Int = 0): Boolean = {

    if (numBlack < 0 || numWhite < 0)
      false

    else if (index == guess.length)
      numBlack == 0 && numWhite == 0

    else if (guess(index) == candidate(index))
      canBeCandidate(candidate.updated(index, '0'), guess, numBlack - 1, numWhite, index + 1)

    else if (candidate.contains(guess(index)))
      canBeCandidate(candidate.updated(candidate.indexOf(guess(index)), '0'), guess, numBlack, numWhite - 1, index + 1)

    else
      canBeCandidate(candidate, guess, numBlack, numWhite, index + 1)
  }

}
