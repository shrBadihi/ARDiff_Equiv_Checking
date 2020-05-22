package equiv.checking;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public interface Utils {
    boolean DEBUG = false;
    boolean Z3_TERMINAL = true;
    HashSet<String> mathFunctions = new HashSet<String>(Arrays.asList("cos","sin","pow","exp","sqrt","pow","sqrt","asin","acos","atan","atan2","abs","log","tan"));

    default void compile(String classpath,File newFile) throws IOException {
        File path = new File(classpath);
        path.getParentFile().mkdirs();
        //Think about whether to do it for the classpaths in the tool as well (maybe folder instrumented not automatically created)
        if(!path.exists())
            path.createNewFile();
        ArrayList<String> options = new ArrayList<>();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        options.addAll(Arrays.asList("-g", "-d", classpath));
        Iterable<? extends JavaFileObject> cpu =
                fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File[]{newFile}));
        compiler.getTask(null, fileManager, null, options, null, cpu).call();
    }
}
