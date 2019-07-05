package com.tech.foodsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.tech.foodsapp.api.FoodApi;
import com.tech.foodsapp.api.FoodClient;

public class Utils {

    public static FoodApi getApi() {
        return FoodClient.getFoodClient().create(FoodApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }


    public static void displayToast(Context ctx,String text){
        Toast toast= Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
