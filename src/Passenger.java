public class Passenger extends Person{
    private long passportNumber;
    private BoardingPass boardingPass;
    private Time waitTime;

    public Passenger(String name, long passportNumber, BoardingPass boardingPass, Time waitTime) {
        super(name);
        this.passportNumber = passportNumber;
        this.boardingPass = boardingPass;
        this.waitTime = waitTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public Time getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Time waitTime) {
        this.waitTime = waitTime;
    }
}
