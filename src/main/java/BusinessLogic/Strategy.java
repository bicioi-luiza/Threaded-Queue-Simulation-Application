package BusinessLogic;
import Model.*;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t); //interfata in since este oarecum inutila deaorece oricum voi implementa doar strategia pt timp , insa am uramrit scheletul si abia apoi am realizat ca nu era nevoie si de cealalta strategie
}
