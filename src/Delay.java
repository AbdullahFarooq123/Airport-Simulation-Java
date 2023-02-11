public class Delay{
    private String delay;
    private String delayReason;
    private Time delayTime;

    public Delay(String delay, String delayReason, Time delayTime) {
        this.delay = delay;
        this.delayReason = delayReason;
        this.delayTime = delayTime;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Time getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Time delayTime) {
        this.delayTime = delayTime;
    }
}
