package com.example.nongraphicalfragmentsample;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.AsyncTaskLoader;

public class SampleAsyncTaskLoader extends AsyncTaskLoader<Bitmap> {
    private static final String mImageUrl = "http://cdn1.www.st-hatena.com/users/ki/kiyotakagoto/profile.gif";

    public SampleAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public Bitmap loadInBackground() {
        return getImage( mImageUrl );
    }

    private Bitmap getImage ( String uriString ) {

        HttpUriRequest request = new HttpGet( uriString );
        DefaultHttpClient dhc = new DefaultHttpClient();
        try {
            HttpResponse response = dhc.execute( request );
            if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
                HttpEntity entity = response.getEntity();
                byte[] imageByteArray = EntityUtils.toByteArray( entity );
                return BitmapFactory.decodeByteArray( imageByteArray, 0, imageByteArray.length );
            }
            else {
                return null;
            }
        }
        catch ( IOException e ) {
            return null;
        }
    }

    @Override
    public void deliverResult ( Bitmap data ) {
        if ( isStarted() ) {
            super.deliverResult( data );
        }
    }

    @Override
    protected void onStartLoading () {
        forceLoad();
    }

    @Override
    protected void onStopLoading () {
        cancelLoad();
        super.onStopLoading();
    }

    @Override
    protected void onReset () {
        onStopLoading();
        super.onReset();
    }
}
