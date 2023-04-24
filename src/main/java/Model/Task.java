package Model;

public class Task {
    private int arrivalTime;
    private int serviceTime;
    private int ID;

    public Task(int arrivalTime , int serviceTime , int ID) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.ID = ID;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "(" +
                " ID=" + ID +
                ", aT=" + arrivalTime +
                ", sT=" + serviceTime +
                ')';
    }
}
