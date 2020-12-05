package me.sivieri.day4

data class PassportWithPrecision(
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
        byr != null && byr.toInt() >= 1920 && byr.toInt() <= 2002 &&
        iyr != null && iyr.toInt() >= 2010 && iyr.toInt() <= 2020 &&
        eyr != null && eyr.toInt() >= 2020 && eyr.toInt() <= 2030 &&
        hgt != null && ((hgt.endsWith("cm") && hgt.substring(0, 3).toInt() >= 150 && hgt.substring(0, 3).toInt() <= 193) ||
                        (hgt.endsWith("in") && hgt.substring(0, 2).toInt() >= 59 && hgt.substring(0, 2).toInt() <= 76)) &&
        hcl != null &&
        ecl != null &&
        pid != null

    companion object {

        private val byrRegex = Regex("byr:([0-9]{4})\\s")
        private val iyrRegex = Regex("iyr:([0-9]{4})\\s")
        private val eyrRegex = Regex("eyr:([0-9]{4})\\s")
        private val hgtRegex = Regex("hgt:([0-9]{3}cm|[0-9]{2}in)\\s")
        private val hclRegex = Regex("hcl:(#[0-9a-f]{6})\\s")
        private val eclRegex = Regex("ecl:(amb|blu|brn|gry|grn|hzl|oth)\\s")
        private val pidRegex = Regex("pid:([0-9]{9})\\s")
        private val cidRegex = Regex("cid:([^\\s]+)\\s")

        fun parse(string: String): PassportWithPrecision =
            PassportWithPrecision(
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