package com.okitoki.okchat

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        // 기본 6자리 인증코드
        var message1 = "[Web발신]\n[다톡] 인증번호 [123456]를 입력해주세요.\n\n3kSojOM1jhN"
        // 4자리 인증코드
        var message2 = "[Web] 다톡 인증번호 [1234]를 입력해주세요.\n\n3kSojOM1jhN"
        // 영문 메세지
        var message3 = "[Web발신] Datalk authentication code: 123456\n\n3kSojOM1jhN"

//        message1 = message1.substringBeforeLast("\n")
        message2 = message2.substringBeforeLast("\n")
        message3 = message3.substringBeforeLast("\n")

//        val result1 = message1.filter { it.isDigit() }
        val numbers = Regex("[0-9]+").findAll(message1)
            .map(MatchResult::value)
            .toList()
        println( "${numbers[0]} ${numbers[1]}")
        val result1 = numbers[0]
        val result2 = message2.filter { it.isDigit() }
        val result3 = message3.filter { it.isDigit() }
        assertEquals("123456", result1)
        assertEquals("1234", result2)
        assertEquals("123456", result3)
    }
}
