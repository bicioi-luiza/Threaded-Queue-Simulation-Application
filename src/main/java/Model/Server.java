package Model;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(){
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }



    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask){
        try{
            tasks.add(newTask); //adaugam taskul la coada
            waitingPeriod.addAndGet(newTask.getServiceTime());
        } catch(IllegalStateException e) {
            System.err.println("Nu se mai pot adauga task uri in coada  ");
        }

    }
    public void run() {
        while (true) {
           Task taskulNou = tasks.peek(); //extragem un task din coada
            if (taskulNou != null) {
                try {

                    sleep(1000);

                } catch (InterruptedException e) {
                    System.out.println("Problema cu sleeping");
                }
                int serviceTimeDecrement=taskulNou.getServiceTime();
                serviceTimeDecrement=serviceTimeDecrement-1;
                taskulNou.setServiceTime(serviceTimeDecrement); //decrementam service timeul prin utilizarea getterelor si seterlor din clasa task
                if (taskulNou.getServiceTime() == 0) //in cazul in care se ajunge la 0 , acesta va fi scos din coada
                    tasks.poll();
                waitingPeriod.decrementAndGet(); //folosim o metoda din clasa AtomicInteger pentru a decrementa waitingPeriod
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {

        if(tasks.isEmpty())
            return "closed";
        else return " "+tasks;
    }
}
