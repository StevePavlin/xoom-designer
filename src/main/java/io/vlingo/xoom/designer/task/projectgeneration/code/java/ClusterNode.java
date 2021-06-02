// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.task.projectgeneration.code.java;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClusterNode {

  private static final int DEFAULT_PORT = 18080;
  private static final int DEFAULT_START_PORT_RANGE = 37381;
  private static final int DEFAULT_TOTAL_NODES = 1;

  public final Integer id;
  public final String name;
  public final Integer operationalPort;
  public final Integer applicationPort;
  public final Integer serverPort;

  public static List<ClusterNode> from(final Integer httpServerPort,
                                       final Integer startPortRange,
                                       final Integer totalNodes) {
    final Integer resolvedHttpServerPort =
            httpServerPort > 0 ? httpServerPort : DEFAULT_PORT;

    final AtomicInteger resolvedStartPortRange =
            startPortRange > 0 ? new AtomicInteger(startPortRange) :
                    new AtomicInteger(DEFAULT_START_PORT_RANGE);

    final Integer resolvedTotalNodes =
            (totalNodes > 0 ? totalNodes : DEFAULT_TOTAL_NODES) + 1;

    return IntStream.range(1, resolvedTotalNodes)
            .mapToObj(nodeId -> new ClusterNode(nodeId, resolvedStartPortRange, resolvedHttpServerPort))
            .collect(Collectors.toList());
  }

  private ClusterNode(final Integer nodeId,
                      final AtomicInteger port,
                      final Integer httpServerPort) {
    this.id = nodeId;
    this.name = "node" + nodeId;
    this.operationalPort = id.equals(1) ? port.get() : port.incrementAndGet();
    this.applicationPort = port.incrementAndGet();
    this.serverPort = httpServerPort;
  }

}
