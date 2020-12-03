package dam.android.angelvilaplana.u3t4event.model;

import dam.android.angelvilaplana.u3t4event.App;
import dam.android.angelvilaplana.u3t4event.R;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

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
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        return DateFormat.getDateInstance(DateFormat.LONG).format(date.getTime());
    }

    public String getTime() {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, minutes);
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(time.getTime());
    }

    @Override
    public String toString() {
        String mPlace = App.getRes().getString(R.string.tvPlace);
        String mPriority = App.getRes().getString(R.string.tvPriority);
        String mDate = App.getRes().getString(R.string.dateName);
        String mHour = App.getRes().getString(R.string.hourName);

        return String.format("%s: %s\n%s: %s\n%s: %s\n%s: %s", mPlace, place, mPriority, priority,
                mDate, getDate(), mHour, getTime());
    }

}
