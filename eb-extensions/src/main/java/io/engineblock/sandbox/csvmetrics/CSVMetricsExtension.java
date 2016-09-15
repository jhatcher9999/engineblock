/*
 *
 *    Copyright 2016 jshook
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package io.engineblock.sandbox.csvmetrics;

import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;

public class CSVMetricsExtension {
    private Logger logger;
    private MetricRegistry metricRegistry;

    public CSVMetricsExtension(Logger logger, MetricRegistry metricRegistry) {
        this.logger = logger;
        this.metricRegistry = metricRegistry;
    }

    public CSVLogger logCsv(String filename) {
        return new CSVLogger(filename, logger, metricRegistry);
    }

}
