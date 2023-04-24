package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy{
    public void addTask(List<Server> servers, Task t){
        Server coadaLaCareAdaugam = servers.get(0); //initial luam prima coada
        for (Server sIndice : servers) {
            if (sIndice.getWaitingPeriod().intValue() < coadaLaCareAdaugam.getWaitingPeriod().intValue()) {
                coadaLaCareAdaugam = sIndice; //alegem coada cu timpul cel mai scurt (mic) de asteptare
            }
        }

        // add the task to the server's queue and update the waiting period
        coadaLaCareAdaugam.addTask(t);
    }

}
