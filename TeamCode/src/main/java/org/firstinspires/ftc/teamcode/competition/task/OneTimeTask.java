package org.firstinspires.ftc.teamcode.competition.task;

public interface OneTimeTask extends Task {

    void init();

    @Override
    default void iterate() {}

    @Override
    default boolean hasNext() {
        return false;
    }
}
