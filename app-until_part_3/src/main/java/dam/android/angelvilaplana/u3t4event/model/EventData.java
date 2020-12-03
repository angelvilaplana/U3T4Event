package dam.android.angelvilaplana.u3t4event.model;

import dam.android.angelvilaplana.u3t4event.App;
import dam.android.angelvilaplana.u3t4event.R;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Activity 1.3
 * Get the event data
 */
public class EventData implements Serializable {

    private String name;

    private String place;

    private String priority;

    private int day;

    private int month;

    private int year;

    private int hour;

    private int minutes;

    public EventData(String name, String place, String priority, int day, int month, int year, int hour, int minutes) {
        this.name = name;
        this.place = place;
        this.priority = priority;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getPriority() {
        return priority;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getDate() {
        // Activity 1.2 - Replace array of months
        String nameMonth = App.getRes().getStringArray(R.array.months_array)[month];
        return day + " " + nameMonth + " " + year;
    }

    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(Time.valueOf(hour + ":" + minutes + ":00"));
    }

    @Override
    public String toString() {
        return "PLACE: " + place + "\nPRIORITY: " + priority +
               "\nDATE: " + getDate() + "\nHOUR: " + getTime();
    }

}
