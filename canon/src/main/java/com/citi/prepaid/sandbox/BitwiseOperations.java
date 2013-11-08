package com.citi.prepaid.sandbox;

class BitwiseOperations
{
  public static void main(String[] args)
  {
    int add1 = 10; 
    int add2 = 99;
    
    int sub1 = 2000; 
    int sub2 = 1000;
    
    int div1 = 10000; 
    int div2 = 100;
    
    int mod1 = 525; 
    int mod2 = 100;
    
    int mult1 = 12; 
    int mult2 = 12;

    
    
    System.out.print(add1 + " + " + add2 + " = ");
    System.out.println(add(add1, add2));
    
    System.out.print(sub1 + " - " + sub2 + " = ");
    System.out.println(sub(sub1, sub2));

    System.out.print(mult1 + " * " + mult2 + " = ");
    System.out.println(mult(mult1, mult2));

    System.out.print(div1 + " / " + div2 + " = ");
    System.out.println(div(div1, div2));
    
    System.out.print(mod1 + " mod " + mod2 + " = ");
    System.out.println(mod(mod1, mod2));
        
    System.out.println("-Integer.MIN_VALUE == Integer.MIN_VALUE : " + String.valueOf(-Integer.MIN_VALUE == Integer.MIN_VALUE));    
  }

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

  static int add(int i, int j)
  {
    int carry, sum;

    carry = (i & j) << 1;

    sum = i ^ j;
    
    int carriesLeft = sum & carry;
    
    if (carriesLeft != 0)
    {
      return add(sum, carry);
    } 
    else
    {
      return sum ^ carry;
    }
  }
  
  static int sub(int i, int j )
  {
    return  add(i, add(~j,1));  
  }
  
  static int mult(int i, int j)
  {
    
    int product = 0;
    
    for ( int k = 1 ; k <= j; k++ )
    {
      product = add(product,i);      
    }
    
    return product;
  }
  
  
  static int div(int i, int j)
  {
    int remainder;
    int quotient = 0;
    
    if (i >= j )
    {
      remainder = sub(i,j);
      quotient++;
      
      while (remainder >= j)
      {
        remainder = sub(remainder,j);
        quotient++;
      }
    }
    
    return quotient;
  }
  
  static int mod(int i, int j)
  {
    int remainder = 0;    
    
    if (i >= j)
    {
      remainder = sub(i,j);      
      while (remainder >= j)
      {
        remainder = sub(remainder,j);
      }
    }
    
    return remainder;
  }
  
}
