package com.routine.def.common.logStandart.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
/*  
 * import java.lang.annotation.Retention;: Imports the Retention annotation from the java.lang.annotation package. 
 * This annotation indicates how long annotations with the annotated type are to be retained.
*/
import java.lang.annotation.RetentionPolicy;
/*
 * import java.lang.annotation.RetentionPolicy;: Imports the RetentionPolicy enum from the java.lang.annotation package.
 *  This enum defines the possible retention policies for annotations.
 */
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogInterface {
    String request_type();
    String user();
    String http_status();
    int status_message();
}

/*
 * @Retention(RetentionPolicy.RUNTIME): This annotation specifies that the LogInterface annotation should be retained at runtime. 
 * This means that the annotation will be available to the JVM through runtime, making it possible to use reflection to inspect 
 * the annotation during program execution.
 */

 /*
  * @Target(ElementType.METHOD): This annotation specifies that the LogInterface annotation can only be applied to methods. 
  The ElementType.METHOD value from the ElementType enum restricts the usage to method declarations.
  */

  /*
   * public @interface LogInterface: This line declares a new annotation type called LogInterface. 
   * The @interface keyword is used to declare an annotation.
   */