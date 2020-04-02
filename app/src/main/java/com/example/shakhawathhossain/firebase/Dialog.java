package com.example.shakhawathhossain.firebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by Shakhawath Hossain on 31/3/2020.
 */

public class Dialog extends AppCompatDialogFragment {

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.change).setMessage(R.string.msg).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));

            }
        });

        return builder.create();
    }
}
