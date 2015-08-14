package com.rancard.materialtest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rancard.fragments.ConvertCurrencyFragment;
import com.rancard.fragments.CreateAccountFragment;
import com.rancard.fragments.CreateBatchTransactionFragment;
import com.rancard.fragments.CreateTransactionFragment;
import com.rancard.fragments.ExchangeRatesFragment;
import com.rancard.fragments.ProfileFragment;
import com.rancard.fragments.ViewAccountFragment;
import com.rancard.fragments.ViewAccountsFragment;
import com.rancard.fragments.ViewTransactionFragment;
import com.rancard.fragments.ViewTransactionsFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private NavigationDrawerFragment mExchangeRatesFragment;
    private NavigationDrawerFragment mConvertCurrencyFragment;
    private Toolbar mToolbar;
    AppCompatActivity activity;

    public static final int PROFILE = 0;
    public static final int CURRENCY = 1;
    public static final int RATES = 2;
    public static final int CREATE_ACCOUNT = 3;
    public static final int VIEW_ACCOUNT = 4;
    public static final int VIEW_ACCOUNTS = 5;
    public static final int CREATE_TRANSACTION = 6;
    public static final int CREATE_BTRANSACTION = 7;
    public static final int VIEW_TRANSACTION = 8;
    public static final int VIEW_TRANSACTIONS = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        activity = this;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager frgManager;
        FragmentTransaction ft;

        if (position == PROFILE) {
            ProfileFragment f1 = new ProfileFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            // set the toolbar title
            setTitle(R.string.profile);
        } else if (position == CURRENCY) {
            ConvertCurrencyFragment f1 = new ConvertCurrencyFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            // set the toolbar title
            setTitle(R.string.convert_currency);

        } else if (position == RATES) {
            ExchangeRatesFragment f1 = new ExchangeRatesFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.exchange_rates);
        } else if (position == CREATE_ACCOUNT) {
            CreateAccountFragment f1 = new CreateAccountFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.create_account);
        } else if (position == VIEW_ACCOUNT) {
            ViewAccountFragment f1 = new ViewAccountFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.view_account);
        } else if (position == VIEW_ACCOUNTS) {
            ViewAccountsFragment f1 = new ViewAccountsFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.view_accounts);
        } else if (position == CREATE_TRANSACTION) {
            CreateTransactionFragment f1 = new CreateTransactionFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.create_transaction);
        } else if (position == CREATE_BTRANSACTION) {
            CreateBatchTransactionFragment f1 = new CreateBatchTransactionFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.create_btransaction);
        } else if (position == VIEW_TRANSACTION) {
            ViewTransactionFragment f1 = new ViewTransactionFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.view_transaction);
        } else if (position == VIEW_TRANSACTIONS) {
            ViewTransactionsFragment f1 = new ViewTransactionsFragment();
            frgManager = getSupportFragmentManager();
            ft = frgManager.beginTransaction();
            ft.replace(R.id.container, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            setTitle(R.string.view_transactions);
        }
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}