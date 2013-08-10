package com.example.nongraphicalfragmentsample;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

public class SampleNonGraphicalFragmentHelper extends Fragment implements LoaderCallbacks<Bitmap>{
    private static final int MSG = 1;
    private static final String TAG = "sample_dialog_fragment";
    
    public void show () {
        getLoaderManager().restartLoader(0, null, this );
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int arg0, Bundle arg1) {
        return new SampleAsyncTaskLoader( getActivity() );
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> arg0, Bitmap imageData) {
        SampleDialogFragmentHandler handler = new SampleDialogFragmentHandler(this, imageData);
        handler.sendEmptyMessage( MSG );
    }

    static class SampleDialogFragmentHandler extends Handler {
        private final WeakReference<SampleNonGraphicalFragmentHelper> mHelper;
        private Bitmap mImageData;

        SampleDialogFragmentHandler( SampleNonGraphicalFragmentHelper nonGraphicalFragment, Bitmap imageData ) {
            mHelper = new WeakReference<SampleNonGraphicalFragmentHelper>( nonGraphicalFragment );
            mImageData = imageData;
        }
        @Override
        public void handleMessage(Message msg) {
            if ( msg.what == MSG ) {
                SampleNonGraphicalFragmentHelper helper = mHelper.get();
                if ( helper != null ) {
                    helper.showDialog( mImageData );
                }
            }
        }
    }
    
    private void showDialog ( Bitmap imageData ) {
        SampleDialogFragment dialog = new SampleDialogFragment();
        dialog.show( getFragmentManager(), TAG, imageData );
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> arg0) {}

}
