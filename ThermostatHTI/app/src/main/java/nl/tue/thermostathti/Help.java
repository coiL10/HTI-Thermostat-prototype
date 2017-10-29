package nl.tue.thermostathti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class Help extends AppCompatActivity {

    private TextView mTextMessage;
    Intent intent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(Help.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_dashboard:
                    intent = new Intent(Help.this, week_overview.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_notifications:
                    break;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.expand_text_viewQA1);
        ExpandableTextView expTv2 = (ExpandableTextView) findViewById(R.id.expand_text_viewQA2);
        ExpandableTextView expTv3 = (ExpandableTextView) findViewById(R.id.expand_text_viewQA3);
        ExpandableTextView expTv4 = (ExpandableTextView) findViewById(R.id.expand_text_viewQA4);

        expTv1.setText(Html.fromHtml(getString(R.string.QA1)));
        expTv2.setText(Html.fromHtml(getString(R.string.QA2)));
        expTv3.setText(Html.fromHtml(getString(R.string.QA3)));
        expTv4.setText(Html.fromHtml(getString(R.string.QA4)));
    }

}
