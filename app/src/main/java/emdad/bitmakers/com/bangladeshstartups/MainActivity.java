package emdad.bitmakers.com.bangladesh_startups;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerListener;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        listView = (ListView)findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, //R.drawable.ic_drawer,
         R.string.drawer_open, R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerListener.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }

    public void selectItem(int position) {
        listView.setItemChecked(position, true);

    }

    public  void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




}

class MyAdapter extends BaseAdapter {


    private Context context;
    String[] newsSites;
    int[] images = {R.drawable.home, R.drawable.tech, R.drawable.business, R.drawable.education, R.drawable.economic,
                R.drawable.politics, R.drawable.opinion, R.drawable.globe, R.drawable.nation, R.drawable.share};

    public MyAdapter(Context context){
        this.context = context;
        newsSites=context.getResources().getStringArray(R.array.news);
    }
    @Override
    public int getCount() {
        return newsSites.length;
    }

    @Override
    public Object getItem(int position) {
        return newsSites[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;
        if(convertView==null)
        {
            LayoutInflater inflater =(LayoutInflater) context
                                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row,parent, false);
        }
        else
        {
            row=convertView;
        }
        TextView titleTextView=(TextView)row.findViewById(R.id.textView1);
        ImageView titleImageView= (ImageView)row.findViewById(R.id.imageView1);
        titleTextView.setText(newsSites[position]);
        titleImageView.setImageResource(images[position]);

        return row;
    }
}