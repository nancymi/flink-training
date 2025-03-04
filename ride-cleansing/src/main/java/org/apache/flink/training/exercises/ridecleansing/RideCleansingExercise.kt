/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.training.exercises.ridecleansing

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.training.exercises.common.datatypes.TaxiRide
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import kotlin.Throws
import org.apache.flink.api.common.JobExecutionResult
import org.apache.flink.api.common.functions.FilterFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.training.exercises.common.utils.GeoUtils
import kotlin.jvm.JvmStatic
import org.apache.flink.training.exercises.ridecleansing.RideCleansingExercise
import org.apache.flink.training.exercises.common.sources.TaxiRideGenerator
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction
import java.lang.Exception

/**
 * The Ride Cleansing exercise from the Flink training.
 *
 *
 * The task of this exercise is to filter a data stream of taxi ride records to keep only rides
 * that both start and end within New York City. The resulting stream should be printed.
 */
class RideCleansingExercise
/** Creates a job using the source and sink provided.  */(
    private val source: SourceFunction<TaxiRide>,
    private val sink: SinkFunction<TaxiRide>
) {
    /**
     * Creates and executes the long rides pipeline.
     *
     * @return {JobExecutionResult}
     * @throws Exception which occurs during job execution.
     */
    @Throws(Exception::class)
    fun execute(): JobExecutionResult {

        // set up streaming execution environment
        val env = StreamExecutionEnvironment.getExecutionEnvironment()

        // set up the pipeline
        env.addSource(source).filter(NYCFilter()).addSink(
            sink
        )

        // run the pipeline and return the result
        return env.execute("Taxi Ride Cleansing")
    }

    /** Keep only those rides and both start and end in NYC.  */
    class NYCFilter : FilterFunction<TaxiRide> {
        @Throws(Exception::class)
        override fun filter(taxiRide: TaxiRide): Boolean {
            return GeoUtils.isInNYC(taxiRide.startLon, taxiRide.startLat) &&
                    GeoUtils.isInNYC(taxiRide.endLon, taxiRide.endLat)
        }
    }

    companion object {
        /**
         * Main method.
         *
         * @throws Exception which occurs during job execution.
         */
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val job = RideCleansingExercise(TaxiRideGenerator(), PrintSinkFunction())
            job.execute()
        }
    }
}