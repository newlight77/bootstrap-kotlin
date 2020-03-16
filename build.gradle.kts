import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    `java-library`
    kotlin("jvm") version "1.3.61"
}

group = "io.github.newlight77"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.slf4j:slf4j-api:1.7.5")
    
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.11.1")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.11.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.11.1")

	testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
	testImplementation("org.mockito:mockito-junit-jupiter:3.3.0")
	testImplementation("org.assertj:assertj-core:3.11.1")
}

configurations {                            
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

sourceSets {                                
    main {                                  
        java.srcDir("src/main/java")
    }
}

java {                                      
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}


application {
    mainClassName = "io.github.newlight77.bootstrap.HelloWorldKt"
}

tasks.withType<Test> {
	useJUnitPlatform()
    testLogging {
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = true
    }
}
