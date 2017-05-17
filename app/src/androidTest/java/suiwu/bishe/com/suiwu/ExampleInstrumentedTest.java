package suiwu.bishe.com.suiwu;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("suiwu.bishe.com.suiwu", appContext.getPackageName());
        int hhhhh = 0;
        try {
            hhhhh = Integer.parseInt("hhhhh");
        } catch (NumberFormatException e) {
            Log.e("intTest", "无效int");
        } finally {
            Log.e("int", String.valueOf(hhhhh));
        }


    }
}
