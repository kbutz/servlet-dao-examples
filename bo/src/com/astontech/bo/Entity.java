package com.astontech.bo;

/**
 * Created by kylebutz1 on 10/31/2016.
 */
public class Entity extends BaseBO {
    private int EntityId;
    private String EntityName;

    public Entity(){}
    public Entity(int entityId, String entityName) {
        EntityId = entityId;
        EntityName = entityName;
    }

    public int getEntityId() {
        return EntityId;
    }

    public void setEntityId(int entityId) {
        EntityId = entityId;
    }

    public String getEntityName() {
        return EntityName;
    }

    public void setEntityName(String entityName) {
        EntityName = entityName;
    }
}
