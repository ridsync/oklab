package com.okitoki.okchat

import android.content.Context
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by okwon on 2020/12/04.
 */

private const val FAKE_STRING = "HELLO WORLD"

@RunWith(MockitoJUnitRunner::class)
class VerifySMSIUnitTest {

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun createInstance() {

    }

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(true).isTrue()
    }

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        `when`(mockContext.getString(R.string.text_about_us))
                .thenReturn(FAKE_STRING)
        val myObjectUnderTest = PushNotiManager(mockContext)

        // ...when the string is returned from the object under test...
//        val result: String = myObjectUnderTest.getHelloWorldString()
//        assertThat(result).isEqualTo(FAKE_STRING)

    }

}