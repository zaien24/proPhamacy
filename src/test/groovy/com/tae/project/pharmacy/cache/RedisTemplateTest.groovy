package com.tae.project.pharmacy.cache

import com.tae.project.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.SetOperations
import org.springframework.data.redis.core.ValueOperations

class RedisTemplateTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private RedisTemplate redisTemplate

    def "RedisTemplate String operations"() {

        given:
        def valueOperations = redisTemplate.opsForValue()
        String key = "stringKey"
        String value = "hello"

        when:
        valueOperations.set(key, value)

        then:
        def resultValue = valueOperations.get(key)
        resultValue == value
    }

    def "RedisTemplate set operations"() {
        given:
        def setOperations = redisTemplate.opsForSet()
        def key = "setKey"

        when:
        setOperations.add(key, "h", "e", "l", "l", "o")

        then:
        def size = setOperations.size(key)
        size == 4
    }

    def "RedisTemplate hash operations"() {
        given:
        def hasOperations = redisTemplate.opsForHash()
        def key = "hashkey"

        when:
        hasOperations.put(key, "subKey", "value")

        then:
        def result = hasOperations.get(key, "subKey")
        result == "value"

        def entries = hasOperations.entries(key)
        entries.keySet().contains("subKey")
        entries.values().contains("value")

        Long size = hasOperations.size(key)
        size == entries.size()
    }

}
