package io.vlingo.xoom.designer.gui;

import io.vlingo.xoom.designer.gui.XoomInitializer;
import io.vlingo.xoom.designer.task.TaskExecutionContext;
import io.vlingo.xoom.designer.task.TaskExecutionException;
import io.vlingo.xoom.designer.task.TaskExecutor;
import io.vlingo.xoom.designer.task.TaskExecutionStep;

public class UserInterfaceBootstrapStep implements TaskExecutionStep {

    @Override
    public void process(final TaskExecutionContext context) {
        try {
            TaskExecutor.skipAutomaticExit();
            XoomInitializer.main(new String[]{});
        } catch (final Exception exception) {
            throw new TaskExecutionException(exception);
        }
    }

}
