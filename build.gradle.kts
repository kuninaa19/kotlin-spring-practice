import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	kotlin("kapt") version "1.4.10"
}

group = "com.koboot"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val querydslVersion ="5.0.0"

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
	implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.modelmapper:modelmapper:2.4.5")
	implementation("org.springframework.boot:spring-boot-starter-log4j2:2.6.2")
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.6.2")
	implementation("mysql:mysql-connector-java:8.0.25")
	implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
	implementation("com.amazonaws:aws-java-sdk-s3:1.12.131")
	implementation("com.google.firebase:firebase-admin:8.1.0")
	implementation("com.querydsl:querydsl-jpa:$querydslVersion")
	kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
	testImplementation("io.mockk:mockk:1.12.2")
	testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
	testImplementation("io.kotest:kotest-assertions-core:5.0.3")
	testImplementation("io.kotest:kotest-extensions-spring:4.4.3")
}

configurations {
	all {
		exclude("org.springframework.boot", "spring-boot-starter-logging")
	}
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
