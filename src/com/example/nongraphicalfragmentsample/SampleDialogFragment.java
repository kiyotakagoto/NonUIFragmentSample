package com.example.nongraphicalfragmentsample;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SampleDialogFragment extends DialogFragment {
    private Bitmap mImageData;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.dialog_fragment_layout, container, false);

        ImageView imageView = (ImageView) dialogView.findViewById( R.id.image );
        imageView.setImageBitmap( mImageData );
        return dialogView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("sample dialog fragment");
        return dialog;
    }

    @Override
    public void onDestroyView() {
        // 画面回転時に消えるのを防ぐ hack
        // http://yuki312.blogspot.jp/2013/01/androiddialogfragmentsetretaininstancet.html#Message-complete
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    public void show ( FragmentManager manager, String tag, Bitmap imageData ) {
        mImageData = imageData;
        show( manager, tag );
    }
}
