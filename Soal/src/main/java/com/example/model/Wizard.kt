package com.example.soal.model

import kotlin.math.min

class Wizard(var nama: String) {
    var maxHp = 50
    var maxMp = 30
    var hp = 50
    var mp = 30
    var hpPotion = 5
    var mpPotion = 5
    var totalKill = 0
    var lifesteal = 0
    var evolutionStatus = false
    var multiplierDamage = 1.0

    fun drinkHealthPotion(): Boolean {
        if (hpPotion > 0) {
            hp = min(maxHp, hp + 25)
            hpPotion--
            return true
        }
        return false
    }

    fun drinkManaPotion(): Boolean {
        if (mpPotion > 0) {
            mp = min(maxMp, mp + 15)
            mpPotion--
            return true
        }
        return false
    }

    fun serangElemen(indeksSerangan: Int, elemenMusuh: String): Int {
        if (mp < 10) return -1
        mp -= 10

        var multiplierElemen = 1.0
        if ((indeksSerangan == 0 && elemenMusuh == "Grass") ||
            (indeksSerangan == 1 && elemenMusuh == "Fire") ||
            (indeksSerangan == 2 && elemenMusuh == "Water")) {
            multiplierElemen = 2.0
        }

        val totalDamage = (10 * multiplierDamage * multiplierElemen).toInt()

        if (evolutionStatus) {
            hp = min(maxHp, hp + lifesteal)
        }
        return totalDamage
    }

    fun damageDiterima(damage: Int) {
        hp -= damage
    }

    fun victory() {
        totalKill++
        if (evolutionStatus) lifesteal++
        if (!evolutionStatus && totalKill >= 5) {
            evolutionStatus = true
            multiplierDamage = 1.5
            maxHp = (50 * 1.5).toInt()
            maxMp = (30 * 1.5).toInt()
            hp = maxHp
            mp = maxMp
            lifesteal = 1
        }
    }

    fun resetKarakter() {
        maxHp = 50; maxMp = 30; hp = 50; mp = 30
        hpPotion = 5; mpPotion = 5
        totalKill = 0; lifesteal = 0
        evolutionStatus = false; multiplierDamage = 1.0
    }
}
