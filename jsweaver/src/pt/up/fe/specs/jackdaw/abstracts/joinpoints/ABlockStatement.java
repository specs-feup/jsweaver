package pt.up.fe.specs.jackdaw.abstracts.joinpoints;

import java.util.List;
import org.lara.interpreter.weaver.interf.SelectOp;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point ABlockStatement
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class ABlockStatement extends AStatement {

    protected AStatement aStatement;

    /**
     * 
     */
    public ABlockStatement(AStatement aStatement){
        this.aStatement = aStatement;
    }
    /**
     * Default implementation of the method used by the lara interpreter to select scopes
     * @return 
     */
    public List<? extends AScope> selectScope() {
        return select(pt.up.fe.specs.jackdaw.abstracts.joinpoints.AScope.class, SelectOp.DESCENDANTS);
    }

    /**
     * Method used by the lara interpreter to select blockStatements
     * @return 
     */
    @Override
    public List<? extends ABlockStatement> selectBlockStatement() {
        return this.aStatement.selectBlockStatement();
    }

    /**
     * Method used by the lara interpreter to select classBodys
     * @return 
     */
    @Override
    public List<? extends AClassBody> selectClassBody() {
        return this.aStatement.selectClassBody();
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
        return this.aStatement.insertImpl(position, code);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint code) {
        return this.aStatement.insertImpl(position, code);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.aStatement.toString();
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AStatement> getSuper() {
        return Optional.of(this.aStatement);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "scope": 
        		joinPointList = selectScope();
        		break;
        	case "blockStatement": 
        		joinPointList = selectBlockStatement();
        		break;
        	case "classBody": 
        		joinPointList = selectClassBody();
        		break;
        	default:
        		joinPointList = this.aStatement.select(selectName);
        		break;
        }
        return joinPointList;
    }

    /**
     * 
     */
    @Override
    public final void defImpl(String attribute, Object value) {
        switch(attribute){
        default: throw new UnsupportedOperationException("Join point "+get_class()+": attribute '"+attribute+"' cannot be defined");
        }
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        this.aStatement.fillWithAttributes(attributes);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aStatement.fillWithSelects(selects);
        selects.add("scope");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aStatement.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "blockStatement";
    }

    /**
     * Defines if this joinpoint is an instanceof a given joinpoint class
     * @return True if this join point is an instanceof the given class
     */
    @Override
    public final boolean instanceOf(String joinpointClass) {
        boolean isInstance = get_class().equals(joinpointClass);
        if(isInstance) {
        	return true;
        }
        return this.aStatement.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum BlockStatementAttributes {
        PARENT("parent"),
        JOINPOINTNAME("joinPointName"),
        AST("ast"),
        CODE("code"),
        LINE("line"),
        ANCESTOR("ancestor"),
        COLUMN("column"),
        TYPE("type"),
        DESCENDANTS("descendants"),
        UUID("uuid"),
        FILE("file"),
        FIELD("field"),
        CHILDREN("children"),
        ROOT("root");
        private String name;

        /**
         * 
         */
        private BlockStatementAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<BlockStatementAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(BlockStatementAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
