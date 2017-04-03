package se.hellsoft.pia6todoapplication;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public final class TaskStorageHelper {
    private static final TaskStorageHelper INSTANCE = new TaskStorageHelper();
    private static final String TAG = "TaskStorageHelper";

    private List<Task> tasks = new ArrayList<>();

    private TaskStorageHelper() {
    }

    public void initStorage(Context context) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tasks.clear();
            Task first = new Task();
            first.setId(1);
            first.setTitle("Fixa ikonerna");
            first.setDescription("Byt ut all ikoner i applikationen mot något mer lämpligt");
            first.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(first);
            Task second = new Task();
            second.setId(2);
            second.setTitle("Fixa menyerna");
            second.setDescription("Redigera menyn så den bara innehåller 'Statistics' och 'Information'. Lägg till skärmar för dessa.");
            second.setStarted(simpleDateFormat.parse("2016-11-01"));
            second.setCompleted(true);
            tasks.add(second);
            Task third = new Task();
            third.setId(3);
            third.setTitle("Koppla ihop lista med detaljvy");
            third.setDescription("Fixa så att ett klick på en task öppnar vald task i detaljvyn.");
            third.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(third);
            Task fourth = new Task();
            fourth.setId(4);
            fourth.setTitle("Spara och radera");
            fourth.setDescription("Fixa så spara och radera-knapparna utför respektive funktion mot TaskStorageHelper.");
            fourth.setStarted(simpleDateFormat.parse("2016-11-01"));
            fourth.setArchived(true);
            tasks.add(fourth);
            Task fifth = new Task();
            fifth.setId(5);
            fifth.setTitle("Redigering i lista");
            fifth.setDescription("Fixa så att ett klick på checkboxen i listan sparas ner.");
            fifth.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(fifth);
            Task sixth = new Task();
            sixth.setId(6);
            sixth.setTitle("Lägg till fält");
            sixth.setDescription("Lägg till fält för när en task skapades och blev avklarad i Task. Dessa ska visas i detaljvyn.");
            sixth.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(sixth);
            Task seventh = new Task();
            seventh.setId(7);
            seventh.setTitle("Filtrering i listan");
            seventh.setDescription("Koppla menyalternativen i menu.xml till att filtrera det som visas i listvyn (alla förutom arkiverade, endast aktiva, endast avklarade och endast arkiverade).");
            seventh.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(seventh);
            Task eighth = new Task();
            eighth.setId(8);
            eighth.setTitle("VG: Spara till databas");
            eighth.setDescription("Fixa så att TaskStorageHelper sparas till en databas (SQLite eller Firebase).");
            eighth.setStarted(simpleDateFormat.parse("2016-11-01"));
            tasks.add(eighth);
        } catch (ParseException e) {
            Log.e(TAG, "Couldn't parse date!", e);
        }
    }

    public static TaskStorageHelper getInstance() {
        return INSTANCE;
    }

    public void saveTask(Task task) {
        long nextId = 0;
        for (Task existingTask : tasks) {
            nextId = existingTask.getId() > nextId ? existingTask.getId() : nextId;
            if (task.getId() == existingTask.getId()) {
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setCompleted(task.isCompleted());
                existingTask.setArchived(task.isArchived());
                return;
            }
        }
        nextId++;
        task.setId(nextId);
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
