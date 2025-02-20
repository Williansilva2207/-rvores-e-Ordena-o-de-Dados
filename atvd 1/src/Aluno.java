public class Aluno implements Comparable<Aluno>{
    private String matricula;
    private String nome;
    private double media;
    private int faltas;

    public Aluno(String matricula, String nome, double media, int faltas){
        this.matricula = matricula;
        this.nome = nome;
        this.media = media;
        this.faltas = faltas;
    }

    public String getMatricula(){
        return matricula;
    }
    public String getNome(){
        return nome;
    }
    public double getMedia(){
        return media;
    }
    public int getFaltas(){
        return faltas;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setMedia(double media){
        this.media = media;
    }
    public void setFaltas(int faltas){
        this.faltas = faltas;
    }
    @Override
    public String toString(){
        return "Aluno: " + nome + "\nMatricula: " + matricula + "\nMedia: " + media + "\nFaltas: " + faltas + "\n";
    }
    @Override
    public int compareTo(Aluno outroAluno)
    {
        return this.matricula.compareTo(outroAluno.matricula);
    } 
}
