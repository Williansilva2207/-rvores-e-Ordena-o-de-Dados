public class LSEAluno {
    private LSENode inicio;
    private LSENode fim;
    private int qtd;

    public LSEAluno(LSENode inicio,LSENode fim, int qtd){
        this.inicio = inicio;
        this.fim = fim;
        this.qtd = qtd;
    }

    public LSENode getInicio(){
        return inicio;
    }
    public LSENode getFim(){
        return fim;
    }

    public int getQtd(){
        return qtd;
    }

    public void setInicio(LSENode inicio){
        this.inicio = inicio;
    }
    public void setFim(LSENode fim){
        this.fim = fim;
    }
    public void setQtd(int qtd){
        this.qtd = qtd;
    }

    public boolean isEmpty( ){
        return qtd == 0;
    }

    public void inicializar(){
        setInicio(null);
        setFim(null);
        setQtd(0);
    }

    public LSENode busca(Aluno aluno){
        LSENode aux = inicio;
        
        while(aux != null){
            if(aux.getInfo().compareTo(aluno) == 0){
                return aux;
            }
            
            aux = aux.getProx();
        }
        return null;
    }

    public void inserirInicio(Aluno aluno){
        
        if(busca(aluno)==null){
            LSENode novo = new LSENode(aluno, inicio);
            setInicio(novo);
            qtd++;
        }else{
            System.out.println("Aluno nao pode ser inserido, pois ja existe.");
        }
    }

    public void inserirFinal(Aluno aluno){
        if(busca(aluno)==null){
            LSENode novo = new LSENode(aluno, null);
            fim.setProx(novo);
            setFim(novo);
            qtd++;

        }else{
            System.out.println("Aluno nao pode ser inserido, pois ja existe.");
        }
    }
    public void listar(){
        LSENode aux = inicio;
        
        while(aux != null){
            System.out.println(aux.getInfo().toString());
            aux = aux.getProx();
        }
    }

    public void exibirDados(String matricula){
        Aluno aluno = new Aluno(matricula," ",0,0);
        LSENode estudante = busca(aluno);
        if(estudante != null){
            System.out.println(estudante.getInfo().toString());
        }else{
            System.out.println("O aluno não está matriculado.");
        }
    }

    public void modificar(String matricula, double media, int faltas){
        Aluno aluno = new Aluno(matricula," ",0,0);
        LSENode aux = busca(aluno);
        if(aux != null){
            aux.getInfo().setMedia(media);
            aux.getInfo().setFaltas(faltas);

        }
        else{
            System.out.println("Aluno não matriculado");
        }
    }

    public void excluirInicio(){
        LSENode novo = inicio;
        inicio = inicio.getProx();
        novo = null;
        qtd--;
    }
    public void excluirFinal(){
        LSENode aux1 = inicio.getProx();
        LSENode aux2 = inicio;
        while(true){
            if(aux1.getProx()==null){
                fim = aux2;
                aux2.setProx(null);
                break;
            }
            aux1 = aux1.getProx();
            aux2 = aux2.getProx();    
        }
        
    }       
}
        
            

