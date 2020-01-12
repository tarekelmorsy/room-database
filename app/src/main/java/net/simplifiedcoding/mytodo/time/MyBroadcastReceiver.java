package net.simplifiedcoding.mytodo.time;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import net.simplifiedcoding.mytodo.AddTaskActivity;
import net.simplifiedcoding.mytodo.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp=MediaPlayer.create(context, R.raw.brid   );
        mp.start();
        Toast.makeText(context, "It's time to work." + AddTaskActivity.TASK, Toast.LENGTH_LONG).show();

    }}