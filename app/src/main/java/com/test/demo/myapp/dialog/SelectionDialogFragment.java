package com.test.demo.myapp.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.test.demo.myapp.R;

public class SelectionDialogFragment extends DialogFragment{

    public SelectionDialogFragment(){

    }

    private String[] selection;
    private DialogGenerator.DialogCallback callback;

    public SelectionDialogFragment(String[] selection, DialogGenerator.DialogCallback callback){
        this.selection = selection;
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Velg")
                .setItems(selection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onOk(selection[which]);
                    }
                });
        return builder.create();
    }

}
