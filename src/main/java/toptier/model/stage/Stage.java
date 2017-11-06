package toptier.model.stage;

import toptier.model.common.Auditable;
import toptier.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class Stage extends Auditable {

    private String title;
    private User user;
    private List<StageItem> stageItems;
    private boolean system = false;

    public Stage(String title, User user) {
        this.title = title;
        this.user = user;
        stageItems = new ArrayList<>();
    }

    public Stage() {
        stageItems = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<StageItem> getStageItems() {
        return stageItems;
    }

    public void setStageItems(List<StageItem> stageItems) {
        this.stageItems = stageItems;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

}
