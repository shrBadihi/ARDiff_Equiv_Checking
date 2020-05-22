package equiv.checking.custom;

import com.github.gumtreediff.gen.jdt.cd.EntityType;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomJdtTreeVisitor extends ASTVisitor {
    private CompilationUnit cu;
    protected TreeContext context = new TreeContext();
    private Deque<ITree> trees = new ArrayDeque();

    public CustomJdtTreeVisitor(CompilationUnit cu){
        super(true);
        this.cu=cu;
    }

   public TreeContext getTreeContext() {
        return this.context;
    }

    protected void pushNode(ASTNode n, String label) {
        int type = n.getNodeType();
        String typeName = n.getClass().getSimpleName();
       this.push(type, typeName, label, n.getStartPosition(), n.getLength());
    }

    protected void pushFakeNode(EntityType n, int startPosition, int length) {
        int type = -n.ordinal();
        String typeName = n.name();
        this.push(type, typeName, "", startPosition, length);
    }

    private void push(int type, String typeName, String label, int startPosition, int length) {
        ITree t = this.context.createTree(type, label, typeName);
        int startLine=cu.getLineNumber(startPosition);
        int numberOfLines=cu.getLineNumber(startPosition+length)-startLine;
        t.setPos(startLine);
        t.setLength(numberOfLines);
        if (this.trees.isEmpty()) {
            this.context.setRoot(t);
        } else {
            ITree parent = (ITree)this.trees.peek();
            t.setParentAndUpdateChildren(parent);
        }

        this.trees.push(t);
    }

    protected ITree getCurrentParent() {
        return (ITree)this.trees.peek();
    }

    protected void popNode() {
        this.trees.pop();
    }


    public void preVisit(ASTNode n) {
        this.pushNode(n, this.getLabel(n));
    }

    protected String getLabel(ASTNode n) {
        if (n instanceof Name) {
            return ((Name)n).getFullyQualifiedName();
        } else if (n instanceof Type) {
            return n.toString();
        } else if (n instanceof Modifier) {
            return n.toString();
        } else if (n instanceof StringLiteral) {
            return ((StringLiteral)n).getEscapedValue();
        } else if (n instanceof NumberLiteral) {
            return ((NumberLiteral)n).getToken();
        } else if (n instanceof CharacterLiteral) {
            return ((CharacterLiteral)n).getEscapedValue();
        } else if (n instanceof BooleanLiteral) {
            return ((BooleanLiteral)n).toString();
        } else if (n instanceof InfixExpression) {
            return ((InfixExpression)n).getOperator().toString();
        } else if (n instanceof PrefixExpression) {
            return ((PrefixExpression)n).getOperator().toString();
        } else if (n instanceof PostfixExpression) {
            return ((PostfixExpression)n).getOperator().toString();
        } else if (n instanceof Assignment) {
            return ((Assignment)n).getOperator().toString();
        } else if (n instanceof TextElement) {
            return n.toString();
        } else if (n instanceof TagElement) {
            return ((TagElement)n).getTagName();
        } else if (n instanceof TypeDeclaration) {
            return ((TypeDeclaration) n).isInterface() ? "interface" : "class";
        }else {
            return "";
        }
    }

    public boolean visit(TagElement e) {
        return true;
    }

    public boolean visit(QualifiedName name) {
        return false;
    }

    public void postVisit(ASTNode n) {
        this.popNode();
    }

}
