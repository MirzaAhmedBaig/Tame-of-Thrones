import com.mab.tameofthrones.Kingdom
import com.mab.tameofthrones.Southeros

fun Southeros.showResult() {
    println("Who is the ruler of Southeros?")
    println("Output: ${getRulerByMajorityOfAlliance()?.name?.capitalize() ?: "None"}")
    println("Allies of Ruler?")
    println("Output: ${getRulerByMajorityOfAlliance()?.getAllies()?.getHasAlliesAsString() ?: "None"}")
}

fun Southeros.showRoundResult(roundNumber: Int) {
    println("Results after round $roundNumber ballot count")
    getCompetingKingdoms().forEach {
        println("Output : Allies for ${it.name} : ${it.getAllies().alliances.size}")
    }

}

fun Southeros.getCompetingKingdoms(): List<Kingdom> {
    return getAllKingdoms().filter { it.isCompeting }
}