package br.com.banco.estoque.entity;

public class Produto {

  private String nome;
  private Long id;
  private int quantidade;
  private int codigo;
  private Long preco;
  private String descricao;


  public String getNome(){return this.nome = nome;}
  public void setNome(String nome) {this.nome = nome;}

  public Long getId() {return this.id = id;}
  public void setId(Long id) {this.id = id;}

  public int getQuantidade() {return this.quantidade = quantidade;}
  public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

  public int getCodigo() {return this.codigo = codigo;}
  public void setCodigo(int codigo) {this.codigo = codigo;}

  public Long getPreco() {return this.preco = preco;}
  public void setPreco(Long preco) {this.preco = preco;}

  public String getDescricao() {return this.descricao = descricao;}
  public void setDescricao(String descricao) {this.descricao = descricao;}
}



