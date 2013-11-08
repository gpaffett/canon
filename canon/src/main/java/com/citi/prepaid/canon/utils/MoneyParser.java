package com.citi.prepaid.canon.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author gp58112
 *
 */
public class MoneyParser
{

  /**
   * Useful Constant for Contry
   */
  public static MoneyParser US = new MoneyParser(Locale.US);
  /**
   *
   */
  public static MoneyParser CANADA = new MoneyParser(Locale.CANADA);
  /**
   *
   */
  public static MoneyParser CHINA = new MoneyParser(Locale.CHINA);
  /**
   *
   */
  public static MoneyParser ENGLISH = new MoneyParser(Locale.ENGLISH);
  /**
   *
   */
  public static MoneyParser FRANCE = new MoneyParser(Locale.FRANCE);
  /**
   *
   */
  public static MoneyParser ITALY = new MoneyParser(Locale.ITALY);
  /**
   *
   */
  public static MoneyParser JAPAN = new MoneyParser(Locale.JAPAN);
  /**
   *
   */
  public static MoneyParser KOREA = new MoneyParser(Locale.KOREA);
  /**
   *
   */
  public static MoneyParser TAIWAN = new MoneyParser(Locale.TAIWAN);
  /**
   *
   */
  public static MoneyParser UK = new MoneyParser(Locale.UK);

  private NumberFormat nf;
  private int decimalDigits;

  /**
   * Creates Money Parser Object with the default locale for this instance of
   * the Java Virtual Machine
   */
  public MoneyParser()
  {
    this(Locale.getDefault());
  }

  /**
   *
   * @param locale
   */
  public MoneyParser(Locale locale)
  {
    // TODO Figure out a more accurate way to determine
    nf = NumberFormat.getInstance(locale);
    decimalDigits = NumberFormat.getCurrencyInstance(locale)
        .getMaximumFractionDigits();
    nf.setGroupingUsed(false);
  }

  /**
   *
   * @param moneyAsString
   * @return
   */
  public Integer parse(String moneyAsString)
  {
    Number formattedMoney;

    try
    {
      formattedMoney = nf.parse(moneyAsString);
    } catch (ParseException pe)
    {
      throw new IllegalArgumentException("Amount: '" + moneyAsString
          + "' unable to be parsed");
    }

    BigDecimal amount = new BigDecimal(formattedMoney.toString())
        .movePointRight(decimalDigits);

    return Integer.valueOf(amount.intValue());

  }

  /**
   *
   * @param moneyAsInteger
   * @return
   */
  public String format(Integer moneyAsInteger)
  {

    BigDecimal moneyAsBigDecimal = new BigDecimal(moneyAsInteger)
        .movePointLeft(decimalDigits);

    return nf.format(moneyAsBigDecimal);
  }

}