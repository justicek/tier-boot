package toptier.model.common;

import java.util.UUID;

public class UID {  // todo: persistence/validation annotations

    private String id;

    public UID(String id) {
        this.id = id;
    }

    public UID() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String generateNewUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
