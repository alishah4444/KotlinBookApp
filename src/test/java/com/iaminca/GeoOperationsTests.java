/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iaminca;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands.DistanceUnit;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Show usage of redis geo-index operations using Template API provided by {@link GeoOperations}.
 *
 * @author Mark Paluch
 */
@SpringBootTest
public class GeoOperationsTests {

    @Autowired
    private StringRedisTemplate operations;
    private GeoOperations<String, String> geoOperations;

    @BeforeEach
    public void before() {

        geoOperations = operations.opsForGeo();

        geoOperations.add("Sicily", new Point(13.361389, 38.115556), "Arigento");
        geoOperations.add("Sicily", new Point(15.087269, 37.502669), "Catania");
        geoOperations.add("Sicily", new Point(13.583333, 37.316667), "Palermo");
    }

    /**
     * Look up points using a geo-index member as reference.
     */
    @Test
    public void geoRadiusByMember() {
        var byDistance = geoOperations.radius("Sicily", "Palermo", new Distance(100, DistanceUnit.KILOMETERS));
        assertThat(byDistance).hasSize(2).extracting("content.name").contains("Arigento", "Palermo");
        var greaterDistance = geoOperations.radius("Sicily", "Palermo", new Distance(200, DistanceUnit.KILOMETERS));
        assertThat(greaterDistance).hasSize(3).extracting("content.name").contains("Arigento", "Catania", "Palermo");
    }

    /**
     * Lookup points within a circle around coordinates.
     */
    @Test
    public void geoRadius() {
        var circle = new Circle(new Point(13.583333, 37.316667), new Distance(100, DistanceUnit.KILOMETERS));
        var result = geoOperations.radius("Sicily", circle);
        assertThat(result).hasSize(2).extracting("content.name").contains("Arigento", "Palermo");
    }

    /**
     * Calculate the distance between two geo-index members.
     */
    @Test
    public void geoDistance() {
        var distance = geoOperations.distance("Sicily", "Catania", "Palermo", DistanceUnit.KILOMETERS);
        assertThat(distance).isNotNull();
        assertThat(distance.getValue()).isBetween(130d, 140d);
    }

    /**
     * Return the geo-hash.
     */
    @Test
    public void geoHash() {
        var geohashes = geoOperations.hash("Sicily", "Catania", "Palermo");
        assertThat(geohashes).hasSize(2).contains("sqdtr74hyu0", "sq9sm1716e0");
    }
}
