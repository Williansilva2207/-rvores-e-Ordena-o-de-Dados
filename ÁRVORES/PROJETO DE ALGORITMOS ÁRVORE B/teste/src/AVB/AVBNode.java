package AVB;

public class AVBNode {
    private int ordem;
    private int numChaves;
    private boolean folha;
    private int[] chaves;
    private AVBNode[] filhos;

    public AVBNode(int ordem) {
        this.ordem = ordem;
        this.folha = true;
        this.chaves = new int[ordem - 1];
        this.filhos = new AVBNode[ordem];
        this.numChaves = 0;
    }

    public int getOrdem() {
        return ordem;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int[] getChaves() {
        return chaves;
    }

    public AVBNode[] getFilhos() {
        return filhos;
    }

    public int getNumChaves() {
        return numChaves;
    }

    public void setNumChaves(int numChaves) {
        this.numChaves = numChaves;
    }
}
