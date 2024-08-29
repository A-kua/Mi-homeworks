package fan.akua.day11

import java.nio.charset.StandardCharsets

class IntroductionBuilder {
    private val lines = mutableListOf<String>()

    fun introduce(block: IntroductionBuilder.() -> Unit) {
        this.block()
        printIntroduction()
    }

    fun name(value: String) {
        lines.add("我是 $value。")
    }

    fun skills(vararg values: String) {
        lines.add("我会 ${values.joinToString(", ")}。")
    }

    fun learning(value: String) {
        lines.add("我在学习 $value。")
    }

    private fun printIntroduction() {
        lines.forEach { println(it) }
    }
}

fun introduction(block: IntroductionBuilder.() -> Unit) {
    IntroductionBuilder().introduce(block)
}

fun main() {
    System.setOut(java.io.PrintStream(System.out, true, StandardCharsets.UTF_8.name()))
    introduction {
        name("谭哲昊Y02114562")
        skills("Java", "Kotlin", "C", "Cpp", "GoLang", "Rust", "Python", "NixLang")
        learning("Android Framework")
    }
}