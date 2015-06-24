package ajitsingh.com.instrumentationtestdemo;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemListActivityTest {

  @Rule
  public ActivityTestRule<ItemListActivity> main = new ActivityTestRule<ItemListActivity>(ItemListActivity.class);


  @Test
  public void testShouldLaunchTheMainActivityAndFindItemsInTheList() throws Exception {
    ListView listview = (ListView) main.getActivity().findViewById(R.id.items_list);

    assertThat(listview.getCount(), is(4));
  }

  @Test
  public void testShouldTestTheItemNameInTheList() throws Exception {
    ListView listview = (ListView) main.getActivity().findViewById(R.id.items_list);

    assertThat((String)listview.getItemAtPosition(0), is("Item 1"));
    assertThat((String)listview.getItemAtPosition(1), is("Item 2"));
    assertThat((String)listview.getItemAtPosition(2), is("Item 3"));
    assertThat((String)listview.getItemAtPosition(3), is("Item 4"));
  }

  @Test
  public void testShouldShowTheItemDetailWhenAnItemIsClicked() throws Exception {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    final ListView listview = (ListView) main.getActivity().findViewById(R.id.items_list);

    instrumentation.runOnMainSync(new Runnable() {
      @Override
      public void run() {
        listview.performItemClick(listview.getChildAt(0), 0, listview.getAdapter().getItemId(0));
      }
    });

    Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(ItemDetailActivity.class.getName(), null, false);
    Activity itemDetailActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);

    TextView detailView = (TextView) itemDetailActivity.findViewById(R.id.item_detail);
    assertThat(detailView.getText().toString(), is("Item 1"));
  }
}