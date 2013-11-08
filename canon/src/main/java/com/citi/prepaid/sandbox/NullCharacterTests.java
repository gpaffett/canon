package com.citi.prepaid.sandbox;

import java.util.Hashtable;

public class NullCharacterTests
{

  public static void main (String[] args)
  {
    char[] test = { 0x47 , 0x65, 0x6F, 0x66, 0x00, 0x66 };
    
    String testString = String.valueOf(test);
    
    System.out.println("Test String: " + testString );
    System.out.println("Test String Length: " + testString.length() );
    
    Hashtable<String, String> testHashtable = new Hashtable<String, String>(1);
    
    testHashtable.put(testString, testString);
    
    System.out.println("Test String from Hashtable: " + testHashtable.get(testString) );
    System.out.println("Test String Length from Hashtable: " + testHashtable.get(testString).length() );
    
  }
  
  
}
