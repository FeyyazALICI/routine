package com.routine.def.common.dtoMapper;

public class EntityToDaoMapper{
    // Generic method to convert an entity to a DAO
    public static <E, D> D convertToDao(E entity, Class<D> daoClass) {
        try{
            /* D daoInstance = new D();   | this can not be used because D is a type parameter, 
             * and Java does not allow you to directly instantiate a type parameter like that. In Java, 
             * type parameters are not known at runtime due to type erasure, which means the compiler cannot create an instance of D in that way.
            */
                D daoInstance = daoClass.getDeclaredConstructor().newInstance();    // getDeclaredConstructor -> creates constructor
            // Loop through each field in the DAO class
                for(java.lang.reflect.Field daoField: daoClass.getDeclaredFields()){
                    daoField.setAccessible(true); // Allow access to private fields of dao class
                // Get the corresponding field in the entity class -> just the field name, not its value
                    java.lang.reflect.Field entityField = entity.getClass().getDeclaredField(daoField.getName());
                    entityField.setAccessible(true);
                // Copy the value from the entity field to the DAO field
                    Object value = entityField.get(entity); // Get the value from the entity
                    daoField.set(daoInstance, value); // Set the value in the DAO
                }
                return daoInstance;     // Return the populated DAO instance
            }catch(Exception e){
            throw new RuntimeException("Failed to convert entity to dao", e);
        }
    }
}


