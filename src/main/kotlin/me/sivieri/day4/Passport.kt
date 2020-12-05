package me.sivieri.day4

data class Passport(
    val byr: String?,
    val iyr: String?,
    val eyr: String?,
    val hgt: String?,
    val hcl: String?,
    val ecl: String?,
    val pid: String?,
    val cid: String?
) {
    fun isValid(): Boolean =
        byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null

    companion object {

        private val byrRegex = Regex("byr:([^\\s]+)")
        private val iyrRegex = Regex("iyr:([^\\s]+)")
        private val eyrRegex = Regex("eyr:([^\\s]+)")
        private val hgtRegex = Regex("hgt:([^\\s]+)")
        private val hclRegex = Regex("hcl:([^\\s]+)")
        private val eclRegex = Regex("ecl:([^\\s]+)")
        private val pidRegex = Regex("pid:([^\\s]+)")
        private val cidRegex = Regex("cid:([^\\s]+)")

        fun parse(string: String): Passport =
            Passport(
                byr = byrRegex.find(string)?.groups?.get(1)?.value,
                iyr = iyrRegex.find(string)?.groups?.get(1)?.value,
                eyr = eyrRegex.find(string)?.groups?.get(1)?.value,
                hgt = hgtRegex.find(string)?.groups?.get(1)?.value,
                hcl = hclRegex.find(string)?.groups?.get(1)?.value,
                ecl = eclRegex.find(string)?.groups?.get(1)?.value,
                pid = pidRegex.find(string)?.groups?.get(1)?.value,
                cid = cidRegex.find(string)?.groups?.get(1)?.value
            )

    }
}