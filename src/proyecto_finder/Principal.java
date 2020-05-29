/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_finder;

import java.io.IOException;
import java.nio.file.*;

/**
 * Ejemplos de busqueda de archivos segun un filtro. Basado en los ejemplos de
 * la documentación oficial de Oracle:
 * https://docs.oracle.com/javase/tutorial/essential/io/index.html
 *
 * Referencia teórica: Página 47-51 de Proydesa.
 *
 * @author Angonoa, Franco
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Ingresa la ruta: ");
        Path startingDir = Paths.get(Consola.readLine());

        System.out.println("Ingresa un patron de busqueda: ");
        String pattern = Consola.readLine();

        Finder finder = new Finder(pattern);
        System.out.println("======================");
        try {
            Files.walkFileTree(startingDir, finder);
        } catch (IOException ex) {
            System.err.printf("Error en IO: %s%n", ex);
        }
        System.out.println("======================");
        
        finder.done();

    } //Fin del metodo main()

} //Fin de la clase
