package proyecto_finder;

import java.io.IOException;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Clase Finder.
 *
 * Referencia teórica: 51 de Proydesa.
 *
 * @author Angonoa, Franco
 */
public class Finder extends SimpleFileVisitor<Path> {

    private final PathMatcher matcher;
    private int numMatches = 0;

    //Constructor para pasar el patron
    public Finder(String pattern) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
    }

    // Compara el patron glob con 
    // el nombre del archivo o directorio.
    public void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            numMatches++; //Cuenta la cantidad de coincidencias
            System.out.println(file);
        }
    }

    // Imprime el número total de coincidencias.
    public void done() {
        System.out.printf("Coincidencias: %d%n", numMatches);
    }

    // Invoca el metodo de coincidencia
    // de patrones find() en cada archivo.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return CONTINUE;
    }

    // Invoca el metodo de coincidencia
    // de patrones find() en cada directorio.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
    
} //Fin de la clase