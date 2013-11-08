package com.citi.prepaid.sandbox;

public class PassByValue
{
   private int someValue = 21;
  
   public void setSomeValue(int value)
   {
     this.someValue = value;       
   }
   
   public int getSomeValue()
   {
     return someValue;
   }
   
   public static void changeReference(PassByValue passValue)
   {
     passValue.setSomeValue(54);
     
     System.out.println("Derefed and changed in method: " + passValue.getSomeValue());
     
     passValue = new PassByValue();
     
     System.out.println("Overwrote in method: " + passValue.getSomeValue());
   }
   
   public static void main(String[] args)
   {
     PassByValue test = new PassByValue();
     
     System.out.println("Instantiated: " + test.getSomeValue());
     
     test.setSomeValue(69);
     
     System.out.println("Changed: " + test.getSomeValue());
         
     PassByValue.changeReference(test);
     
     System.out.println("End: " + test.getSomeValue());
   }
}
