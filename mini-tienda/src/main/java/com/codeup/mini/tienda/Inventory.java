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
    
      
    // --- Métodos utilitarios ---
    //Flujo de cada opción
    private static void addProduct() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }
        if (name.contains(nombre)) { // verifica si el producto ya existe 
            JOptionPane.showMessageDialog(null, "El producto ya existe.");
            return;
        }

        try {
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock:"));

            name.add(nombre);
            price = expandPrecios(price, precio);
            stock.put(nombre, cantidad);

            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio o cantidad inválidos.");
        }
    }

    private static void listInventory() {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== INVENTARIO ===\n");
        for (int i = 0; i < name.size(); i++) {
            sb.append(name.get(i)).append(" - $")
              .append(price[i]).append(" - Stock: ")
              .append(stock.get(name.get(i))).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void shoppingProduct() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
        if (nombre == null || !name.contains(nombre)) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a comprar:"));
            int disponible = stock.get(nombre);

            if (cantidad <= 0 || cantidad > disponible) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente o cantidad inválida.");
                return;
            }

            int index = indexOfNombre(nombre);
            double subtotal = price[index] * cantidad;
            totalShopping += subtotal;

            stock.put(nombre, disponible - cantidad);

            JOptionPane.showMessageDialog(null, "Compra realizada. Subtotal: $" + subtotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        }
    }

    private static void showStatistics() {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos.");
            return;
        }

        double min = price[0], max = price[0];
        int idxMin = 0, idxMax = 0;

        for (int i = 1; i < price.length; i++) {
            if (price[i] < min) { min = price[i]; idxMin = i; }
            if (price[i] > max) { max = price[i]; idxMax = i; }
        }

        JOptionPane.showMessageDialog(null,
                "Producto más barato: " + name.get(idxMin) + " - $" + min +
                "\nProducto más caro: " + name.get(idxMax) + " - $" + max);
    }

    private static void searchProduct() {
        String busqueda = JOptionPane.showInputDialog("Ingrese texto a buscar:");
        if (busqueda == null || busqueda.trim().isEmpty()) return;

        StringBuilder sb = new StringBuilder("Resultados:\n");
        for (int i = 0; i < name.size(); i++) {
            if (name.get(i).toLowerCase().contains(busqueda.toLowerCase())) {
                sb.append(name.get(i)).append(" - $")
                  .append(price[i]).append(" - Stock: ")
                  .append(stock.get(name.get(i))).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void exit() {
        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.\nTotal de compras: $" + totalShopping);
        System.exit(0);
    }
    
    // --- Helpers ---
    private static double[] expandPrecios(double[] oldArray, double nuevoPrecio) {
        double[] newArray = new double[oldArray.length + 1];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        newArray[oldArray.length] = nuevoPrecio;
        return newArray;
    }

    private static int indexOfNombre(String nombre) {
        return name.indexOf(nombre);
    }
}
     
    

