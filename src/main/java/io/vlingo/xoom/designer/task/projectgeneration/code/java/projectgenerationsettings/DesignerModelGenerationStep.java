// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.task.projectgeneration.code.java.projectgenerationsettings;

import io.vlingo.xoom.codegen.CodeGenerationContext;
import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateProcessingStep;
import io.vlingo.xoom.designer.task.projectgeneration.Label;

import java.util.Arrays;
import java.util.List;

public class DesignerModelGenerationStep extends TemplateProcessingStep {

  @Override
  protected List<TemplateData> buildTemplatesData(final CodeGenerationContext context) {
    final String appName = context.parameterOf(Label.APPLICATION_NAME);
    final String generationSettings = context.parameterOf(Label.DESIGNER_MODEL_JSON);
    return Arrays.asList(new DesignerModelTemplateData(appName, generationSettings));
  }
}
