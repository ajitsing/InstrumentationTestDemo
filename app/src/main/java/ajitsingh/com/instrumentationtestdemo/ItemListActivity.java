package ajitsingh.com.instrumentationtestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ItemListActivity extends Activity implements AdapterView.OnItemClickListener {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_list);

    ListView listView = (ListView) findViewById(R.id.items_list);

    listView.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    Intent intent = new Intent(this, ItemDetailActivity.class);
    intent.putExtra("detail", getResources().getStringArray(R.array.items)[i]);

    startActivity(intent);
  }
}
