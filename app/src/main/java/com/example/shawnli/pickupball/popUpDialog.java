package com.example.shawnli.pickupball;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;

public class popUpDialog extends DialogFragment {
    private Court currentCourt;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        currentCourt = Single.getInstance().getCurrentCourt();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.added_to_game)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing and return to activity.
                    }
                })
                .setNegativeButton(R.string.take_me,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Uri gmmIntentUri = Uri.parse("google.navigation:q="+currentCourt.getAddress());

                                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                // Make the Intent explicit by setting the Google Maps package
                                mapIntent.setPackage("com.google.android.apps.maps");

                                // Attempt to start an activity that can handle the Intent
                                startActivity(mapIntent);

                            }
                        }
                );
        // Create the AlertDialog object and return it
        return builder.create();


    }
}