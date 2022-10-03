package Main;

import Exceptions.UnexpectedCharacterException;
import Exceptions.UnexpectedOperatorException;

import java.util.*;
import java.io.*;

public class RPN {
  private ArrayList<Token> tokens = new ArrayList<>();

  public void scan(FileReader file) throws UnexpectedCharacterException {
      try (Scanner Scan = new Scanner(file)) {
        while(Scan.hasNext()) {
              Token token;
              if(Scan.hasNextInt()){
                  token = new Token(TokenType.NUM, Scan.nextLine());
                  this.tokens.add(token);
              }else{
                  String operator = Scan.nextLine();
                  switch (operator) {
                      case "+":
                          token = new Token(TokenType.PLUS, operator);
                          tokens.add(token);
                          break;
                      case "-":
                          token = new Token(TokenType.MINUS, operator);
                          tokens.add(token);
                          break;
                      case "*":
                          token = new Token(TokenType.STAR, operator);
                          tokens.add(token);
                          break;
                      case "/":
                          token = new Token(TokenType.SLASH, operator);
                          tokens.add(token);
                          break;
                      default:
                          UnexpectedCharacterException error = new UnexpectedCharacterException(operator);
                          throw error;
                  }
              }
          }
    }
  }

  public int run() throws UnexpectedOperatorException {
      Stack<Integer> stack = new Stack<>();
      for(Token token : this.tokens) {
          try{
              stack.push(Integer.parseInt(token.lexeme));
          }catch (NumberFormatException e){
              int x = 0, y = 0;
              y = stack.pop();
              x = stack.pop();

              switch (token.type){
                  case MINUS -> stack.push(x-y);
                  case PLUS -> stack.push(x+y);
                  case STAR -> stack.push(x*y);
                  case SLASH -> stack.push(x/y);
                  case EOF -> {}
                  case NUM -> {}
                  default -> {
                      UnexpectedOperatorException error = new UnexpectedOperatorException(token.lexeme);
                      throw error;
                  }
              }
          }
      }
      return stack.get(0);
  }
}
