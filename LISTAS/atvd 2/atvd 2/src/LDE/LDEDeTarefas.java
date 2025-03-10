package LDE;
import Dados.Tarefas;
public class LDEDeTarefas {
    private LDENode inicio;
    private LDENode fim;
    private int qtd;

    public boolean isEmpty() {
        return this.qtd == 0;
    }

    private LDENode buscar(Tarefas tarefa) {
        LDENode aux;
        aux = this.inicio;
        for(int i = 0; i < this.qtd; i++) {
            if (tarefa.compareTo(aux.getInfo()) == 0) {
                return aux;
            } else {
                aux = aux.getProx();
            }
        }
        return null;
    }

    public void adicionar(Tarefas tarefa) {
        LDENode retorno = this.buscar(tarefa);
        LDENode novo;
        if (retorno == null) {
            novo = new LDENode(tarefa);
            if (isEmpty()) {
                this.inicio = novo;
                this.fim = novo;
                this.inicio.setProx(fim);
                this.fim.setProx(inicio);
                this.fim.setAntes(inicio);
                this.inicio.setAntes(fim);

                this.qtd++;
            } else if (novo.getInfo().getNivel() > inicio.getInfo().getNivel()) {
                novo.setAntes(fim);
                this.inicio.setAntes(novo);
                novo.setProx(inicio);
                this.inicio = novo;
                this.fim.setProx(inicio);
                this.qtd++;

            } else if (novo.getInfo().getNivel() == inicio.getInfo().getNivel()) {
                LDENode aux = this.inicio.getProx();
                novo.setAntes(inicio);
                novo.setProx(aux);
                aux.setAntes(novo);
                inicio.setProx(aux);
                this.qtd++;
            } else if (novo.getInfo().getNivel() <= fim.getInfo().getNivel()) {
                novo.setAntes(fim);
                novo.setProx(inicio);
                fim.setProx(novo);
                inicio.setAntes(novo);
                this.fim = novo;
                this.qtd++;

            } else {
                LDENode aux1 = this.inicio;
                LDENode aux2 = this.inicio.getProx();
                while (true) {
                    if (novo.getInfo().getNivel() > aux2.getInfo().getNivel()) {
                        novo.setAntes(aux1);
                        novo.setProx(aux2);
                        aux2.setAntes(novo);
                        aux1.setProx(novo);
                        this.qtd++;
                        return;
                    } else {
                        aux1 = aux2;
                        aux2 = aux2.getProx();
                    }

                }
            }
        }

    }

    private void encontrar(Tarefas tarefa) {
        LDENode aux;
        aux = this.inicio;
        int qtd = 0;
        while (aux != null) {

            if (tarefa.compareTo(aux.getInfo()) == 0) {
                System.out.println("Achado!!!\nQuantidade de tarefas para ainda serem executadas: " + qtd);
            } else {
                qtd++;
                aux = aux.getProx();
            }
        }
        System.out.println("Nao encontrado!");

    }
    public void executar(){
        if(this.isEmpty()){
            System.out.println("Nao há mais tarefas!\n");
        }else{
            LDENode novo = this.inicio;
            this.inicio = inicio.getProx();
            this.fim.setProx(inicio);
            this.inicio.setAntes(fim);
            this.qtd--;
            System.out.println("Tarefa: " + novo.getInfo().getNome() + " executada com sucesso!");
            novo.setAntes(null);
            novo.setProx(null);
            novo.setInfo(null);
        }



    }
    public void deletar(Tarefas tarefa) {
        LDENode retorno = this.buscar(tarefa);
        if(retorno == null){
            System.out.println("Nao Existe!");
        }else{
            LDENode aux = retorno.getProx();
            LDENode novo = retorno.getAntes();
            novo.setProx(aux);
            aux.setAntes(novo);
            retorno.setInfo(null);
            retorno.setAntes(null);
            retorno.setProx(null);
            this.qtd--;
        }
    }
    public void exibir() {
        LDENode aux = inicio;
        for (int i = 1; i <= this.qtd; i++) {
            System.out.println(aux.getInfo() + "\n");
            aux = aux.getProx();
        }
        System.out.println("Quantidade: " + this.qtd);
    }
    public void mudarPrioridade(Tarefas tarefa, int prioridade) {
        LDENode retorno = this.buscar(tarefa);
        if(retorno == null){
            System.out.println("Nao Existe!");
        }else{
            LDENode aux = retorno.getProx();
            LDENode aux2 = retorno.getAntes();
            aux.setAntes(aux2);
            aux2.setProx(aux);
            retorno.setAntes(null);
            retorno.setProx(null);
            retorno.getInfo().setNivel(prioridade);
            this.qtd--;
            adicionar(retorno.getInfo());
        }
    }
}
