package com.mattiast.myfirstandroidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_TASK = "task";
    private Task task;
    TaskStorageHelper storageHelper = TaskStorageHelper.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        Button deleteButton = (Button) findViewById(R.id.delete_button);

        // Get the task that we might have received
        Intent intent = getIntent();
        task = intent.getParcelableExtra(KEY_TASK);

        if (task != null) {
            // Add click listener only for an existing Task
            deleteButton.setOnClickListener(this);
        } else {
            // Don't show if task is null (new Task!)
            deleteButton.setVisibility(View.INVISIBLE);
        }

        // Only update the field if we have an existing task
        if (task != null) {
            EditText title = (EditText) findViewById(R.id.title);
            title.setText(task.getTitle());
            EditText description = (EditText) findViewById(R.id.description);
            description.setText(task.getDescription());
            CheckBox completed = (CheckBox) findViewById(R.id.completed);
            completed.setChecked(task.isCompleted());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
            String myStarted = simpleDateFormat.format(task.getStarted().getTime());
            TextView timeStampView = (TextView) findViewById(R.id.timeStampView);
            timeStampView.setText("Created:" + myStarted);

            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
            //String completedTimeStamp = simpleDateFormat.format(task.isCompleted());
            //TextView completedTimeStampView = (TextView) findViewById(R.id.timeStampView);
            //completedTimeStampView.setText("Completed:" + completedTimeStamp);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                saveOrCreateTask();
                break;
            case R.id.delete_button:
                deleteTask();
                break;
        }

    }

    private void deleteTask() {

        storageHelper.deleteTask(task);

        finish();
    }

    private void saveOrCreateTask() {
        if (task == null)
        {
            task = new Task();
        }
        EditText title = (EditText) findViewById(R.id.title);
        String newTitle = title.getText().toString();
        task.setTitle(newTitle);

        EditText description = (EditText) findViewById(R.id.description);
        String newDescription = description.getText().toString();
        task.setDescription(newDescription);

        CheckBox completed = (CheckBox) findViewById(R.id.completed);
        if (completed.isChecked())
        {
            //timestamp for when task were completed
            task.setCompleted(true);

            //test 1
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
            //String myStarted = simpleDateFormat.format(task.getStarted().getTime());
            //TextView timeStampView = (TextView) findViewById(R.id.completedTimeStamp);
            //timeStampView.setText("Completed:" + myStarted);

            //test 2
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
            //String timeOfCompleted = simpleDateFormat.format(task.isCompleted());
            //TextView completedTimeStampView = (TextView) findViewById(R.id.completedTimeStamp);
            //completedTimeStampView.setText("Completed:" + timeOfCompleted);
        }
        else
        {
            task.setCompleted(false);
        }

        storageHelper.saveTask(task);
        System.out.println(task);

        finish();
    }
}
