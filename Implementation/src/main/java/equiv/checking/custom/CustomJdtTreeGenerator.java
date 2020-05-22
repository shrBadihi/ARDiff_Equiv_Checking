package equiv.checking.custom;

import com.github.gumtreediff.gen.SyntaxException;
import com.github.gumtreediff.gen.jdt.AbstractJdtTreeGenerator;
import com.github.gumtreediff.gen.jdt.AbstractJdtVisitor;
import com.github.gumtreediff.gen.jdt.cd.EntityType;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class CustomJdtTreeGenerator extends AbstractJdtTreeGenerator {

    private static char[] readerToCharArray(Reader r) throws IOException {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(r)) {
            char[] buf = new char[10];
            int numRead = 0;
            while ((numRead = br.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
        }
        return  fileData.toString().toCharArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public TreeContext generate(Reader r) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS9);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        Map pOptions = JavaCore.getOptions();
        pOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_9);
        pOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_9);
        pOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_9);
        pOptions.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
        parser.setCompilerOptions(pOptions);
        parser.setSource(readerToCharArray(r));
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        CustomJdtTreeVisitor v = this.createVisitor(cu);
        if ((cu.getFlags() & 1) != 0) {
            throw new SyntaxException(this, r);
        } else {
            cu.accept(v);
            return v.getTreeContext();
        }

    }

    @Override
    protected AbstractJdtVisitor createVisitor() {
        return null;
    }

    protected CustomJdtTreeVisitor createVisitor(CompilationUnit cu){
        return new CustomJdtTreeVisitor(cu);
    }
}

