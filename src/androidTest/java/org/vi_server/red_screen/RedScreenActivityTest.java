package org.vi_server.red_screen;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class org.vi_server.red_screen.RedScreenActivityTest \
 * org.vi_server.red_screen.tests/android.test.InstrumentationTestRunner
 */
public class RedScreenActivityTest extends ActivityInstrumentationTestCase2<RedScreenActivity> {

    public RedScreenActivityTest() {
        super("org.vi_server.red_screen", RedScreenActivity.class);
    }

}
