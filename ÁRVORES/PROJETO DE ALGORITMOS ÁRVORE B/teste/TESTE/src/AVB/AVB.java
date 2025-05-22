package AVB;
public class AVB{
    private AVBNode raiz;
    public boolean isEmpty(){
       return raiz == null;
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

}
