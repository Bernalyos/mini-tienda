package com.codeup.mini.tienda;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
/**
 *
 * @author Coder
 */
public class Inventory {
    
    //modelo de datos
    private static final ArrayList<String> name = new ArrayList<>();
    private static double[] price = new double[0];
    private static final HashMap<String, Integer> stock = new HashMap<>();

    // Total de compras en la sesión
    private static double totalShopping = 0;
    
       
    // menú principal con JOptionPane
    public static void main(String[] args){
        while(true){
            String opcion = JOptionPane.showInputDialog("""
                    ===Menú Inventario==
                    1. Agregar producto
                    2. Listar inventario
                    3. Comprar producto
                    4. Mostrar estadisticas
                    5. Buscar producto por nombre
                    6. Salir
                    Elige una opción:"""); 
            
            if (opcion == null) break; // cancelar = salir 
             
            switch (opcion){
                case "1" -> addProduct();
                case "2" -> listInventory();
                case "3" -> shoppingProduct();
                case "4" -> showStatistics();
                case "5" -> searchProduct();
                case "6" -> exit();
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }
     
    
}
