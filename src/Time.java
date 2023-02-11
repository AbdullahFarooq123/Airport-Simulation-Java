import java.util.Random;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        setSeconds(seconds);
        setMinutes(minutes);
        setHours(hours);
    }

    public boolean equals(Time time) {
        return this.hours == time.hours && this.minutes == time.minutes && this.seconds == time.seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        if (this.hours >= 24) {
            this.hours /= hours;
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        if (this.minutes >= 60) {
            setHours(hours + 1);
            this.minutes /= minutes;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        if (this.seconds >= 60) {
            setMinutes(minutes + 1);
            this.seconds /= seconds;
        }
    }

    public void tick() {
        if (++seconds == 60) {
            seconds = 0;
            if (++minutes == 60) {
                minutes = 0;
                if (++hours == 24) {
                    hours = 0;
                }
            }
        }
    }

    public int getTimeInMinutes(){
        return (int) (hours*60 + minutes + Math.floor(seconds / 60.0));
    }

    public int getTimeInHours(){
        return (int) (hours + Math.floor(minutes/24.0) + Math.floor(seconds / 24.0));
    }

    public int getDifferenceInHours(Time time){
        return Math.abs(getTimeInHours()-time.getTimeInHours());
    }

    public void addTime(Time time) {
        setSeconds(seconds + time.seconds);
        setMinutes(minutes + time.minutes);
        setHours(hours + time.hours);
    }

    public static Time getRandomTime() {
        return new Time(Static.getRandomInt(24, 0), Static.getRandomInt(60, 0), Static.getRandomInt(60, 0));
    }

    public void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
