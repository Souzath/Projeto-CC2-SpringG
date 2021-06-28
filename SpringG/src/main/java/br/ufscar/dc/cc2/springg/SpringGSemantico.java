package br.ufscar.dc.cc2.springg;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.cc2.springg.SpringGParser.EntidadeContext;

public class SpringGSemantico extends SpringGBaseVisitor<Void> {

	TabelaDeSimbolos tabela;
	List<String> listaEntidades;
	
	@Override
	public Void visitPrograma(SpringGParser.ProgramaContext ctx) {
		tabela = new TabelaDeSimbolos();
		listaEntidades = new ArrayList<>();
		for(EntidadeContext ectx : ctx.entidade())
		{
			listaEntidades.add(ectx.nome.getText());
		}
		return super.visitPrograma(ctx);
	}

	@Override
	public Void visitEntidade(SpringGParser.EntidadeContext ctx) {
		
		String nomeVar = ctx.nome.getText();
		System.out.println(nomeVar);
		
		// Verificar se a entidade já foi declarada
		if (tabela.existe(nomeVar)) {
			SpringGSemanticoUtils.adicionarErroSemantico(ctx.nome, "Entidade " + nomeVar + " já existe");
		} else {
			tabela.adicionar(nomeVar);
		}

		return super.visitEntidade(ctx);
	}

	@Override
	public Void visitAtributo(SpringGParser.AtributoContext ctx) {
		if (!SpringGSemanticoUtils.eTipoValido(ctx.tipo.getText(), listaEntidades)) {
			SpringGSemanticoUtils.adicionarErroSemantico(ctx.nome, "Tipo do atributo " + ctx.nome.getText() + " é inválido");
		}

		return super.visitAtributo(ctx);
	}

}
