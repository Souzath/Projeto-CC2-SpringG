
/**
 * Define a grammar called SpringG
 */
 
 
 /*Palavras Reservadas: Produto, Nota, String, Float, Date */
 
 
 /* public class Produto(){
  * 
  * 	private String nome;
  * 	private String descricao;
  * 	private Float preco;
  * 
  * 	Produto(String nome, String, descricao, Float preco){
  * 		this.nome = nome;
  * 		this.descricao = descricao;
  * 		this.preco = preco;
  * 	}
  * 
  * 	public String getNome(){
  * 		return nome;
  * 	}
  * 
  * 	public String getDescricao(){
  * 		return descricao;
  * 	}
  * 
  * 	public Float getPreco(){
  * 		return preco;
  * 	}
  * 
  * 	public void setNome(String nome){
  * 		this.nome = nome;
  * 	}
  * 
  * 	public void setDescricao(String descricao){
  * 		this.descricao = descricao;
  * 	}
  * 
  * 	public void setPreco(Float preco){
  * 		this.preco = preco;
  * 	}
  * 
  * }
  * 
  * public class Nota(){
  * 
  * 	private date data;
  * 	private Produto produto = new Produto();
  * 
  * 	Nota(Date data, Produto produto){
  * 		
  * 		this.data = data;
  * 		this.produto = produto;
  * 
  * 	}
  * 
  * 	public date getData(){
  * 		return data;
  * 	}
  * 
  * 	public Produto getProd(){
  * 		return produto;
  * 	}
  * 
  * 	public void setData(Date data){
  * 		this.data = data;
  * 	}
  * 
  * 	public void setProd(Produto produto){
  * 		this.produto = produto;
  * 	}
  * 
  * }
  * 
  * 
  */
 
 
 
 
 
grammar SpringG;        

programa: entidade* EOF;
entidade: 'Entidade'  nome=ID '{' atributo* '}';
atributo : nome=ID ':' tipo=ID;
WS : [ \n\t\r]+ -> skip;
ID :  [a-zA-Z] [a-zA-Z0-9]*;









