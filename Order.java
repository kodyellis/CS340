// Kody Ellis Order.java Tuskegee University Database CS 340 class, Submission date: Feb5, 2018

import java.io.*;

//importing 4 more because thats better than importing all of util.
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
//legacy package, only used because ease-of-access for this particular assignment.
import java.util.StringTokenizer;

/**
 * Represents a pizza order, comprised of one or more pizzas.
 * */
public class Order {

   //static allows this to be used through the whole class
   //since it is a variable of a class instance
   public static ArrayList<Pizza> pizzaList =  new ArrayList<Pizza>();


   public static void main(String[] args) {

      String fileName;

      // is filename is not provided to program at runtime, then the program
      //doesn't crash and filename example is provided instead.
      try {

         fileName = args[0];

   } catch(Exception ex) {
         fileName = "testFile.dat";
      }

      Order thisOrder = new Order();

      thisOrder.getFile(fileName);

      //sorts array list based on cost of each pizza element then rearranges based on descending order(high to low)
      Collections.sort(pizzaList, Collections.reverseOrder());

      thisOrder.display();
      thisOrder.getCost();

   }

   /**
    * Opens target file and extracts pizza crust, size, and toppings data.
    * After data is extracted for 1 pizza,  switches to another function
    * to add that pizza's data to the pizza arraylist.
    * */
   public void getFile(String fileName) {
      String crust = "n/a";
      String size = "n/a";
      StringTokenizer test;
      Order myOrder = new Order();

      String[] toppings = new String[7];

      //so the scanner object is there in case the file directory is mssing
        Scanner input = new Scanner("n/a");

      try{
         input = new Scanner(new File(fileName));//"C:\\Users\\Kody Ellis\\eclipse-workspace\\cs340 lab2\\src\\lab2\\testFile"));

      }catch (Exception ex) {

         ex.printStackTrace();
      }

      while(input.hasNextLine()) {
         //starts line 1, gets crust and size
         String line = input.nextLine();
         test = new StringTokenizer(line, ", ");
         crust = test.nextToken();
         size = test.nextToken();

         //starts line 2, gets each topping
         line = input.nextLine();
         test = new StringTokenizer(line, ",");

         //gets string array
         int index = 0;
         while(test.hasMoreTokens()){
            toppings[index] = test.nextToken();
            index++;
         }


         //adds to array after every 2lines(a.k.a. pizzas)
         myOrder.addList(crust,size,toppings);
         }

      //closes scanner
      input.close();

   }



   /**
    * Adds  a Pizza object to a Pizza ArrayList.
    * @param crust This is The Pizza's crust
    * @param size This is the Size of the pizza. Used in
    *                finding the cost of the pizza
    * @param toppings These are the toppings of the Pizza. Used in
    *                finding the cost of the pizza.
    */
   public void addList(String crust, String size, String[] toppings) {
      //create pizza object
      Pizza myPizza = new Pizza(crust, size);
      myPizza.setToppings(toppings);

      //for code stability
      myPizza.costOfPizza();

      //add to array list
      pizzaList.add(myPizza);

   }



   /**
    * calculates cost of each pizza and gets total amount
    * should be changed in order to accommodate adding more pizzas from user choice
    */
   public void getCost() {
      double cost = 0;
      double totalCost = 0;

      //Doesnt calculate cost anymore once it's been called.'
      //should get changed if we are adding the feature  add multiple pizzas again
       //total cost is creating a pizza object iterating through each on gettign the cost then computing up total cost
      if (Pizza.getTotal() == 0) {
         for (int i = 0; i < pizzaList.size(); i++) {
            cost = pizzaList.get(i).costOfPizza();

            //System.out.println("pizza"+i+" cost: "+cost);
            Pizza.setTotal(cost);

         }
      }

      System.out.println("Pizza subtotal: " + Pizza.getTotal());

      totalCost = Pizza.getTotal() + (Pizza.getTotal()*Pizza.getTax());
      System.out.println("Pizza total: " + totalCost);
   }






   /**
    * Prints out info about each pizza, like crust, size, toppings and cost info.
    * */
   public void display() {

      for (int i = 0; i < pizzaList.size(); i++) {
         System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
         //i+1 because i starts at 0

         //Pizza number isnt necessarily the number of the pizza when it was ordered,
         //just repressents the ordererd place of the pizza in the list.
         System.out.println("Pizza "+(i+1)+"\n"+"Crust:"+ pizzaList.get(i).getCrust()+"\n"+"Size: "+pizzaList.get(i).getSize()+"\n"+"Toppings: "+pizzaList.get(i).getToppings()+"\n"+"Cost:"+pizzaList.get(i).costOfPizza());
         System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

      }
   }
}
