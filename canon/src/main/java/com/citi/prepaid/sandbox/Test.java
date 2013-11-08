package com.citi.prepaid.sandbox;

import com.ecount.core.enums.ServiceExceptionProperties;

public class Test
{
    
    static void printIntAsBinary(String s, int i)
    {
      System.out.print(s + " as binary: ");
      for (int j = 31; j >= 0; j--)
        if (((1 << j) & i) != 0)
          System.out.print("1");
        else
          System.out.print("0");
      System.out.println();
    }
 
  public static void main(String[] args)  
  {
      
      
      int initCode = 1;
      int finalCode = initCode & 0xffff;
      
      printIntAsBinary("1", initCode);
      printIntAsBinary("0xffff", 0xffff);
      
      printIntAsBinary("code & 0xffff : ", finalCode );
      
      //int finalClass =  ((initCode & 0xffff) / ServiceExceptionProperties.ClassIdOffset);
      
      //System.out.println("((initCode & 0xffff) / ServiceExceptionProperties.ClassIdOffset) : " + finalClass );
      
      
      //mCode   = ((code & 0xffff) % ServiceExceptionProperties.ClassIdOffset) + 
//                 (getCodeClassID() * ServiceExceptionProperties.ClassIdOffset);
  }

}
