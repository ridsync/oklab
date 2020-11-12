package com.okitoki.okchat

import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 *  [ BDD ]
 *  Feature : 테스트에 대상의 기능/책임을 명시한다.
    Scenario : 테스트 목적에 대한 상황을 설명한다.
    Given : 시나리오 진행에 필요한 값을 설정한다.
    When : 시나리오를 진행하는데 필요한 조건을 명시한다.
    Then : 시나리오를 완료했을 때 보장해야 하는 결과를 명시한다.
 */
class ExampleUnitTest {

    /**
     * 테스트 목적 : 문자메세지 파싱
     * 기대 결과 : 문자내용중 첫번째 숫자를(4자리이상 6자리이하 제한)파싱하고 반환한다.
     */
    @Test
    fun `SMS message parsing test`() {
        // Expected Matcher
        val matcherCode = "123456"
        // 1. 성공(true)
        // 기본 6자리 인증코드
        var success = "[Web발신]\n[다톡] 인증번호 [123456]를 입력해주세요.\n\n3kSojOM1jhN"
        // 4자리 인증코드
        var message2 = "[Web] 다톡 인증번호 [1234]를 입력해주세요.\n\n3kSojOM1jhN"

        // 2. 실패(false) -> < 입력값 : null, empty , many? >
        var error1 = "[Web발신]\n[다톡] 인증번호 [123]를 입력해주세요.\n\n3kSojOM1jhN"
        var error2 = "[Web발신343433]\n[다톡] 인증번호 [123456]를 입력해주세요.\n\n3kSojOM1jhN"
        var error3 = "[Web발신]\n[다톡] 인증번호 []를 입력해주세요.\n\n"
        var error4 = "[Web발신]\n[다톡] 인증번호 [235235325325231343423432]를 입력해주세요.\n\n"
        var error5 = ""
        var error6 = null

        // 영문 메세지
        var message3 = "[Web발신] Datalk authentication code: 123456\n\n3kSojOM1jhN"

        // Actual , Matcher(Expected)
        Assert.assertThat(sendSmsAuthCode(success), Is.`is`(matcherCode))
        Assert.assertThat(sendSmsAuthCode(message2), Is.`is`("1234"))
        Assert.assertThat(sendSmsAuthCode(message3), Is.`is`(matcherCode))
        Assert.assertThat(sendSmsAuthCode(error1), Is.`is`(CoreMatchers.not(matcherCode)))
        Assert.assertThat(sendSmsAuthCode(error2), Is.`is`(CoreMatchers.not(matcherCode)))
        Assert.assertThat(sendSmsAuthCode(error3), Is.`is`(CoreMatchers.not(matcherCode)))
        Assert.assertThat(sendSmsAuthCode(error4), Is.`is`(CoreMatchers.not(matcherCode)))
        Assert.assertThat(sendSmsAuthCode(error5), Is.`is`(CoreMatchers.not(matcherCode)))
        Assert.assertThat(sendSmsAuthCode(error6), Is.`is`(CoreMatchers.not(matcherCode)))

//        message1 = message1.substringBeforeLast("\n")
//        var result1 = message1.filter { it.isDigit() }
//        assertEquals("123456", result1)
//        message2 = message2.substringBeforeLast("\n")
//        val result2 = message2.filter { it.isDigit() }
//        assertEquals("1234", result2)
//        val result3 = message3.filter { it.isDigit() }
//        assertEquals("123456", result3)
    }

    private fun sendSmsAuthCode(message: String?) : String {
        if(message == null) return ""
        try {
            val code = Regex("[0-9]+").findAll(message)
                .map(MatchResult::value)
                .filter { it.length in 4..6 }
                .first()
            if (code.isNotEmpty()) {
                return code
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }
}
