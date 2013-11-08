package com.citi.prepaid.domain;

public class DDA
{
  private String number;
  private String memberId;
  
  public DDA(String number)
  {
    super();
    this.number = number;
  }
  public String getNumber()
  {
    return number;
  }
  public void setNumber(String number)
  {
    this.number = number;
  }
  public String getMemberId()
  {
    return memberId;
  }
  public void setMemberId(String memberId)
  {
    this.memberId = memberId;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object arg)
  {
    return number.equals(arg.toString());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return number;
  }
  
  
  
}
