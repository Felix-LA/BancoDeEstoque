package br.com.banco.estoque;

import br.com.banco.estoque.entity.Produto;

import java.util.List;
import java.util.Scanner;

public class Menu {

  AdicionarProduto AdicionarProduto = new AdicionarProduto();

  RemoverProduto RemoverProduto = new RemoverProduto();

  VisualizarProduto VisualizarProdutos = new VisualizarProduto();

  EditarProduto EditarProduto = new EditarProduto();

  Sair sair = new Sair();

  Scanner sc = new Scanner(System.in);

  public void run(List<Produto> produtos) {

    String acao = null;
    do {
      acao = mostrarMenu(produtos);
    }
    while (!"4".equals(acao));
  }

  public String mostrarMenu(List<Produto> produtos) {


    System.out.println("Seja Bem Vindo!!!");
    System.out.println("Selecione o que deseja fazer");
    System.out.println("0 - Adicionar Produto");
    System.out.println("1 - Remover Produto");
    System.out.println("2 - Visualizar Produto");
    System.out.println("3 - Editar Produto");
    System.out.println("4 - Sair");

    String opcaoSelecionada = sc.nextLine();

    switch (opcaoSelecionada) {
      case "0":
        AdicionarProduto.adicionarProduto();
        break;
      case "1":
        VisualizarProdutos.VisualizarProdutos();
        RemoverProduto.removerProduto();
        break;
      case "2":
        VisualizarProdutos.VisualizarProdutos();
        break;
      case "3":
        VisualizarProdutos.VisualizarProdutos();
        EditarProduto.editarProduto();
        break;
      case "4":
        sair.sair();
        break;
    }

    return opcaoSelecionada;
  }

}
