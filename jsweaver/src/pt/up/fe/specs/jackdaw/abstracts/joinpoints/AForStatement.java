package pt.up.fe.specs.jackdaw.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.AttributeException;
import java.util.List;
import org.lara.interpreter.weaver.interf.SelectOp;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AForStatement
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AForStatement extends ALoop {

    protected ALoop aLoop;

    /**
     * 
     */
    public AForStatement(ALoop aLoop){
        this.aLoop = aLoop;
    }
    /**
     * Initialization expression of this for statement.
     */
    public abstract AJoinPoint getInitImpl();

    /**
     * Initialization expression of this for statement.
     */
    public final Object getInit() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "init", Optional.empty());
        	}
        	AJoinPoint result = this.getInitImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "init", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "init", e);
        }
    }

    /**
     * Test expression of this for statement.
     */
    public abstract AJoinPoint getTestImpl();

    /**
     * Test expression of this for statement.
     */
    public final Object getTest() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "test", Optional.empty());
        	}
        	AJoinPoint result = this.getTestImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "test", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "test", e);
        }
    }

    /**
     * Update expression of this for statement.
     */
    public abstract AJoinPoint getUpdateImpl();

    /**
     * Update expression of this for statement.
     */
    public final Object getUpdate() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "update", Optional.empty());
        	}
        	AJoinPoint result = this.getUpdateImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "update", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "update", e);
        }
    }

    /**
     * Default implementation of the method used by the lara interpreter to select blockStatements
     * @return 
     */
    public List<? extends ABlockStatement> selectBlockStatement() {
        return select(pt.up.fe.specs.jackdaw.abstracts.joinpoints.ABlockStatement.class, SelectOp.DESCENDANTS);
    }

    /**
     * Get value on attribute kind
     * @return the attribute's value
     */
    @Override
    public String getKindImpl() {
        return this.aLoop.getKindImpl();
    }

    /**
     * Get value on attribute isInnermost
     * @return the attribute's value
     */
    @Override
    public Boolean getIsInnermostImpl() {
        return this.aLoop.getIsInnermostImpl();
    }

    /**
     * Get value on attribute nestedLevel
     * @return the attribute's value
     */
    @Override
    public Integer getNestedLevelImpl() {
        return this.aLoop.getNestedLevelImpl();
    }

    /**
     * Get value on attribute parent
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getParentImpl() {
        return this.aLoop.getParentImpl();
    }

    /**
     * Get value on attribute joinPointName
     * @return the attribute's value
     */
    @Override
    public String getJoinPointNameImpl() {
        return this.aLoop.getJoinPointNameImpl();
    }

    /**
     * Get value on attribute ast
     * @return the attribute's value
     */
    @Override
    public String getAstImpl() {
        return this.aLoop.getAstImpl();
    }

    /**
     * Get value on attribute code
     * @return the attribute's value
     */
    @Override
    public String getCodeImpl() {
        return this.aLoop.getCodeImpl();
    }

    /**
     * Get value on attribute line
     * @return the attribute's value
     */
    @Override
    public Integer getLineImpl() {
        return this.aLoop.getLineImpl();
    }

    /**
     * Get value on attribute ancestor
     * @return the attribute's value
     */
    @Override
    public AJoinPoint ancestorImpl(String joinPointType) {
        return this.aLoop.ancestorImpl(joinPointType);
    }

    /**
     * Get value on attribute column
     * @return the attribute's value
     */
    @Override
    public Integer getColumnImpl() {
        return this.aLoop.getColumnImpl();
    }

    /**
     * Get value on attribute type
     * @return the attribute's value
     */
    @Override
    public String getTypeImpl() {
        return this.aLoop.getTypeImpl();
    }

    /**
     * Get value on attribute descendantsArrayImpl
     * @return the attribute's value
     */
    @Override
    public AJoinPoint[] getDescendantsArrayImpl() {
        return this.aLoop.getDescendantsArrayImpl();
    }

    /**
     * Get value on attribute uuid
     * @return the attribute's value
     */
    @Override
    public String getUuidImpl() {
        return this.aLoop.getUuidImpl();
    }

    /**
     * Get value on attribute file
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getFileImpl() {
        return this.aLoop.getFileImpl();
    }

    /**
     * Get value on attribute field
     * @return the attribute's value
     */
    @Override
    public Object fieldImpl(String fieldName) {
        return this.aLoop.fieldImpl(fieldName);
    }

    /**
     * Get value on attribute childrenArrayImpl
     * @return the attribute's value
     */
    @Override
    public AJoinPoint[] getChildrenArrayImpl() {
        return this.aLoop.getChildrenArrayImpl();
    }

    /**
     * Get value on attribute root
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getRootImpl() {
        return this.aLoop.getRootImpl();
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
        return this.aLoop.insertImpl(position, code);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint code) {
        return this.aLoop.insertImpl(position, code);
    }

    /**
     * 
     */
    @Override
    public Optional<? extends ALoop> getSuper() {
        return Optional.of(this.aLoop);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "blockStatement": 
        		joinPointList = selectBlockStatement();
        		break;
        	default:
        		joinPointList = this.aLoop.select(selectName);
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
        this.aLoop.fillWithAttributes(attributes);
        attributes.add("init");
        attributes.add("test");
        attributes.add("update");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aLoop.fillWithSelects(selects);
        selects.add("blockStatement");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aLoop.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "forStatement";
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
        return this.aLoop.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum ForStatementAttributes {
        INIT("init"),
        TEST("test"),
        UPDATE("update"),
        KIND("kind"),
        ISINNERMOST("isInnermost"),
        NESTEDLEVEL("nestedLevel"),
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
        private ForStatementAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<ForStatementAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(ForStatementAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
