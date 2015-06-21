package ajitsingh.com.instrumentationtestdemo;

import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

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
}