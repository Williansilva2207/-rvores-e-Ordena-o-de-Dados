package AVB;

public class AVBNode{
    private int grauMin;
    private int numChaves;
    private boolean folha;
    private int[] chaves;
    private AVBNode[] filhos;


    public AVBNode(int grauMin) {
        this.grauMin = grauMin;
        this.folha = true;
        this.chaves =  new int[2*grauMin - 1];
        filhos = new AVBNode[2*grauMin];
        this.numChaves = 0;
    }

    public int getGrauMin() {
        return grauMin;
    }

    public void setGrauMin(int grauMin) {
        this.grauMin = grauMin;
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

    public void setChaves(int chaves) {
        this.chaves = new int[]{chaves};
    }

    public AVBNode[] getFilhos() {
        return filhos;
    }

    public void setFilhos(AVBNode[] filhos) {
        this.filhos = filhos;
    }

    public int getNumChaves() {
        return numChaves;
    }

    public void setNumChaves(int numChaves) {
        this.numChaves = numChaves;
    }

}
