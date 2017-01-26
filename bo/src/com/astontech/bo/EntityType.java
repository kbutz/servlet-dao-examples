package com.astontech.bo;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class EntityType extends BaseBO {

    //region PROPERTIES
    private int EntityTypeId;
    private String EntityTypeName;

    //endregion
    //region CONSTRUCTORS
    public EntityType(){}

    public EntityType(String entityTypeName) {
        EntityTypeName = entityTypeName;
    }
    //endregion
    //region GETTERS AND SETTERS
    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public String getEntityTypeName() {
        return EntityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        EntityTypeName = entityTypeName;
    }
    //endregion
}
