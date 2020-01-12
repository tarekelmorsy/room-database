package net.simplifiedcoding.mytodo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import net.simplifiedcoding.mytodo.time.Time;

public class AddTaskActivity extends AppCompatActivity {

    private EditText  editTextDesc, editTextFinishBy;
    public static EditText TASK ;
    private DatePicker dateNewTask;
    Button time ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        time = findViewById(R.id.button_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, Time.class);
                startActivity(intent);

            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  My ToDo List  ");

        actionBar.setIcon(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        TASK = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        dateNewTask = findViewById(R.id.datePicker1);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sTask = TASK.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        editTextFinishBy.setText(" "+ dateNewTask.getDayOfMonth()+ "/" + (dateNewTask.getMonth() + 1) + "/" + dateNewTask.getYear());
        final String sFinishBy = editTextFinishBy.getText().toString().trim();


        if (sTask.isEmpty()) {
            TASK.setError("Task required");
            TASK.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task toDoTask = new Task();
                toDoTask.setTask(sTask);
                toDoTask.setDesc(sDesc);
                toDoTask.setFinishBy(sFinishBy);
                toDoTask.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(toDoTask);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

}
