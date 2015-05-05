package com.futuretraxex.qriozity;

/**
 * Created by lud on 4/23/2015.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_username, null);
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final Context self = getActivity();
        if(settings.getString(getString(R.string.key_username),"quser").equals("quser"))   {
            new AlertDialog.Builder(getActivity()).setTitle("Username")
                    .setView(dialogView)
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TextView username = (TextView)dialogView.findViewById(R.id.username_dlg);
                            if(username == null)    {
                                Toast.makeText(self,"Oops.",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                settings.edit().putString(getString(R.string.key_username),username.getText().toString() ).apply();
                                Toast.makeText(self,"Registered : " + username.getText().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //Inflate menu : ) 34
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageButton play = (ImageButton)rootView.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(getActivity(),PlayActivity.class);
                startActivity(playIntent);

            }
        });

        final ImageButton stats = (ImageButton)rootView.findViewById(R.id.statst);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statIntent = new Intent(getActivity(), StatsActivity.class);
                startActivity(statIntent);
            }
        });

        final ImageButton exit = (ImageButton)rootView.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });

        return rootView;
    }

}
