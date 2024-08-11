


package servidorenviararchivos;
import java.util.StringTokenizer;

/**
 *
 * @author Dell
 */
public class ValidarRuta {

    String ruta,separador,nuevoSeparador;
    
    public ValidarRuta(String laruta, String sep, String nuevosep)
    {
        ruta=laruta;
        separador=sep;
        nuevoSeparador=nuevosep;
         
    }
    
    public String obtenerRutaCorregidaWindows()
    {
        
        StringTokenizer tokens=new StringTokenizer(ruta,separador);
        //para guardar la ruta
        String rutaCorregida =new String();
        //contando los tockens en este caso las carpetas contando el nombre del archivo seleccionado 
        int noTockens=tokens.countTokens();
        int i=1;
        do{
            rutaCorregida += tokens.nextToken()+nuevoSeparador;
            i++;
            
        }while(i<noTockens);
        //agregar a la ruta corregida el ultimo tokens
        rutaCorregida += tokens.nextToken();
        //mostrar la ruta corregida en consola 
        
        System.err.println(rutaCorregida+"\n"+noTockens+"tokens");
        return rutaCorregida;
        
    }
    
}
