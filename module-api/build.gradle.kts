plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

val queryDsl = "5.0.0"

dependencies {
    implementation(project(":module-core"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("org.modelmapper:modelmapper:2.4.5")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.6.2")
    implementation("mysql:mysql-connector-java:8.0.29")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.131")
    implementation("com.google.firebase:firebase-admin:8.1.0")
    implementation("com.querydsl:querydsl-jpa:$queryDsl")
    kapt("com.querydsl:querydsl-apt:$queryDsl:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
    testImplementation("io.kotest:kotest-assertions-core:5.0.3")
    testImplementation("io.kotest:kotest-extensions-spring:4.4.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
