/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.app;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kie.kogito.conf.ConfigBean;
import org.kie.kogito.event.cloudevents.CloudEventMeta;
import org.kie.kogito.event.EventKind;

public class DecisionCloudEventMetaFactory {

    ConfigBean config;

    private CloudEventMeta buildCloudEventMeta(String type, String sourceSuffix, EventKind kind) {
        String source = kind == EventKind.PRODUCED
                ? Stream.of(config.getServiceUrl(), sourceSuffix)
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("/"))
                : "";
        return new CloudEventMeta(type, source, kind);
    }

    public CloudEventMeta buildCloudEventMeta_CONSUMED_DecisionRequest() {
        return new CloudEventMeta("DecisionRequest", "", EventKind.CONSUMED);
    }

    public CloudEventMeta buildCloudEventMeta_PRODUCED_DecisionResponseError_UnknownModel() {
        String source = Optional.of(config.getServiceUrl()).filter(s -> s != null && !s.isEmpty()).orElse("__UNKNOWN_SOURCE__");
        return new CloudEventMeta("DecisionResponseError", source, EventKind.PRODUCED);
    }

    public CloudEventMeta buildCloudEventMeta_$methodName$() {
        return buildCloudEventMeta($type$, $source$, $kind$);
    }
}
