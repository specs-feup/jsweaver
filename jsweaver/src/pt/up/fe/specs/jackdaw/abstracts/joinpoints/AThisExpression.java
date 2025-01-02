package pt.up.fe.specs.jackdaw.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AThisExpression
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AThisExpression extends AExpression {

    protected AExpression aExpression;

    /**
     * 
     */
    public AThisExpression(AExpression aExpression){
        this.aExpression = aExpression;
    }
    /**
     * Get value on attribute ancestor
     * @return the attribute's value
     */
    @Override
    public AJoinPoint ancestorImpl(String joinPointType) {
        return this.aExpression.ancestorImpl(joinPointType);
    }

    /**
     * Get value on attribute ast
     * @return the attribute's value
     */
    @Override
    public String getAstImpl() {
        return this.aExpression.getAstImpl();
    }

    /**
     * Get value on attribute childrenArrayImpl
     * @return the attribute's value
     */
    @Override
    public AJoinPoint[] getChildrenArrayImpl() {
        return this.aExpression.getChildrenArrayImpl();
    }

    /**
     * Get value on attribute code
     * @return the attribute's value
     */
    @Override
    public String getCodeImpl() {
        return this.aExpression.getCodeImpl();
    }

    /**
     * Get value on attribute column
     * @return the attribute's value
     */
    @Override
    public Integer getColumnImpl() {
        return this.aExpression.getColumnImpl();
    }

    /**
     * Get value on attribute descendantsArrayImpl
     * @return the attribute's value
     */
    @Override
    public AJoinPoint[] getDescendantsArrayImpl() {
        return this.aExpression.getDescendantsArrayImpl();
    }

    /**
     * Get value on attribute field
     * @return the attribute's value
     */
    @Override
    public Object fieldImpl(String fieldName) {
        return this.aExpression.fieldImpl(fieldName);
    }

    /**
     * Get value on attribute file
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getFileImpl() {
        return this.aExpression.getFileImpl();
    }

    /**
     * Get value on attribute joinPointName
     * @return the attribute's value
     */
    @Override
    public String getJoinPointNameImpl() {
        return this.aExpression.getJoinPointNameImpl();
    }

    /**
     * Get value on attribute line
     * @return the attribute's value
     */
    @Override
    public Integer getLineImpl() {
        return this.aExpression.getLineImpl();
    }

    /**
     * Get value on attribute parent
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getParentImpl() {
        return this.aExpression.getParentImpl();
    }

    /**
     * Get value on attribute root
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getRootImpl() {
        return this.aExpression.getRootImpl();
    }

    /**
     * Get value on attribute type
     * @return the attribute's value
     */
    @Override
    public String getTypeImpl() {
        return this.aExpression.getTypeImpl();
    }

    /**
     * Get value on attribute uuid
     * @return the attribute's value
     */
    @Override
    public String getUuidImpl() {
        return this.aExpression.getUuidImpl();
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
        return this.aExpression.insertImpl(position, code);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint code) {
        return this.aExpression.insertImpl(position, code);
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AExpression> getSuper() {
        return Optional.of(this.aExpression);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	default:
        		joinPointList = this.aExpression.select(selectName);
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
        this.aExpression.fillWithAttributes(attributes);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aExpression.fillWithSelects(selects);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aExpression.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "thisExpression";
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
        return this.aExpression.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum ThisExpressionAttributes {
        ANCESTOR("ancestor"),
        AST("ast"),
        CHILDREN("children"),
        CODE("code"),
        COLUMN("column"),
        DESCENDANTS("descendants"),
        FIELD("field"),
        FILE("file"),
        JOINPOINTNAME("joinPointName"),
        LINE("line"),
        PARENT("parent"),
        ROOT("root"),
        TYPE("type"),
        UUID("uuid");
        private String name;

        /**
         * 
         */
        private ThisExpressionAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<ThisExpressionAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(ThisExpressionAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
