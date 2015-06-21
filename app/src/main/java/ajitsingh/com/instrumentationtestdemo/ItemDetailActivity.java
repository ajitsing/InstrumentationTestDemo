package ajitsingh.com.instrumentationtestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail);

    TextView detailView = (TextView) findViewById(R.id.item_detail);
    detailView.setText(getIntent().getStringExtra("detail"));
  }
}
