package burger.model.supply;

import burger.exception.ValueException;
import burger.model.MapKey;

public class Cheese extends MapKey implements Supply {
   private static double price;

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) throws Exception {
      if (price < 0)
         throw new ValueException();
      Cheese.price = price;
   }

   @Override
   public String toString() {
      return "queijo";
   }
}