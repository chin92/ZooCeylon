package com.android.ZooCeylon.com.android.ZooCeylon.Mario;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.android.ZooCeylon.MainActivity;
import com.android.ZooCeylon.R;

/**
 * Created by Asus i5 on 1/15/2015.
 */
public class frg_friends_dialogClass extends DialogFragment {
    Toast t;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.frg_friends_dialog, null))
                // Add action buttons
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //Testing is Message Dialog works
                        // t = Toast.makeText(getActivity(),"CLicked success", Toast.LENGTH_LONG);
                        //   t.show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        frg_friends_dialogClass.this.getDialog().cancel();

                        Intent i= new Intent(getActivity(),MainActivity.class);
                        startActivity(i);
                    }
                });
        return builder.create();
    }
}
