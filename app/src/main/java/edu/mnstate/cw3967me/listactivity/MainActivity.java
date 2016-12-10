package edu.mnstate.cw3967me.listactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] prodArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prodArray =
                getResources().getStringArray(R.array.arrayProds);
        final ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.layout_list_item, prodArray);
        ListView listView =
                (ListView) findViewById(R.id.prod_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position,
                        Toast.LENGTH_LONG).show();
            }
        });


        final ListView listview = (ListView) findViewById(R.id.listview);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }


        final StableArrayAdapter stableArrayAdapter = new StableArrayAdapter(this, android.R.layout.activity_list_item, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @TargetApi(16)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                stableArrayAdapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });
    }

    //show menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(this, MySimpleArrayAdapter.class));
        return true;
    }
}


class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
