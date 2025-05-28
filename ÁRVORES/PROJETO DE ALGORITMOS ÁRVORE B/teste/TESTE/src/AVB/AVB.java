package AVB;
public class AVB{
    private AVBNode raiz;
    public boolean isEmpty(){
       return raiz == null;
   }

    public int buscarValor(AVBNode r, int chave) {
        int i = 0;
        while (i < r.getNumChaves() && chave > r.getChaves()[i]){
            i++;
        }

        if (i < r.getNumChaves() && chave < r.getChaves()[i]) {
            return r.getChaves()[i];
        }

        if (r.isFolha()) {
            return 0;
        }

        return buscarValor(r.getFilhos()[i], chave);
    }
    private void repartirFilhoArvore(AVBNode node, int l) {
        int t = node.getGrauMin();
        AVBNode y = node.getFilhos()[l];
        AVBNode z = new AVBNode(t);
        z.setFolha(y.isFolha());
        z.setNumChaves(t - 1);

        for (int i = 0; i < t - 1; i++) {
            z.getChaves()[i] = y.getChaves()[i + t];
        }

        if (!y.isFolha()) {
            for (int i = 0; i <= t; i++) {
                z.getFilhos()[i] = y.getFilhos()[i + t];
            }
        }

        y.setNumChaves(t - 1);

        for (int j = node.getNumChaves(); j >= l + 1; j--) {
            node.getChaves()[j + 1] = node.getChaves()[j];
        }

        for (int j = node.getNumChaves() + 1; j >= l + 1; j--) {
            node.getFilhos()[j + 1] = node.getFilhos()[j];
        }

        node.getChaves()[l] = y.getChaves()[t - 1];
        node.getFilhos()[l + 1] = z;
        node.setNumChaves(node.getNumChaves() + 1);
    }
    public void inserirValor(int valor){
        if(isEmpty()){
            raiz = new AVBNode(5);
            raiz.setFolha(true);
            raiz.setNumChaves(1);
            raiz.getChaves()[0] = valor;
        }else{
            if(raiz.getNumChaves() == (2 * raiz.getGrauMin() - 1)){
                AVBNode novo = new AVBNode(raiz.getGrauMin());
                novo = reparteRaizArvore(novo, raiz);
                insereNaoCheioArvore(novo,valor);
                raiz = novo;
            }else{
                insereNaoCheioArvore(raiz,valor);
            }
        }
   }

    private AVBNode reparteRaizArvore(AVBNode s,AVBNode r){

       s.setFolha(false);
       s.setNumChaves(0);
       s.getFilhos()[0] = r;
       repartirFilhoArvore(s,0);
       return s;
   }
    private void insereNaoCheioArvore(AVBNode r, int valor){
        int i = r.getNumChaves()-1;
        if(r.isFolha()){
            while(i >= 0 && valor < r.getChaves()[i]){
                r.getChaves()[i+1] = r.getChaves()[i];
                i--;
            }
            r.getChaves()[i+1] = valor;
            r.setNumChaves(r.getNumChaves()+1);
        }
        else{
            while(i >= 0 && valor < r.getChaves()[i]){
                i--;
            }
            i++;
            if(r.getNumChaves() == (2 * r.getGrauMin() - 1)){

                repartirFilhoArvore(r, i);
                if(valor > r.getChaves()[i]){
                    i++;
                }

            }
            insereNaoCheioArvore(r.getFilhos()[i], valor);
        }

   }
    public void imprimir() {
        imprimirArvore(raiz, 0);
    }

    private void imprimirArvore(AVBNode node, int nivel) {
        if (node == null) return;

        System.out.print("Nível " + nivel + ": ");
        for (int i = 0; i < node.getNumChaves(); i++) {
            System.out.print(node.getChaves()[i] + " ");
        }
        System.out.println();

        if (!node.isFolha()) {
            for (int i = 0; i <= node.getNumChaves(); i++) {
                imprimirArvore(node.getFilhos()[i], nivel + 1);
            }
        }
    }

    public void removerValor(int valor){
        if(isEmpty()){
            System.out.println("A árvore está vazia!");
        }else{
            this.raiz = removeValorArvore(raiz, valor);
        }
    }

    private AVBNode removeValorArvore(AVBNode r, int valor) {
        if(r.isFolha()){
            for(int i = 0; i < r.getNumChaves(); i++){
                if(r.getChaves()[i] == valor){
                    for(int j = i; j < r.getNumChaves() - 1; j++){
                        r.getChaves()[j] = r.getChaves()[j+1];
                    }
                    r.setNumChaves(r.getNumChaves()-1);
                    return r;
                }

            }
            return r;
        }else{
            for(int i = 0; i < r.getNumChaves(); i++){
                if(r.getChaves()[i] > valor){
                    r.getFilhos()[i] = removeValorArvore(r.getFilhos()[i], valor);
                    if(r.getFilhos()[i].getNumChaves() == 1){
                        if(r.getFilhos()[i].getChaves()[i] + r.getFilhos()[i].getChaves()[i+1] < 2*r.getGrauMin() - 1){
                            r = concatenaFilhos(r, i);
                        }
                    }
                    return r;
                }else if(r.getChaves()[i] == valor){
                    for(int j = i; j < r.getNumChaves() - 1; j++){
                        r.getChaves()[j] = r.getChaves()[j+1];
                    }
                    r.setNumChaves(r.getNumChaves()-1);
                    return r;
                }
            }
            if(r.getFilhos()[r.getNumChaves()] != null){
                r.getFilhos()[r.getNumChaves()] = removeValorArvore(r.getFilhos()[r.getNumChaves()], valor);
            }else{
                return r;
            }

        }
        return r;
    }

    private AVBNode concatenaFilhos(AVBNode r, int i) {
        AVBNode novo = new AVBNode(r.getGrauMin());
        if(r.getFilhos()[i] != r.getFilhos()[r.getNumChaves() + 1]){
            if(r.getFilhos()[i].isFolha()){
                novo.setFolha(true);
            }
            novo.getChaves()[0] = r.getFilhos()[i].getChaves()[0];
            novo.getChaves()[1] = r.getChaves()[i];
            while(true){
                int j = 2;
                for(int n = 0; n < r.getFilhos()[i+1].getNumChaves(); n++){
                    novo.getChaves()[j] = r.getFilhos()[i+1].getChaves()[n];
                    j++;
                }
                break;
            }
            r.getFilhos()[i] = novo;
            for(int j = i; j < r.getNumChaves() - 1; j++){
                r.getChaves()[j] = r.getChaves()[j+1];
            }
            r.setNumChaves(r.getNumChaves()-1);
            return r;
        }else{

        }
        return r;

    }
    //
    //
    //
    //if(!folha){
    //      atual.chave[i] = filho[i].chave[filho.nChave-1]
    //      filho[i].chave[filho.nChave-1].numChave--
    //      if(atual.filho[i].nChaves < 2 && nó.filho[i].nChaves + nó.filho[i+1].nChaves < 2*Minimo-1){
    //          novo = concatenacao(pai.chave[i],filho[a], filho[a+1])
    //          atual.filho[i] = novo
    //          atual.nChaves--
    //
    //      }else if(filho[i+1] == null){
    //          novo = concatenacao(pai.chave[i],filho[a-1], filho[a]
    //      }
    //}
    // concatenacao(p.copia,n1, n2){
    //  Node node
    //  for(int i = filho2.nChave+1; i>=0; i--){
    //      if(i-2 !< 0){
    //          node.chave[i] = filho2.chave[i-2]
    //      }else{
    //          node.chave[i] = p.copia
    //      }
    //      if(i==0){
    //          node.chave[i] = filho1.chave[i];
    //          return node
    //   }
    // }
    //
    //
    //


}
