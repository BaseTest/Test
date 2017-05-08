package com.example.employee.test;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.employee.test.activity.SpecialityActivity;
import io.requery.Persistable;
import io.requery.android.QueryRecyclerAdapter;
import io.requery.reactivex.ReactiveEntityStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class BaseActivity extends AppCompatActivity {

    protected final static String EXTRA_SPECIALITY_ID = "specialityId";

    protected final static String EXTRA_EMPLOYEE_ID = "employeeId";

    protected ExecutorService executor;

    protected int filteredId;

    protected abstract QueryRecyclerAdapter createAdapter();

    protected ReactiveEntityStore<Persistable> data;

    protected QueryRecyclerAdapter adapter;

    protected ExecutorService getExecutor() {
        if (executor == null)
            executor = Executors.newSingleThreadExecutor();
        return executor;
    }

    protected QueryRecyclerAdapter initAdapter() {
        if (adapter == null) {
            adapter = createAdapter();
            adapter.setExecutor(getExecutor());
        }
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getActionBarTitle());
        }
        doBefore(savedInstanceState);
        data = ((EmployeeApplication) getApplication()).getData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        filteredId = getIntent().getIntExtra(getCurrentKey(), -1);
        recyclerView.setAdapter(initAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doAfter(savedInstanceState);
    }

    protected void processAdapter() {
        adapter.queryAsync();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(SpecialityActivity.class);
                return true;
        }
        return false;
    }

    protected abstract void doAfter(Bundle savedInstanceState);

    protected abstract void doBefore(Bundle savedInstanceState);

    protected abstract String getActionBarTitle();

    protected void startActivity(@NonNull Class clazz) {
        startActivity(clazz, -1);
    }

    protected abstract String getFilteredKey();

    protected abstract String getCurrentKey();

    protected void startActivity(@NonNull Class clazz, int id) {
        Intent intent = new Intent(this, clazz);
        if (id >= 0)
            intent.putExtra(getFilteredKey(), id);
        startActivity(intent);
    }

    // TODO перевести на работу с FragmentManager
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        processAdapter();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        getExecutor().shutdown();
        if (adapter != null)
            adapter.close();
        super.onDestroy();
    }
}
