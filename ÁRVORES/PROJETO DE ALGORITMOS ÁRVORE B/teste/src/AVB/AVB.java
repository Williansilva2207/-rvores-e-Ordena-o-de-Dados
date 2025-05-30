package AVB;
import Queue.*;
public class AVB {
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
    public void inserirValor(int valor) {
        if (isEmpty()) {
            raiz = new AVBNode(5);
            raiz.setFolha(true);
            raiz.setNumChaves(1);
            raiz.getChaves()[0] = valor;
        } else {
            if (raiz.getNumChaves() == raiz.getOrdem() - 1) {
                AVBNode novaRaiz = new AVBNode(raiz.getOrdem());
                novaRaiz.setFolha(false);
                novaRaiz.getFilhos()[0] = raiz;
                repartirFilho(novaRaiz, 0);
                inserirNaoCheio(novaRaiz, valor);
                raiz = novaRaiz;
            } else {
                inserirNaoCheio(raiz, valor);
            }
        }
    }
    private void repartirFilho(AVBNode pai, int index) {
        AVBNode cheio = pai.getFilhos()[index];
        AVBNode novo = new AVBNode(cheio.getOrdem());
        novo.setFolha(cheio.isFolha());

        int meio = (cheio.getOrdem() - 1) / 2;


        int numChavesNovo = cheio.getOrdem() - 1 - meio - 1;

        novo.setNumChaves(numChavesNovo);


        for (int j = 0; j < numChavesNovo; j++) {
            novo.getChaves()[j] = cheio.getChaves()[j + meio + 1];
        }


        if (!cheio.isFolha()) {
            for (int j = 0; j <= numChavesNovo; j++) {
                novo.getFilhos()[j] = cheio.getFilhos()[j + meio + 1];
            }
        }


        cheio.setNumChaves(meio);


        for (int j = pai.getNumChaves(); j >= index + 1; j--) {
            pai.getFilhos()[j + 1] = pai.getFilhos()[j];
        }
        pai.getFilhos()[index + 1] = novo;


        for (int j = pai.getNumChaves() - 1; j >= index; j--) {
            pai.getChaves()[j + 1] = pai.getChaves()[j];
        }


        pai.getChaves()[index] = cheio.getChaves()[meio];
        pai.setNumChaves(pai.getNumChaves() + 1);
    }

    private void inserirNaoCheio(AVBNode node, int valor) {
        int i = node.getNumChaves() - 1;

        if (node.isFolha()) {
            while (i >= 0 && valor < node.getChaves()[i]) {
                node.getChaves()[i + 1] = node.getChaves()[i];
                i--;
            }
            node.getChaves()[i + 1] = valor;
            node.setNumChaves(node.getNumChaves() + 1);
        } else {
            while (i >= 0 && valor < node.getChaves()[i]) {
                i--;
            }
            i++;

            if (node.getFilhos()[i].getNumChaves() == node.getOrdem() - 1) {
                repartirFilho(node, i);

                if (valor > node.getChaves()[i]) {
                    i++;
                }
            }

            inserirNaoCheio(node.getFilhos()[i], valor);
        }
    }
    public void imprimirOrdenado() {
        if(isEmpty()){
            System.out.println("Vazio");
        }else{
            imprimirEmOrdem(raiz);
        }
    }
    private void imprimirEmOrdem(AVBNode r) {
        if (r == null){
            return;
        }

        int numChaves = r.getNumChaves();
        AVBNode[] filhos = r.getFilhos();
        int[] chaves = r.getChaves();

        for (int i = 0; i < numChaves; i++) {
            imprimirEmOrdem(filhos[i]);
            System.out.print(chaves[i] + " ");
        }
        imprimirEmOrdem(filhos[numChaves]);
    }
    public void imprimirPorNivel() {
        if(isEmpty()){
            System.out.println("Vazio");
        }else{
            passeioPorNivel(raiz);
        }
    }
    private void passeioPorNivel(AVBNode r) {
        Queue<AVBNode> fila = new Queue();
        Queue<Integer> nivel = new Queue();
        if (this.isEmpty() == false) {
            fila.enQueue(this.raiz);
            nivel.enQueue(0);
            Integer atual = -1;
            while (fila.isEmpty() == false) {
                AVBNode aux = fila.deQueue();
                Integer nivelAux = nivel.deQueue();
                if (nivelAux != atual) {
                    atual = nivelAux;
                    System.out.println("\nNivel: " + atual);
                }
                for (int i = 0; i < aux.getNumChaves(); i++) {
                    System.out.print(" | " + aux.getChaves()[i] + " | ");
                }
                for (int i = 0; i <= aux.getNumChaves(); i++) {
                    if (aux.getFilhos()[i] != null) {
                        fila.enQueue(aux.getFilhos()[i]);
                        nivel.enQueue(nivelAux + 1);
                    }
                }
                System.out.print("\t\t");
            }
            System.out.println("\n");
        } else {
            System.out.println("Árvore vazia");
        }
    }

    /*public void removerValor(int valor){
        if(isEmpty()){
            System.out.println("A árvore está vazia!");
        }else{
            this.raiz = removeValorArvore(raiz, valor);
            if (this.raiz.getNumChaves() == 0 && !this.raiz.isFolha()) {
                this.raiz = this.raiz.getFilhos()[0];
            }

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
                        if(i != r.getNumChaves()){
                            if(r.getFilhos()[i].getNumChaves() + r.getFilhos()[i+1].getNumChaves() < r.getOrdem() - 1){
                                r = concatenaFilhos(r, i);
                            }
                        }else{
                            if(r.getFilhos()[i].getNumChaves() + r.getFilhos()[i-1].getNumChaves() < r.getOrdem() - 1){
                                r = concatenaFilhos(r, i);
                            }
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
                if(r.getFilhos()[r.getNumChaves()].getNumChaves() == 1){
                    r = concatenaFilhos(r, r.getNumChaves());
                    return r;
                }
            }else{
                return r;
            }

        }
        return r;
    }
    private AVBNode concatenaFilhos(AVBNode pai, int indice) {
        AVBNode filhoEsq = pai.getFilhos()[indice];
        AVBNode filhoDir = pai.getFilhos()[indice + 1];

        // Novo nó que vai unir os dois filhos e a chave do pai que os separa
        AVBNode novo = new AVBNode(pai.getOrdem());
        novo.setFolha(filhoEsq.isFolha());

        // Copia chaves e filhos do filho esquerdo
        for (int i = 0; i < filhoEsq.getNumChaves(); i++) {
            novo.getChaves()[i] = filhoEsq.getChaves()[i];
        }
        for (int i = 0; i <= filhoEsq.getNumChaves(); i++) {
            novo.getFilhos()[i] = filhoEsq.getFilhos()[i];
        }

        // Adiciona a chave do pai
        novo.getChaves()[filhoEsq.getNumChaves()] = pai.getChaves()[indice];
        int pos = filhoEsq.getNumChaves() + 1;

        // Copia chaves e filhos do filho direito
        for (int i = 0; i < filhoDir.getNumChaves(); i++) {
            novo.getChaves()[pos + i] = filhoDir.getChaves()[i];
        }
        for (int i = 0; i <= filhoDir.getNumChaves(); i++) {
            novo.getFilhos()[pos + i] = filhoDir.getFilhos()[i];
        }

        novo.setNumChaves(filhoEsq.getNumChaves() + filhoDir.getNumChaves() + 1);

        // Ajusta os filhos do pai
        for (int i = indice + 1; i < pai.getNumChaves(); i++) {
            pai.getChaves()[i - 1] = pai.getChaves()[i];
            pai.getFilhos()[i] = pai.getFilhos()[i + 1];
        }

        pai.getFilhos()[indice] = novo;
        pai.setNumChaves(pai.getNumChaves() - 1);

        return pai;
    }

    /*private AVBNode concatenaFilhos(AVBNode r, int i) {
        AVBNode novo = new AVBNode(r.getOrdem());
        if(r.getFilhos()[i].isFolha()) {
            novo.setFolha(true);
            if (i < r.getNumChaves()) {
                novo.getChaves()[0] = r.getFilhos()[i].getChaves()[0];
                novo.getChaves()[1] = r.getChaves()[i];
                novo.setNumChaves(2);
                while (true) {
                    int j = 2;
                    for (int n = 0; n < r.getFilhos()[i + 1].getNumChaves(); n++) {
                        novo.getChaves()[j] = r.getFilhos()[i + 1].getChaves()[n];
                        j++;
                        novo.setNumChaves(novo.getNumChaves()+1);
                    }
                    break;
                }
                r.getFilhos()[i] = novo;
                for (int j = i; j < r.getNumChaves() - 1; j++) {
                    r.getChaves()[j] = r.getChaves()[j + 1];
                }
                r.setNumChaves(r.getNumChaves() - 1);
                return r;

            } else {
                for (int n = 0; n < r.getFilhos()[i - 1].getNumChaves(); n++) {
                    novo.getChaves()[n] = r.getFilhos()[i - 1].getChaves()[n];
                    novo.getFilhos()[n] = r.getFilhos()[i - 1].getFilhos()[n];
                    novo.setNumChaves(novo.getNumChaves()+1);
                }
                novo.getFilhos()[novo.getNumChaves()] = r.getFilhos()[i - 1].getFilhos()[r.getNumChaves()];
                novo.getChaves()[novo.getNumChaves()] = r.getChaves()[i-1];
                novo.setNumChaves(novo.getNumChaves()+1);
                int e = 0;
                for(int j = novo.getNumChaves(); j < j+r.getFilhos()[i].getNumChaves(); j++){
                    novo.getChaves()[j] = r.getFilhos()[i].getChaves()[e];
                    novo.getFilhos()[j] = r.getFilhos()[i].getFilhos()[e];
                    novo.setNumChaves(novo.getNumChaves()+1);
                    e++;
                }
                novo.getFilhos()[novo.getNumChaves()] = r.getFilhos()[i].getFilhos()[r.getNumChaves()];
                r = novo;
                return r;

            }

        }else{
            novo.setFolha(false);
            if (i < r.getNumChaves()) {
                novo.getChaves()[0] = r.getFilhos()[i].getChaves()[0];
                novo.getChaves()[1] = r.getChaves()[i];
                novo.setNumChaves(2);
                while (true) {
                    int j = 2;
                    for (int n = 0; n < r.getFilhos()[i + 1].getNumChaves(); n++) {
                        novo.getChaves()[j] = r.getFilhos()[i + 1].getChaves()[n];
                        j++;
                        novo.setNumChaves(novo.getNumChaves()+1);
                    }
                    break;
                }
                r.getFilhos()[i] = novo;
                for (int j = i; j < r.getNumChaves() - 1; j++) {
                    r.getChaves()[j] = r.getChaves()[j + 1];
                }
                r.setNumChaves(r.getNumChaves() - 1);
                return r;

            } else {

                for (int n = 0; n < r.getFilhos()[i - 1].getNumChaves(); n++) {
                    novo.getChaves()[n] = r.getFilhos()[i - 1].getChaves()[n];
                    novo.getFilhos()[n] = r.getFilhos()[i - 1].getFilhos()[n];
                    novo.setNumChaves(novo.getNumChaves()+1);
                }
                novo.getFilhos()[novo.getNumChaves()] = r.getFilhos()[i-1].getFilhos()[r.getFilhos()[i-1].getNumChaves()];
                novo.getChaves()[novo.getNumChaves()] = r.getChaves()[i-1];
                novo.setNumChaves(novo.getNumChaves()+1);
                int e = 0;
                int j = novo.getNumChaves();
                int limite = j + r.getFilhos()[i].getNumChaves();
                while(j < limite){
                    novo.getChaves()[j] = r.getFilhos()[i].getChaves()[e];
                    novo.getFilhos()[j] = r.getFilhos()[i].getFilhos()[e];
                    novo.setNumChaves(novo.getNumChaves()+1);
                    e++;
                    j++;
                }

                novo.getFilhos()[novo.getNumChaves()] = r.getFilhos()[i].getFilhos()[r.getNumChaves()];
                r = novo;
                return r;


            }

        }
    }*/
    private void ordenarChave(AVBNode r){
        int n = r.getNumChaves();
        for (int i = 1; i < n; i++) {
            int chave = r.getChaves()[i];
            int j = i - 1;

            while (j >= 0 && r.getChaves()[j] > chave) {
                r.getChaves()[j + 1] = r.getChaves()[j];
                j = j - 1;
            }
            r.getChaves()[j + 1] = chave;
        }

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
