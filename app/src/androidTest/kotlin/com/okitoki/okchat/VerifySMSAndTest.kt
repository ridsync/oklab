package com.okitoki.okchat

import android.content.Context
import android.os.Parcel
import android.text.TextUtils.writeToParcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val TEST_STRING = "This is a string"
const val TEST_LONG = 12345678L

/**
 * Created by okwon on 2020/12/04.
 */
// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4::class)
@SmallTest
class VerifySMSAndTest {

//    @Mock
//    private lateinit var mockContext: Context

    private lateinit var myProfile: MyProfile

    @Before
    fun createInstance() {
        myProfile = MyProfile.getInstance()
    }

    @Test
    fun logHistory_ParcelableWriteRead() {
        val parcel = Parcel.obtain()
//        logHistory.apply {
//            // Set up the Parcelable object to send and receive.
//            addEntry(TEST_STRING, TEST_LONG)
//
//            // Write the data.
//            writeToParcel(parcel, describeContents())
//        }
//
//        // After you're done with writing, you need to reset the parcel for reading.
//        parcel.setDataPosition(0)
//
//        // Read the data.
//        val createdFromParcel: LogHistory = LogHistory.CREATOR.createFromParcel(parcel)
//        createdFromParcel.getData().also { createdFromParcelData: List<Pair<String, Long>> ->
//
//            // Verify that the received data is correct.
//            assertThat(myProfile.idx).isEqualTo(1)
//            assertThat(createdFromParcelData[0].first).isEqualTo(TEST_STRING)
//            assertThat(createdFromParcelData[0].second).isEqualTo(TEST_LONG)
//        }
    }
}

