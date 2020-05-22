package equiv.checking.symparser;

import java.util.List;
import java.util.function.Function;

public class SymLexer {
    int index = -1;
    int current;
    String spfOutput;

    public SymLexer(String spfOutput){
        this.spfOutput=spfOutput;
    }


    public void nextChar(){
        current = (++ index < spfOutput.length()) ? spfOutput.charAt(index) : -1 ;
    }

    public boolean eat(int charToEat){
        while(current==' ') nextChar();
        if(current == charToEat){
            nextChar();
            return true;
        }
        return false;
    }

}
