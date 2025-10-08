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
    
}
