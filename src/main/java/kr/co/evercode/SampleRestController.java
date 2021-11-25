package kr.co.evercode;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {
	
    @RequestMapping(value = "/hello.do") 
    public World getDao(@RequestBody Hello hello){
    	World world = new World();
    	
    	world.setAge(hello.getAge());
    	world.setGreeting("RestController: Hi'  " + hello.getName());
         
        return world;
    }

}
