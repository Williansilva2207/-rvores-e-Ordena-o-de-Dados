package Cadastro;
import ABB.*;
import Produto.*;

import java.util.Scanner;

public class Cadastro {
   private ABB<Produto> acaos;
   private Produto produto;

   public String cod;
   public String descr;
   public String forne;
   public double value;
   public int quantidade;
   Scanner sc = new Scanner(System.in);

   public void cadastrar(){
       acaos = new ABB<Produto>();
       ABBNode<Produto> produtoABBNode = new ABBNode<Produto>(produto);

       System.out.println("Coloque o código: ");
       cod = sc.nextLine();
       produto = new Produto(cod, null,null);
       produtoABBNode = acaos.busca1(produto);
       if(produto.compareTo(produtoABBNode.getData()) == 0){
           System.out.println("Produto não pode ser cadastrado, já existe um!");
       }
       else{
           System.out.println("Coloque o descricao: ");
           descr = sc.nextLine();
           System.out.println("Coloque o fornecedor: ");
           forne = sc.nextLine();
           System.out.println("Coloque o valor: ");
           value = sc.nextDouble();
           System.out.println("Coloque o quantidade: ");
           quantidade = sc.nextInt();


           acaos.insert(produto);
       }
   }
   public void listar(){
       acaos = new ABB<Produto>();
       acaos.emOrdem();
   }
}
