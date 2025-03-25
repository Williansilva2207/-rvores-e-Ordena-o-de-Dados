package Produto;

public class Produto implements Comparable<Produto>{
    String codProduto;
    String descricao;
    String fornecedor;
    double preco;
    int qtdEstoque;

    public Produto(String codProduto, String descricao, String fornecedor, double preco, int qtdEstoque) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(String codProduto, String descricao, String fornecedor) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
    }

    public Produto(double preco) {
        this.preco = preco;
    }

    public Produto (int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int compareTo(Produto produto) {
        return this.codProduto.compareTo(produto.codProduto);
    }
    public String toString(){
        return "Codigo: " + codProduto+"\n" + "Descricao: " + descricao + "\n" + "Fornecedor: " + fornecedor + "\n" + "Preco: " + preco + "\n" + "Qtd de Estoque: " + qtdEstoque + "\n";
    }
}
