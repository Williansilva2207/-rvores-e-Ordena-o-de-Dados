package Dados;
public class Tarefas implements Comparable<Tarefas> {
    private String nome;

    private int nivel;
    
    public Tarefas(String tarefa){
        this.nome = tarefa;
    }
    public Tarefas(int nivel){

        this.nivel = nivel;
    }
    public Tarefas(String nome, int nivel){
        this.nome = nome;
        this.nivel = nivel;
    }

    @Override
    public int compareTo(Tarefas tarefa){
        return this.nome.compareTo(tarefa.nome);
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel){
        if(nivel >= 1 && nivel <= 10){
            this.nivel = nivel;
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome + "\n" + "Nivel: " + this.nivel;
    }

   
    
}
