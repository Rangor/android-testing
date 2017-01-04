package com.test.demo.myapp.dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

public class DialogGenerator {

    public void alertDialogWithText(Context context, final DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOk(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void datePickerDialog(FragmentManager fragmentManager, final DialogCallback callback) {
        DialogFragment newFragment = new DatePickerFragment(callback);
        newFragment.show(fragmentManager, "datePicker");
    }

    public void timePickerDialog(FragmentManager fragmentManager, final DialogCallback callback) {
        DialogFragment newFragment = new TimePickerFragment(callback);
        newFragment.show(fragmentManager, "timePicker");
    }

    public void selectionDialog(FragmentManager fragmentManager, String[] selection, final DialogCallback callback){
        SelectionDialogFragment newFragment = new SelectionDialogFragment(selection, callback);
        newFragment.show(fragmentManager, "selectionPicker");
    }

    public interface DialogCallback {
        public void onOk(String result);

        public void onCancel();
    }
}
