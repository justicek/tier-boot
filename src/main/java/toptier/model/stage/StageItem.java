package toptier.model.stage;

import toptier.model.common.Auditable;

public class StageItem extends Auditable {

    private String title;
    private String description;
    private int ordinal;
    private Stage stage;

    public StageItem(String title, int ordinal) {
        this.title = title;
        this.ordinal = ordinal;
    }

    public StageItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
