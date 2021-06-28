package br.ufscar.dc.cc2.springg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.ufscar.dc.cc2.springg.SpringGParser.AtributoContext;
import br.ufscar.dc.cc2.springg.SpringGParser.EntidadeContext;

public class SpringGGerador extends SpringGBaseVisitor<Void>{
	private File raiz;	
	private StringBuilder texto = new StringBuilder();
	private StringBuilder gs = new StringBuilder();
	private StringBuilder atr = new StringBuilder();
	File pacote = new File(raiz, "src/main/java/pacote");
	
	
	@Override
		public Void visitEntidade(EntidadeContext ctx) {
			String nomeEntidade = ctx.nome.getText();
			StringBuilder atr = new StringBuilder();
			File pacote = new File(raiz, "src/main/java/pacote");
			pacote.mkdirs();
			File f = new File(pacote, nomeEntidade + ".java");
			File arquivo = new File(pacote, nomeEntidade + "Repository.java");
			File controller = new File(pacote, nomeEntidade + "Controller.java");
			File exc = new File(pacote, nomeEntidade + "NotFoundException.java");
			File advice = new File(pacote, nomeEntidade + "NotFoundAdvice.java");
			//File controller = new File(pacote, nomeEntidade + "Controller.java");
				texto.delete(0, texto.length());
				gs.delete(0, gs.length());
				texto.append("package pacote;");
				texto.append("\n\nimport java.util.Objects;");
				texto.append("\nimport javax.persistence.Entity;");
				texto.append("\nimport javax.persistence.GeneratedValue;");
				texto.append("\nimport javax.persistence.Id;");
				texto.append("\nimport java.util.Date;");
				texto.append("\n\n@Entity");
				texto.append("\n class "+ nomeEntidade + "{");
				texto.append("\n private @Id @GeneratedValue Long id;");
				
				gerarCampos(ctx.atributo());
				
				
				
				try {
					f.createNewFile();
					PrintWriter pw = new PrintWriter(f);
					pw.println(texto);
					pw.println("\n" + nomeEntidade + "() {}\n");
					gerarConstrutor(ctx.atributo(), pw, nomeEntidade);
					pw.println("\n");
					pw.println("public Long getId() {\n return this.id;\n} ");
					pw.println("public void setId(Long id) {\n this.id = id;\n }");
					pw.println(gs);
					pw.println("\n }");
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					arquivo.createNewFile();
					PrintWriter pw2 = new PrintWriter(arquivo);
					pw2.println("package pacote;\n");
					pw2.println("import org.springframework.data.jpa.repository.JpaRepository;");
					pw2.println("\npublic interface " + nomeEntidade + "Repository extends JpaRepository<" + nomeEntidade +", Long> {");
					pw2.println("\n}");
					pw2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StringBuilder aux = new StringBuilder();
				aux.append(Character.toLowerCase(nomeEntidade.charAt(0)));
				aux.append(nomeEntidade.subSequence(1, nomeEntidade.length()));
				try {
					controller.createNewFile();
					PrintWriter pw3 = new PrintWriter(controller);
					pw3.println("package pacote;\n");
					pw3.println("import java.util.List;");
					pw3.println("import org.springframework.web.bind.annotation.DeleteMapping;");
					pw3.println("import org.springframework.web.bind.annotation.GetMapping;");
					pw3.println("import org.springframework.web.bind.annotation.PathVariable;");
					pw3.println("import org.springframework.web.bind.annotation.PostMapping;");
					pw3.println("import org.springframework.web.bind.annotation.PutMapping;");
					pw3.println("import org.springframework.web.bind.annotation.RequestBody;");
					pw3.println("import org.springframework.web.bind.annotation.RestController;");
					pw3.println("\n@RestController");
					pw3.println("public class " + nomeEntidade + "Controller {");
					pw3.println(" private final " + nomeEntidade + "Repository repository;");
					pw3.println("\n" + nomeEntidade + "Controller(" + nomeEntidade + "Repository repository) {\n");
					pw3.println("this.repository = repository;\n");
					pw3.println("}\n");
					pw3.println("\n@GetMapping (\"/" + aux + "s\")");
					pw3.println("List<" + nomeEntidade + "> all () { ");
					pw3.println("return repository.findAll();\n}");
					pw3.println("\n@PostMapping (\"/" + aux + "s\")");
					pw3.println(nomeEntidade + " new" + nomeEntidade + " (@RequestBody " + nomeEntidade + " new" + nomeEntidade + ") {");
					pw3.println("return repository.save(new" + nomeEntidade + ");\n}");
					pw3.println("\n@GetMapping (\"/" + aux + "s/{Id}\")");
					pw3.println(nomeEntidade + " one(@PathVariable Long id) {");
					pw3.println("return repository.findById(id)\r\n"
							+ "      .orElseThrow(() -> new ProdutoNotFoundException(id));\r\n"
							+ "  }");
					pw3.println("\n@PutMapping (\"/" + aux + "/{id}\")");
					pw3.println(nomeEntidade + " replace" + nomeEntidade + "(@RequestBody " + nomeEntidade + " new" + nomeEntidade + ", @PathVariable Long id) {");
					pw3.println("return repository.findById(id)");
					pw3.println(".map(" + aux + "-> {");
					gerarAtributos(ctx.atributo(), pw3, nomeEntidade);
					pw3.println(atr);
					pw3.println("return repository.save(" + aux + ");");
					pw3.println("})");
					pw3.println(".orElseGet(() -> {\r\n"
							+ "        new" + nomeEntidade + ".setId(id);");
					pw3.println("return repository.save(new" + nomeEntidade + ");\n});");
					pw3.println("}");
					pw3.println("\n@DeleteMapping (\"/" + aux + "s\")");
					pw3.println("public void delete" + nomeEntidade + "(@PathVariable Long id) {\r\n"
							+ "    repository.deleteById(id);\r\n"
							+ "  }");
					pw3.println("}");
					pw3.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					exc.createNewFile();
					PrintWriter pw4 = new PrintWriter(exc);
					pw4.println("package pacote;");
					pw4.println("\npublic class " + nomeEntidade + "NotFoundException extends RuntimeException {\n");
					pw4.println(nomeEntidade + "NotFoundException (Long id) {\r\n"
							+ "    super(\"Could not find " + aux  + "\" + id);\n }\n}");
					pw4.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					advice.createNewFile();
					PrintWriter pw5 = new PrintWriter(advice);
					
					pw5.println("package pacote;\n");
					pw5.println("import org.springframework.http.HttpStatus;");
					pw5.println("import org.springframework.web.bind.annotation.ControllerAdvice;");
					pw5.println("import org.springframework.web.bind.annotation.ExceptionHandler;");
					pw5.println("import org.springframework.web.bind.annotation.ResponseBody;");
					pw5.println("import org.springframework.web.bind.annotation.ResponseStatus;");
					pw5.println("\n@ControllerAdvice");
					pw5.println("public class " + nomeEntidade + "NotFoundAdvice {");
					pw5.println("@ResponseBody");
					pw5.println("@ExceptionHandler(" + nomeEntidade + "NotFoundException.class)");
					pw5.println("@ResponseStatus(HttpStatus.NOT_FOUND)");
					pw5.println("\nString " + nomeEntidade + "NotFoundHandler(" + nomeEntidade + "NotFoundException ex) {");
					pw5.println("return ex.getMessage();\n }\n}");
					
					pw5.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 return super.visitEntidade(ctx);
		}
	
	
	
	/*private void gerarSetterController(List<AtributoContext> atributos, PrintWriter pw, String nomeEntidade) {
		for(AtributoContext ac:atributos) {
			String nomeAtributo = ac.nome.getText();
			String tipoAtributo = ac.tipo.getText();
			
			pw.println("\n  " + tipoAtributo + "  " + nomeAtributo + ";");
			gs.append("\n\npublic " + tipoAtributo + " get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + "() {\n return this." + nomeAtributo + ";\n}");
			gs.append("\n\npublic void set" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + " (" + tipoAtributo + " nomeAtributo) {\n this." + nomeAtributo + " = nomeAtributo;\n}");

			
		}
		
	}*/
	
	private void gerarAtributos(List<AtributoContext> atributos, PrintWriter pw, String nomeEntidade) {
		atr.delete(0, atr.length());
		StringBuilder aux = new StringBuilder();
		aux.append(Character.toLowerCase(nomeEntidade.charAt(0)));
		aux.append(nomeEntidade.subSequence(1, nomeEntidade.length()));
		for(AtributoContext ac : atributos) {
			String nomeAtributo = ac.nome.getText();
			String tipoAtributo = ac.tipo.getText();
			
			pw.println(aux + ".set" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + "(new" + nomeEntidade + ".get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + "());\n");
		}
		
		/*pw.println("return repository.save(" + Character.toLowerCase(nomeEntidade.charAt(0)) + nomeEntidade.subSequence(1, nomeEntidade.length()) + ");");
		pw.println("})");
		pw.println(".orElseGet(() -> {\r\n"
				+ "        new" + nomeEntidade + "setId(id);");
		pw.println("return repository.save(new" + nomeEntidade + ");\n});");*/
	}



	private void gerarCampos(List<AtributoContext> atributos) {
		for(AtributoContext ac:atributos) {
			String nomeAtributo = ac.nome.getText();
			String tipoAtributo = ac.tipo.getText();
			
			texto.append("\n private " + tipoAtributo + "  " + nomeAtributo + ";");
			gs.append("\n\npublic " + tipoAtributo + " get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + "() {\n return this." + nomeAtributo + ";\n}");
			gs.append("\n\npublic void set" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.subSequence(1, nomeAtributo.length()) + " (" + tipoAtributo + " nomeAtributo) {\n this." + nomeAtributo + " = nomeAtributo;\n}");

			
		}
		
	}

	
	private void gerarConstrutor(List<AtributoContext> atributos, PrintWriter pw, String nomeEntidade) {
		
		StringBuilder aux = new StringBuilder();
		aux.append(nomeEntidade + "(");
		StringBuilder aux2 = new StringBuilder();
		for(AtributoContext ac:atributos) {
			String nomeAtributo = ac.nome.getText();
			String tipoAtributo = ac.tipo.getText();
			
			aux.append(tipoAtributo + " " + nomeAtributo + ", ");
			aux2.append("this." + nomeAtributo + " = " + nomeAtributo + ";\n");
		}
		pw.println(aux.subSequence(0, (aux.length() - 2)) + ") {");
		pw.println(aux2 + "}");
	}

	
	
	
	public SpringGGerador(File raiz) {
		super();
		this.raiz = raiz;
	}
}
