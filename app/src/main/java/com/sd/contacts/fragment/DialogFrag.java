package com.sd.contacts.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.sd.contacts.R;

/**
 * Created by pstorli on 10/3/2017.
 *
 * Use this code to create an alert dialog instead.
 *   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 *   builder.setView(R.layout.dialog_frag);
 *
 *   android:textColor="?attr/dialogHeaderBackground"
 *
 */

public class DialogFrag extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Dialog dialog = new Dialog(getActivity(), R.style.QuestionStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_frag);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Button btnClose = (Button) dialog.findViewById(R.id.btn_dialog_pos);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listener.onRegisterUserComplete();
                dialog.dismiss();
            }
        });

        return dialog;
    }

}