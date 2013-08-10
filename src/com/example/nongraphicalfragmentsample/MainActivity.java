package com.example.nongraphicalfragmentsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    private static final String HELPER_TAG = "non_graphical_fragment_helper";
    private SampleNonGraphicalFragmentHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // non-graphical fragment を activity に追加
        mHelper = (SampleNonGraphicalFragmentHelper) getSupportFragmentManager().findFragmentByTag( HELPER_TAG );
        if ( mHelper == null ) {
            mHelper = new SampleNonGraphicalFragmentHelper();
            getSupportFragmentManager().beginTransaction().add( mHelper, HELPER_TAG ).commit();
        }

        Button trigger = (Button) findViewById( R.id.trigger );
        trigger.setOnClickListener( new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mHelper.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
