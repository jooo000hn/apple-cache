/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appleframework.cache.jedis.balancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.JedisPool;

public class RoundRobinLoadBalancer implements LoadBalancer {

    private final AtomicInteger index = new AtomicInteger(-1);

    @Override
    public JedisPool getJedisPool(List<JedisPool> jedisPoolsCopy) {
        int ind = Math.abs(index.incrementAndGet() % jedisPoolsCopy.size());
        return jedisPoolsCopy.get(ind);
    }

}