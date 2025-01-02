package pt.up.fe.specs.jackdaw.abstracts.weaver;

import org.lara.interpreter.weaver.LaraWeaverEngine;
import java.util.Arrays;
import java.util.List;
import pt.up.fe.specs.jackdaw.enums.LoopKind;
import java.util.ArrayList;

/**
 * Abstract Weaver Implementation for JackdawWeaver<br>
 * Since the generated abstract classes are always overwritten, their implementation should be done by extending those abstract classes with user-defined classes.<br>
 * The abstract class {@link pt.up.fe.specs.jackdaw.abstracts.AJackdawWeaverJoinPoint} contains attributes and actions common to all join points.
 * The implementation of the abstract methods is mandatory!
 * @author Lara C.
 */
public abstract class AJackdawWeaver extends LaraWeaverEngine {

    /**
     * Get the list of available actions in the weaver
     * 
     * @return list with all actions
     */
    @Override
    public final List<String> getActions() {
        String[] weaverActions= {"refactor", "refactor", "refactorParam", "setName"};
        return Arrays.asList(weaverActions);
    }

    /**
     * Returns the name of the root
     * 
     * @return the root name
     */
    @Override
    public final String getRoot() {
        return "project";
    }

    /**
     * Returns a list of classes that may be imported and used in LARA.
     * 
     * @return a list of importable classes
     */
    @Override
    public final List<Class<?>> getAllImportableClasses() {
        Class<?>[] defaultClasses = {LoopKind.class};
        List<Class<?>> otherClasses = this.getImportableClasses();
        List<Class<?>> allClasses = new ArrayList<>(Arrays.asList(defaultClasses));
        allClasses.addAll(otherClasses);
        return allClasses;
    }

    /**
     * Does the generated code implements events?
     * 
     * @return true if implements events, false otherwise
     */
    @Override
    public final boolean implementsEvents() {
        return true;
    }
}
