package burger.action;

import burger.model.Order;
import burger.model.product.Product;
import burger.model.supply.Supply;

import java.util.ArrayList;
import java.util.HashMap;

public class MakeOrder implements Action {
   private static ArrayList<Order> orders = new ArrayList<>();
   private static HashMap<Supply, Integer> storage = new HashMap<>();

   public static boolean addOrder(Order order) {
      return orders.add(order);
   }

   public static int addIngredient(Supply ingredient) {
      Integer value = storage.get(ingredient);
      int nIngredient = value == null ? 0 : value;
      storage.put(ingredient, nIngredient + 1);

      return nIngredient;
   }

   @Override
   public void execute() throws Exception {
      if (orders.isEmpty())
         throw new Exception("Nenhum pedido para preparar.");

      System.out.println("\nIngredientes em estoque:");
      for (Supply ingredient : storage.keySet())
         System.out.println(ingredient + ": " + storage.get(ingredient));

      Order order = orders.remove(0);
      HashMap<Product, Integer> products = order.getProducts();
      HashMap<Supply, Integer> ingredients = new HashMap<>();

      for (Product product : products.keySet()) {
         int nProduct = products.get(product);
         for (Supply ingredient : product.getIngredients()) {
            Integer value = ingredients.get(ingredient);
            int nIngredient = value == null ? 0 : value;
            ingredients.put(ingredient, nIngredient + nProduct);
         }
      }

      boolean ok = true;
      for (Supply ingredient : ingredients.keySet()) {
         int nIngredient = ingredients.get(ingredient);
         Integer value = storage.get(ingredient);
         int nsIngredient = value == null ? 0 : value;
         nIngredient = nsIngredient - nIngredient;
         ok = ok && nIngredient >= 0;
         ingredients.put(ingredient, nIngredient);
      }

      if (ok) {
         for (Supply ingredient : ingredients.keySet())
            storage.put(ingredient, ingredients.get(ingredient));
         BoxOrder.addOrder(order);
         System.out.println("\nPedido " + order.id + " encaminhado para embalagem.");
      } else {
         addOrder(order);
         System.out.println("\n<!> Pedido " + order.id + " não pode ser preparado.");
      }
   }

   @Override
   public String toString() {
      return "preparar pedido";
   }
}