import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logbackVersion: String by project

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.github.sintexer"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-rsocket")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework:spring-jdbc")

	// logging
	implementation("io.github.microutils:kotlin-logging:2.0.11")
	implementation("ch.qos.logback:logback-core:$logbackVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	testImplementation("org.slf4j:slf4j-simple:1.7.32")

//	runtimeOnly("io.r2dbc:r2dbc-postgresql")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
