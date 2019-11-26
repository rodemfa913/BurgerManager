package burger.model.employee;

import burger.BurgerMan;
import burger.action.Action;
import burger.model.Person;

import java.util.HashMap;

public abstract class Employee extends Person {
   public final String login;
   private HashMap<String, String> profile;

   public Employee() {
      this("-");
   }

   public Employee(String login) {
      this.login = login;
      this.profile = new HashMap<>();
   }

   public abstract Employee build();

   public HashMap<String, String> getProfile() {
      return new HashMap<>(profile);
   }

   public String putAttribute(String key, String value) {
      return profile.put(key, value);
   }

   public abstract void signIn();

   protected void signIn(Action... actions) {
      while (true) {
         System.out.println("\n0 - sair");
         int a;
         for (a = 1; a <= actions.length; a++)
            System.out.println(a + " - " + actions[a - 1]);
         System.out.print("\nAção: ");

         try {
            a = Integer.parseInt(BurgerMan.input.nextLine());
            if (a == 0)
               break;

            actions[a - 1].execute(this);
         } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("<!> Entrada inválida.");
         } catch (Exception ex) {
            System.out.println("<!> " + ex.getMessage());
         }
      }
   }
}