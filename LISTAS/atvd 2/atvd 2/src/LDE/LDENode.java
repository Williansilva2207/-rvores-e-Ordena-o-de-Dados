package LDE;

import Dados.Tarefas;

public class LDENode {
    private LDENode antes;
    private Tarefas info;
    private LDENode prox;

    public LDENode(Tarefas tarefas){
        this.info = tarefas;
    }

    public LDE.LDENode getAntes() {
        return antes;
    }

    public void setAntes(LDE.LDENode antes) {
        this.antes = antes;
    }

    public Tarefas getInfo() {
        return info;
    }

    public void setInfo(Tarefas info) {
        this.info = info;
    }

    public LDE.LDENode getProx() {
        return prox;
    }

    public void setProx(LDE.LDENode prox) {
        this.prox = prox;
    }
}
