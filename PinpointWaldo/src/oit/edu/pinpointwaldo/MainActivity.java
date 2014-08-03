package oit.edu.pinpointwaldo;

import oit.edu.pinpointwaldo.services.GPSDataService;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DrawerLayout mDrawer = null;
	private ActionBarDrawerToggle mDrawerToggle = null;
	private CharSequence mTitle = null;
	
	private ListView mDrawerList = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
		mTitle = getTitle();
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_drawermenu);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.drawable.ic_drawer, R.string.drawer_open_desc, R.string.drawer_close_desc) {

			public void onDrawerClosed (View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			
			public void onDrawerOpened (View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
		};
		
		mDrawer.setDrawerListener(mDrawerToggle);
		handleIntent(getIntent());
		
		getFragmentManager().beginTransaction().replace(R.id.frame_container, new WaldoMapFragment()).commit();
		this.startService(new Intent(this, GPSDataService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
			searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean open = mDrawer.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_search).setVisible(!open);
		menu.findItem(R.id.action_setting).setVisible(!open);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
	};
	
	@Override
	protected void onNewIntent(Intent i)
	{
		handleIntent(i);
	}
	
	private void handleIntent(Intent i) {
		if (Intent.ACTION_SEARCH.equals(i.getAction())) {
			String query = i.getStringExtra(SearchManager.QUERY);
			Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
		}
	}
}
