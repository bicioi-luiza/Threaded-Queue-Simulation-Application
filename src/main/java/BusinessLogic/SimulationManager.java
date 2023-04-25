package BusinessLogic;

import Model.Task;
import Model.*;
import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.round;



public class SimulationManager implements Runnable {
    private int timeLimit;  // = 100;
    private int maxProcessingTime;  // = 10;
    private int minProcessingTime; // = 2;
    private int numberOfServers; // = 3;
    private int numberOfClients; // = 100;
    private int maxArrivalTime;
    private int minArrivalTime;
    //private boolean running;
    private SelectionPolicy selectionPolicy; //= SelectionPolicy.SHORTEST_TIME;


    private Scheduler scheduler;

    private List<Task> generatedTasks;
    private InterfataCozi frame;

    public SimulationManager( InterfataCozi frame,int timeLimit, int maxProcessingTime,int minProcessingTime,int numberOfServers,int numberOfClients,  int minArrivalTime, int maxArrivalTime,
                              SelectionPolicy policy) {

        this.frame=frame;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.selectionPolicy=policy;
        //System.out.println(selectionPolicy);
        generatedTasks=new CopyOnWriteArrayList<>();
        scheduler = new Scheduler(numberOfServers,numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
       // System.out.println(scheduler.getStrategy().getClass());
        generateNRandomTasks();
    }

    public void generateNRandomTasks(){
    for(int i=0;i<numberOfClients;i++){
        // Instance of random class
        Random rand = new Random();
        Task clientNou= new Task(0,0,i); //initializam un task caruia ii dam valori 0 , fiindca oricum vom pune valori random dupa aceea
        clientNou.setServiceTime(rand.nextInt(maxProcessingTime + 1-minProcessingTime)); //se alege un nr random intre cele doua
        clientNou.setServiceTime(clientNou.getServiceTime()+minProcessingTime);
        clientNou.setArrivalTime(rand.nextInt(maxArrivalTime + 1-minArrivalTime)); //
        clientNou.setArrivalTime(clientNou.getArrivalTime()+minArrivalTime);
        generatedTasks.add(clientNou); // adaugam noul client in lista

    }
        Collections.sort(generatedTasks, Comparator.comparing(Task::getArrivalTime));
    }

    public void run() {
        DecimalFormat df = new DecimalFormat("#.#####");//il utilizam astfel incat daca double ul parsat este de forma x.00 noi sa afisam doar x
        int currentTime = 0;
        String txt = "";
        float avgWaitingTime = 0; //initializam timpul avg de asteptare cu 0
        float avgServiceTime = 0; //initializam tmpul mediu se servire
        int maxClienti = 0;
        int peekHour = 0;
        int gol = 0; //presupunem ca inca avem clienti , deci cozile / waiting client nu sunt goale
        for (Task t : generatedTasks) {
            avgServiceTime += t.getServiceTime();
        }

        FileWriter file;
        try {
            file = new FileWriter("log.txt");


            while (currentTime <= timeLimit && gol == 0) {
                int maxTimeSize = 0;
                while (currentTime <= timeLimit) {
                    for (Task client : generatedTasks) { //cautam daca exista un client cu arriva time ==curent time
                        if (client.getArrivalTime() == currentTime) {
                            scheduler.dispatchTask(client);
                            //System.out.println(0);
                            generatedTasks.remove(client);
                            //System.out.println(1);
                        }
                    }

                    System.out.println("T: " + currentTime);
                    txt += "T: " + currentTime + "\n";
                    System.out.println("Waitingclients : " + generatedTasks.toString());
                    if (generatedTasks.isEmpty())
                        txt += "Waitingclients : closed " + "\n";
                    else txt += "Waitingclients : " + generatedTasks.toString() + "\n";
                    System.out.println(scheduler.toString());
                    txt += scheduler.toString() + "\n";
                    frame.setTextArea(txt);
                    int maxPerServer = 0;
                    for (Server s : scheduler.getServers()) {
                        avgWaitingTime += (float) (s.getWaitingPeriod().get());
                        maxPerServer += s.getTasks().size();

                    }
                    if (maxClienti < maxPerServer) {
                        peekHour = currentTime;
                        maxClienti = maxPerServer;
                    }

                    if (generatedTasks.size() == 0) {
                        gol = 1;
                        for (Server serv : scheduler.getServers()) {
                            if (serv.getTasks().size() != 0) {
                                gol = 0;
                            }
                        }
                    }
                    if (gol == 1) break;

                    currentTime++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //currentTime++;

                }


            }
            avgWaitingTime = avgWaitingTime / (numberOfServers * timeLimit);
            //avgWaitingTime= (float) (round(avgWaitingTime * 100) / 100.0);
            System.out.println("Average waiting time:" + round(avgWaitingTime * 100) / 100.0);
            txt += "Average waiting time:" + round(avgWaitingTime * 100) / 100.0 + "\n";
            txt += "Average service time:" + (avgServiceTime / (numberOfClients)) + "\n";
            txt += "Peek hour: " + peekHour + " existand " + maxClienti + " clienti";
            file.write(txt+"\n");
            //file.write("Average waiting time:" + round(avgWaitingTime * 100) / 100.0 + "\n"+"Average service time:" + (avgServiceTime / (numberOfClients)) + "\n"+"Peek hour: " + peekHour + " existand " + maxClienti + " clienti");
            file.close();
            frame.setTextArea(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //SimulationManager gen=new SimulationManager(60,4,2,2,4,2,30,SelectionPolicy.SHORTEST_TIME);
        //Thread t= new Thread(gen);
        //t.start();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfataCozi window = new InterfataCozi();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
