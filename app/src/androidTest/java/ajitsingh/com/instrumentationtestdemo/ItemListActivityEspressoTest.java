package ajitsingh.com.instrumentationtestdemo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ItemListActivityEspressoTest {

  @Rule
  public ActivityTestRule<ItemListActivity> main = new ActivityTestRule<ItemListActivity>(ItemListActivity.class){
    @Override
    protected void beforeActivityLaunched() {
      Intents.init();
      super.beforeActivityLaunched();
    }

    @Override
    protected void afterActivityFinished() {
      super.afterActivityFinished();
      Intents.release();
    }
  };

  @Test
  public void shouldLaunchTheMainActivityAndFindItemsInTheList() throws Exception {
    onView(withId(R.id.items_list)).check(matches(withChild(withText("Item 1"))));
    onView(withId(R.id.items_list)).check(matches(withChild(withText("Item 2"))));
    onView(withId(R.id.items_list)).check(matches(withChild(withText("Item 3"))));
    onView(withId(R.id.items_list)).check(matches(withChild(withText("Item 4"))));
  }

  @Test
  public void shouldShowTheItemDetailWhenAnItemIsClicked() throws Exception {
    intending(hasComponent(ItemDetailActivity.class.getName()))
      .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, new Intent()));

    onView(withText("Item 1")).perform(click());
    intended(hasComponent(ItemDetailActivity.class.getName()));
  }
}