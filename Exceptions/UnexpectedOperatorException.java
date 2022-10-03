package Exceptions;

public class UnexpectedOperatorException extends Exception {
  public UnexpectedOperatorException(String operator){ super("Unexpected operator: " + operator);}
}
