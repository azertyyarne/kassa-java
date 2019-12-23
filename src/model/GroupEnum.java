package model;

import java.util.ArrayList;
import java.util.List;

public enum GroupEnum {
    GR1("gr1"),
    GR2("gr2");

    private String description;

    GroupEnum(String description){
        this.description = description;
    }

    public static List<String> getDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (GroupEnum groupEnum : GroupEnum.values()){
            descriptions.add(groupEnum.description);
        }
        return descriptions;
    }
}
