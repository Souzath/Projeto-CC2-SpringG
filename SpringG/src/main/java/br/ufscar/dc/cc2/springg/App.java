package br.ufscar.dc.cc2.springg;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.ufscar.dc.cc2.springg.SpringGParser.ProgramaContext;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        System.out.println("Estou rodando");
        SpringGLexer lexer = new SpringGLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SpringGParser parser = new SpringGParser(tokens);
        ProgramaContext arvore = parser.programa();
        SpringGSemantico as = new SpringGSemantico();
        as.visitPrograma(arvore);
        SpringGSemanticoUtils.errosSemanticos.forEach((s) -> System.out.println(s));
        
        if(SpringGSemanticoUtils.errosSemanticos.isEmpty())
        {
        	SpringGGerador gerador = new SpringGGerador(new File(args[1]));
        	gerador.visitPrograma(arvore);
        }
	}
}
