package BusinessLogic;
import Model.*; //pt a importa toate clasele din pachetul respectiv

import java.util.ArrayList;
import java.util.List;


public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) { //constructor scheduler (e ca un magazin cu o lista de cozi si maximul de taskuri (clienti) pe coada
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        this.servers = new ArrayList<Server>();

        for(int i=0; i<maxNoServers; i++) {
            Server serv = new Server();
            this.servers.add(serv);
            Thread t = new Thread(serv);
            t.start();
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy(); //am schimbat doar denumirile claselor care implementeaza Strategy
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task t) {
        //call the strategy addTask method
     strategy.addTask(servers, t);
    }

    @Override
    public String toString() {
        String string="";
        int i=1;
        for (Server s : servers) {
            string+="Coada "+i+":"+s.toString()+"\n";
            i++;

        }
        return string;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
