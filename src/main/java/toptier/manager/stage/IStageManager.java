package toptier.manager.stage;

import toptier.model.stage.Stage;

import java.util.List;

public interface IStageManager {    // user permissions, here and elsewhere | todo

    // create the stage owned by user and return stage id
    String createStage(Stage stage);

    // get a stage with the given id
    Stage getStage(String stageId);

    // get a list of all the stages owned by the given user | todo: pageable
    List<Stage> getStagesByUser(String userId);

    // delete stage with given id and return success/failure of deletion
    boolean deleteStage(String stageId);

    // update a stage and return it with its update properties
    Stage updateStage(Stage currentStage);
}
