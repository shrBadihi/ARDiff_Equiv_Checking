package equiv.checking;
import com.github.gumtreediff.actions.ActionGenerator;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.gen.jdt.JdtTreeGenerator;
import com.github.gumtreediff.io.ActionsIoUtils;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.matchers.Matchers;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GumTreeThings {

    /*** To be merged once tested enough  *****/

    public static void main(String[] args){
       String methodPath1 = "./src/examples/demo/original/old.java";
        String methodPath2 = "./src/examples/demo/original/newV.java";
        try {
            ChangeExtractor changeExtractor=new ChangeExtractor();
            ArrayList<Integer> changes= changeExtractor.obtainChanges(methodPath1,methodPath2,false, "./src/examples/demo/original/");
            for(Integer i:changes)
                System.out.println(i);

        }
        catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
