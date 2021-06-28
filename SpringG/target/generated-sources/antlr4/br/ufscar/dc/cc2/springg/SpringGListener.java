// Generated from br\u005Cufscar\dc\cc2\springg\SpringG.g4 by ANTLR 4.9.2
package br.ufscar.dc.cc2.springg;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SpringGParser}.
 */
public interface SpringGListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SpringGParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(SpringGParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpringGParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(SpringGParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpringGParser#entidade}.
	 * @param ctx the parse tree
	 */
	void enterEntidade(SpringGParser.EntidadeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpringGParser#entidade}.
	 * @param ctx the parse tree
	 */
	void exitEntidade(SpringGParser.EntidadeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpringGParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAtributo(SpringGParser.AtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpringGParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAtributo(SpringGParser.AtributoContext ctx);
}