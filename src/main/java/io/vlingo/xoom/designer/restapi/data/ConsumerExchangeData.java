// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.restapi.data;

import java.util.ArrayList;
import java.util.List;

public class ConsumerExchangeData {

    public final String exchangeName;
    public final List<ReceiverData> receivers = new ArrayList<>();

    public ConsumerExchangeData(final String exchangeName) {
        this.exchangeName = exchangeName;
    }
}
