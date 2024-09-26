package br.com.banco.estoque;

import br.com.banco.estoque.entity.Produto;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<Produto> produtos = new ArrayList<Produto>();

    Menu menu = new Menu();
    menu.run(produtos);
  }
}
