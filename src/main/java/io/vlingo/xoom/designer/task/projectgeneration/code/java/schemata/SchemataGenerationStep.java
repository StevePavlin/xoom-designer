// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.designer.task.projectgeneration.code.java.schemata;

import io.vlingo.xoom.codegen.CodeGenerationContext;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameter;
import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateProcessingStep;
import io.vlingo.xoom.designer.task.projectgeneration.Label;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.model.valueobject.ValueObjectDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchemataGenerationStep extends TemplateProcessingStep {

  @Override
  protected List<TemplateData> buildTemplatesData(final CodeGenerationContext context) {
    final List<CodeGenerationParameter> valueObjects =
            context.parametersOf(Label.VALUE_OBJECT).collect(Collectors.toList());

    final List<CodeGenerationParameter> exchanges =
            context.parametersOf(Label.AGGREGATE).flatMap(aggregate -> aggregate.retrieveAllRelated(Label.EXCHANGE))
                    .collect(Collectors.toList());

    final List<CodeGenerationParameter> publishedValueObjects =
            findPublishedValueObjects(exchanges, valueObjects);

    final List<TemplateData> templateData = new ArrayList<>();
    templateData.addAll(DomainEventSpecificationTemplateData.from(exchanges));
    templateData.addAll(ValueObjectSpecificationTemplateData.from(exchanges, publishedValueObjects));
    templateData.add(new SchemataPluginTemplateData(exchanges, publishedValueObjects));
    return templateData;
  }

  private List<CodeGenerationParameter> findPublishedValueObjects(final List<CodeGenerationParameter> exchanges,
                                                                  final List<CodeGenerationParameter> valueObjects) {
    final List<CodeGenerationParameter> publishedValueObjects =
            ValueObjectDetail.findPublishedValueObjects(exchanges, valueObjects)
                    .collect(Collectors.toList());

    final List<CodeGenerationParameter> relatedValueObjects =
            publishedValueObjects.stream().flatMap(valueObject -> ValueObjectDetail.collectRelatedValueObjects(valueObject, valueObjects))
                    .collect(Collectors.toList());

    return Stream.of(publishedValueObjects, relatedValueObjects).flatMap(List::stream).collect(Collectors.toList());
  }

  @Override
  public boolean shouldProcess(final CodeGenerationContext context) {
    return context.parametersOf(Label.AGGREGATE).flatMap(aggregate -> aggregate.retrieveAllRelated(Label.EXCHANGE)).count() > 0;
  }
}
