package Main;

import Exceptions.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnexpectedCharacterException, UnexpectedOperatorException {
	// write your code here
        RPN rpn = new RPN();
        rpn.scan(new FileReader("Calc1.stk"));
        int resultado = rpn.run();
        System.out.println(resultado);
    }


}
