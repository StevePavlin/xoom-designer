// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.designer.task.projectgeneration.code.java.unittest.projections;

import java.util.Collections;
import java.util.List;

public class PreliminaryStatement {

  private static final String PROJECTION_CONTROL_PATTERN = "final CountingProjectionControl control = new CountingProjectionControl();\n" +
      "    final AccessSafely access = control.afterCompleting(2);";

  public static List<String> with(final String testMethodName) {
    final String testDataVariableName = TestDataFormatter.formatStaticVariableName(1, testMethodName);
    return Collections.singletonList(String.format(PROJECTION_CONTROL_PATTERN, testDataVariableName));
  }

}
