// package com.remiges.adv_java_assignment.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.remiges.adv_java_assignment.service.RedisService;

// @RestController
// @RequestMapping("/redis")
// public class RedisController {

//     private final RedisService redisService;

//     @Autowired
//     private RedisTemplate<String, Object> redisTemplate;

//     public RedisController(RedisService redisService) {
//         this.redisService = redisService;
//     }

//     @PostMapping("/add")
//     public void addKey(@RequestParam String key, @RequestParam String value) {
//         redisService.addKey(key, value);
//     }

//     @GetMapping("/get")
//     public String getValue(@RequestParam String key) {
//         return redisService.getValue(key);
//     }

//     @PutMapping("/increment")
//     public Long incrementValue(@RequestParam String key) {
//         return redisService.incrementValue(key);
//     }

//     @PutMapping("/decrement")
//     public Long decrementValue(@RequestParam String key) {
//         return redisService.decrementValue(key);
//     }

//     @PutMapping("/ttl")
//     public void setTTL(@RequestParam String key, @RequestParam long ttlInSeconds) {
//         redisService.setTTL(key, ttlInSeconds);
//     }
    
// }
