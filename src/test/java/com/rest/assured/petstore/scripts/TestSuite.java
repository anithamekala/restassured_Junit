package com.rest.assured.petstore.scripts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	GetByStatus.class,
	Post_JsonObject.class,
	GetById.class,
	PutById.class,
	GetById.class,
	DeleteById.class
})

public class TestSuite {   
}  
