trait Candidates {

  def eliminateCandidates(code: String, numBlack: Int, numWhite: Int)

  def getBestCandidate: String

  def count: Int

}
