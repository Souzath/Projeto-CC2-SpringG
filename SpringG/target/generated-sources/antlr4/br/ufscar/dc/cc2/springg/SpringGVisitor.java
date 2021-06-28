// Generated from br\u005Cufscar\dc\cc2\springg\SpringG.g4 by ANTLR 4.9.2
package br.ufscar.dc.cc2.springg;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SpringGParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SpringGVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SpringGParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(SpringGParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpringGParser#entidade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntidade(SpringGParser.EntidadeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpringGParser#atributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributo(SpringGParser.AtributoContext ctx);
}