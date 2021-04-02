package com.groupeleven.studentlife.testHelper;

import android.os.IBinder;
import android.view.WindowManager;
import androidx.test.espresso.Root;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class MyToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("toasted");
    }

    @Override
    public boolean matchesSafely(Root root){

        Boolean returnVal = false;
        int paramsType = root.getWindowLayoutParams().get().type;

        if ((paramsType == WindowManager.LayoutParams.TYPE_TOAST)){
            IBinder windowTk = root.getDecorView().getWindowToken();
            IBinder appTk = root.getDecorView().getApplicationWindowToken();

            if(windowTk == appTk){
                returnVal = true;
            }
        }

        return returnVal;
    }

}
