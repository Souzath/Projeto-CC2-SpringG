package br.ufscar.dc.cc2.springg;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class SpringGSemanticoUtils {

public static List<String> errosSemanticos = new ArrayList<>();
    
    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        int coluna = t.getCharPositionInLine();
        errosSemanticos.add(String.format("Erro %d:%d - %s", linha, coluna, mensagem));
    }
    
    public static boolean eTipoValido(String tipo, List<String> listaEntidades)
    {
    	if(tipo.equals("int") || tipo.equals("double") || tipo.equals("String") || tipo.equals("Date"))
    	{
    		return true;
    	}
    	
    	if(listaEntidades.contains(tipo))
    	{
    		return true;
    	}
    	
    	return false;
    }
    
}
