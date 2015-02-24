package com.openweather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Ignacio Rojas González and Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class Utils {

    public static AlertDialog alertDialog(Context ctx, String msg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(ctx.getString(R.string.error_title_default))
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton(ctx.getString(R.string.ok), onClickListener);
        return builder.create();
    }
}
