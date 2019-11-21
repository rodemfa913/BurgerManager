package burger.model.employee;

import burger.action.BoxOrder;

public class Boxer extends Employee {
   private static final BoxOrder action = new BoxOrder();
   private static int nBoxer;

   public Boxer() {
      super();
   }

   public Boxer(String login) {
      super(login);
   }

   public Boxer build() {
      return new Boxer(toString() + (nBoxer++));
   }

   public void signIn() {
      signIn(action);
   }

   @Override
   public String toString() {
      return "embalador";
   }
}