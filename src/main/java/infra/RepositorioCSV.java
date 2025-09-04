/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package infra;

import java.nio.file.*;
import java.util.*;

/**
 *
 * @author marco
 */
public class RepositorioCSV {
    public static List<String> LeerLineas(String ruta) throws Exception {
        return Files.readAllLines(Path.of(ruta));
    }
    public static void escribirLineas(String ruta, List<String> lineas) throws 
            Exception {
        Path p = Path.of(ruta);
        Files.createDirectories(p.getParent());
        Files.write(p, lineas);
    }
    
}
