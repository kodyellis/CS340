// Kody Ellis Pizza.java Tuskegee University Database CS 340 class, Submission date: Feb5, 2018


//for collections, comparable, etc
import java.util.*;

//Comparable interface used for sorting by Pizza cost
public class Pizza implements Comparable<Pizza>{

   private final double SMALL = 9.5;
   private final double MEDIUM = 12.25;
   private final double  LARGE  = 14.5;
   private final double TOPPING = 1.5;

   private static final double TAX = 0.10;
   private static double totalCost = 0;

   private String crust; //hand-tossed, thin, thick
   private String size; //small, medium, large
   private String[] toppings; //pepperoni, beef, mushrooms, green pepper, tomato, olive, and sausage
   private String toppingList;

   private double cost;

   /**
    * Initializes a Pizza object
    * @param crust This is a string for the crust of the pizza
    * Should be hand-tossed, thick, or thin. Not case sensitive
    * @param size This is a string for the size of the pizza
    * Should be small, medium, or large. Not case sensitive
    * */
   public Pizza(String crust, String size) {
      //assignment 2 pizza = new assignment2();
      //crust to lowercase doesnt work in constructor
      this.crust = crust;
      this.size = size;

      this.toppings = new String[7];
      toppingList = "";

      cost = 0;

   }

   /**
    * Sets a new String value for string instance variable crust
    * @param crust This is a string for the size of the pizza
    * Should be hand-tossed, thick, or thin. Not case sensitive
    * */
   public void setCrust(String crust) {
      this.crust = crust;
   }

   /**
    * Sets a new String value for string instance variable size
    * @param size This is a String for the size of the pizza
    * Should be small, medium, large. Not case sensitive
    * */
   public void setSize(String size) {
      this.size = size;
   }


   /**
    * This sets the elements inside the toppings array with actual String elements
    * containing topping details
    * Before this is set, topping array might contain only null elements.
    * @param toppings This is a String array containing string elements
    * of toppings for the pizza instance.
    * */
   public void setToppings(String[] toppings) {

      int index = 0;

      /*
         every time you call this it will search the loop for null...first occurence of null
         it replaces that space with the topping
      */
      while (index < 7 && toppings[index] != null) {
         this.toppings[index] = toppings[index];
         index++;
      }
   }


   /**
    * Adds the parameter cost to the totalCost class variable
    * @param cost This is a double variable representing a Pizza's cost
    * */
   public static void setTotal(double cost) {
      totalCost += cost;
   }


   /**
    * This returns the total cost of the pizza
    * pizza class to use it.
    * @return the class variable total cost
    * */
   public static double getTotal() {
      return totalCost;
   }


   /**
    * This returns Tax constant variable
    * @return a double class constant variable tax
    * */
   public static double getTax() {
      return TAX;
   }


   /**
    *Returns crust varible
    * @return a string class instance variable
    * */
   public String getCrust() {
      return crust;
   }


   /**
    *Returns pizza size variable
    * @return a string class instance variable
    * */
   public String getSize() {
      return size;
   }


   /**
    *  Concatenates all non-null topping string element
    *   to a string then returns the string
    * @return List of toppings with possible commas in string data type
    * */
    public String getToppings() {
      if (toppingList == "") {

         int i = 0;
         while (i < 7 && toppings[i] != null) {

            if (toppingList != "") {
               toppingList = toppingList + ", " + toppings[i];

            }else {
               toppingList = toppingList + toppings[i];
            }

            i++;
         }
      }

      return toppingList;
    }

    /**
     * Calculates cost of Pizza based on size and amount of toppings
     * @return the cost of the pizza
     * */
   public double costOfPizza () {
      int toppingsAmount = 0;
      int index = 0;
      size = size.toLowerCase();
      crust = crust.toLowerCase();

      if (cost == 0) {

         if (size.equals("large")) {
            cost += LARGE;
         }
         else if(size.equals("medium")) {
            cost += MEDIUM;

         }else if(size.equals("small")) {
            cost += SMALL;
         }

         while(index < 7 && toppings[index] != null) {
            toppingsAmount++;
            index++;
         }

         cost += (toppingsAmount*TOPPING);
      }

      return cost;
   }


   /**
    * Method from the comparable interface
    * returns whether or not an object is less than, equal to, or greater than
    * the specified object
    * @param comparePizza a Pizza object that is passed as an argument
    * @return a negative integer is object is less than specified object
    *           a zero is the object is equal to the specified object
    *         a positive integer is object is greater than the specified object.
    * */
   //class Sorting implements Comparator<Pizza>
   public int compareTo(Pizza comparePizza) {

      double compareQuantity = ((Pizza) comparePizza).costOfPizza();

      double intCast = this.cost - compareQuantity;

      //needs to be int casted as this functions returns int
      return (int) intCast;

   }


   /**
    * From the Comparator interface. Uses Comparable interface
    * */
   public static Comparator<Pizza> PizzaCompare = new Comparator<Pizza>() {

      /**
       * @param the first object to be compared.
       * @param the second object to be compared.
       * @return a negative integer, zero, or a positive integer as
       * the first argument is less than, equal to, or greater than
       * the second.
       * @throws NullPointerException - if an argument is null and this
       * comparator does not permit null arguments
       * @throws  ClassCastException - if the arguments' types prevent them
       * from being compared by this comparator.
       * */
      public int compare(Pizza a, Pizza b) {

         //Double ab = a.costOfPizza();
         //Double ba = b.costOfPizza();

         return b.compareTo(a);
      }
   };


}
