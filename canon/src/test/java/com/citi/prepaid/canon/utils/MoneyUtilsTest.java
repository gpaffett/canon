package com.citi.prepaid.canon.utils;

import java.util.Locale;

import junit.framework.TestCase;

public class MoneyUtilsTest extends TestCase
{

  public void testFormat()
  {

    System.out.println("Testing Default Locale");

    MoneyParser utility = new MoneyParser();

    for (int i = -10000000; i < 10000000; i++)
    {
      Integer val = utility.parse(utility.format(Integer.valueOf(i)) );
      assertEquals(val.intValue(), i);
    }

    System.out.println("Testing Default Locale with Integer.MAX_VALUE");

    Integer valIntMax1 = utility.parse(utility.format(Integer.valueOf(Integer.MAX_VALUE)) );
    assertEquals(valIntMax1.intValue(), Integer.MAX_VALUE);

    System.out.println("Testing Default Locale with Integer.MIN_VALUE");

    Integer valIntMin1 = utility.parse(utility.format(Integer.valueOf(Integer.MIN_VALUE)) );
    assertEquals(valIntMin1.intValue(), Integer.MIN_VALUE);


    System.out.println("Testing " + Locale.GERMANY.getDisplayCountry() + " Locale" );

    MoneyParser utility2 = new MoneyParser(Locale.GERMANY);

    for (int i = -10000000; i < 10000000; i++)
    {

      Integer val = utility2.parse(utility2.format(Integer.valueOf(i)) );
      assertEquals(val.intValue(), i);
    }

    System.out.println("Testing " + Locale.GERMANY.getDisplayCountry() + " Locale with Integer.MAX_VALUE");

    Integer valIntMax2 = utility2.parse(utility2.format(Integer.valueOf(Integer.MAX_VALUE)) );
    assertEquals(valIntMax2.intValue(), Integer.MAX_VALUE);

    System.out.println("Testing " + Locale.GERMANY.getDisplayCountry() + " Locale with Integer.MIN_VALUE");

    Integer valIntMin2 = utility2.parse(utility2.format(Integer.valueOf(Integer.MIN_VALUE)) );
    assertEquals(valIntMin2.intValue(), Integer.MIN_VALUE);


    System.out.println("Testing " + Locale.JAPAN.getDisplayCountry() + " Locale");

    MoneyParser utility3 = new MoneyParser(Locale.JAPAN);

    for (int i = -10000000; i < 10000000; i++)
    {
      Integer val = utility3.parse(utility3.format(Integer.valueOf(i)) );
      assertEquals(val.intValue(), i);
    }

    System.out.println("Testing " + Locale.JAPAN.getDisplayCountry() + " Locale with Integer.MAX_VALUE");

    Integer valIntMax3 = utility3.parse(utility3.format(Integer.valueOf(Integer.MAX_VALUE)) );
    assertEquals(valIntMax3.intValue(), Integer.MAX_VALUE);

    System.out.println("Testing " + Locale.JAPAN.getDisplayCountry() + " Locale with Integer.MIN_VALUE");

    Integer valIntMin3 = utility3.parse(utility3.format(Integer.valueOf(Integer.MIN_VALUE)) );
    assertEquals(valIntMin3.intValue(), Integer.MIN_VALUE);



  }

}
