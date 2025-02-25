public class LSENode {
    private Aluno info;
    private LSENode prox;

    public LSENode(Aluno info, LSENode prox){
        this.info = info;
        this.prox = prox;
    }

    public Aluno getInfo(){
        return info;
    }
    public LSENode getProx(){
        return prox;
    }

    public void setInfo(Aluno info){
        this.info = info;
    }
    public void setProx(LSENode prox){
        this.prox = prox;
    }
}
