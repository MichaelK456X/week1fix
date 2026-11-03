package com.example.soal

import java.util.Scanner
import java.util.Random
import com.example.soal.model.Wizard

fun main() {
    val input = Scanner(System.`in`)
    val random = Random()
    val daftarElemen = arrayOf("Fire", "Water", "Grass")

    println("Who is your name?")
    val namaAwal = input.nextLine()
    val player = Wizard(namaAwal)
    println("Good luck , ${player.nama}! because you are going to need it")

    while (true) {
        println()
        println("What are you going to do?")
        println("1. View Stats")
        println("2. Enter battle")
        print("Pilih: ")
        val pilihanMenu = input.nextLine()

        if (pilihanMenu == "1") {
            while (true) {
                println()
                println("--- ${player.nama}'s Stats ---")
                println("HP: ${player.hp}/ ${player.maxHp}")
                println("Mana: ${player.mp}/ ${player.maxMp}")
                println("Kills you need to evolve: ${player.totalKill}/ 5")
                println("Mana Potions you have: ${player.mpPotion}")
                println("Health Potions you have: ${player.hpPotion}")
                println("-------------------")

                println("a. Drink Mana Potion")
                println("b. Drink Health Potion")
                println("c. Rename youself")
                println("d. Back")
                print("Pilih: ")
                val opsiStats = input.nextLine()

                if (opsiStats.equals("a", true)) {
                    player.drinkManaPotion()
                } else if (opsiStats.equals("b", true)) {
                    player.drinkHealthPotion()
                } else if (opsiStats.equals("c", true)) {
                    print("New name: ")
                    player.nama = input.nextLine()
                } else if (opsiStats.equals("d", true)) break
            }
        } else if (pilihanMenu == "2") {
            val elemenMusuh = daftarElemen[random.nextInt(3)]
            val namaMusuh = elemenMusuh + "mon"
            var hpMusuh = 30

            while (hpMusuh > 0 && player.hp > 0) {
                println()
                println("--- BATTLE ---")
                println(player.nama)
                println("HP: ${player.hp}/ ${player.maxHp}")
                println("Mana: ${player.mp}/ ${player.maxMp}")
                println("HP Potions: ${player.hpPotion}")
                println("MP Potions: ${player.mpPotion}")
                println()
                println(namaMusuh)
                println("HP: $hpMusuh")
                println("Type: $elemenMusuh")
                println("--------------")

                println("a. Fire Attack")
                println("b. Water Attack")
                println("c. Grass Attack")
                println("d. Drink potion")
                println("e. Run")
                print("Pilih: ")
                val opsiBattle = input.nextLine()
                if (opsiBattle.equals("e", true)) {
                    println("You run away"); break
                }
                if (opsiBattle.equals("d", true)) {
                    println("1. HP Potion")
                    println("2. MP Potion")
                    print("Pilih: ")
                    val pilihanPotion = input.nextLine()
                    if (pilihanPotion == "1") {
                        player.drinkHealthPotion()
                    } else if (pilihanPotion == "2") {
                        player.drinkManaPotion()
                    }
                    continue
                }

                val indeksSerangan =
                    if (opsiBattle.equals("a", true)) 0
                    else if (opsiBattle.equals("b", true)) 1
                    else if (opsiBattle.equals("c", true)) 2
                    else -1
                if (indeksSerangan == -1) continue

                val totalDamage = player.serangElemen(indeksSerangan, elemenMusuh)
                if (totalDamage == -1) {
                    println("Not enough mana"); continue
                }

                hpMusuh -= totalDamage
                println("You deal $totalDamage damage")

                if (player.evolutionStatus) {
                    println("Lifesteal success! you recover ${player.lifesteal} HP.")
                }

                if (hpMusuh > 0) {
                    player.damageDiterima(10)
                    println("Enemy dealt 10 damage to you!")
                }
            }

            if (player.hp <= 0) {
                println("You die, Game restart")
                player.resetKarakter()
            } else if (hpMusuh <= 0) {
                println("You defeated the enemy")
                val oldStatus = player.evolutionStatus
                player.victory()
                if (!oldStatus && player.evolutionStatus) {
                    println("Congrats! You evolved into a Strong Wizard")
                }
            }
        }
    }
}

