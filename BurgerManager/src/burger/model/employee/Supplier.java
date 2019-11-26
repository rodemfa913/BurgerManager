package burger.model.employee;

import burger.action.AcquireSupplies;
import burger.action.Action;
import burger.action.EditProfile;
import burger.exception.ValueException;

public class Supplier extends Employee {
   private static Action[] actions = new Action[] {new AcquireSupplies(), new EditProfile()};
   private static int nSupplier;
   private static double salary;

   public Supplier() {
      super();
   }

   public Supplier(String login) {
      super(login);
   }

   @Override
   public Supplier build() {
      return new Supplier(toString() + (nSupplier++));
   }

   public double getSalary() {
      return salary;
   }

   public void setSalary(double salary) throws Exception {
      if (salary < 0)
         throw new ValueException();
      Supplier.salary = salary;
   }

   @Override
   public void signIn() {
      signIn(actions);
   }

   @Override
   public String toString() {
      return "fornecedor";
   }
}