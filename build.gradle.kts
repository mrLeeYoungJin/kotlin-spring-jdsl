import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt") version "1.7.0"
}

group = "com.lyjguy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val snippetsDir by extra { file("build/generated-snippets") }

repositories {
    mavenLocal {
        content {
            includeGroup("org.springframework.restdocs")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter:2.0.3.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Querydsl
    implementation("com.querydsl:querydsl-jpa")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

//    // Querydsl JPAAnnotationProcessor 사용 지정
//    implementation("com.querydsl:querydsl-apt:5.0.0")
//    // java.lang.NoClassDefFoundError(javax.annotation.Entity) 발생 대응
//    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
//    // java.lang.NoClassDefFoundError(javax.annotation.Generated) 발생 대응
//    annotationProcessor("jakarta.annotation:jakarta.annotation-api")

    runtimeOnly("com.h2database:h2")
    //runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

val test = tasks.withType<Test> {
    useJUnitPlatform()
    outputs.dir(snippetsDir)
}

val asciidoctor = tasks.withType<AsciidoctorTask> {
    dependsOn(test)
    baseDirFollowsSourceDir()
    doLast {
        copy {
            from("$outputDir")
            into("src/main/resources/static/docs")
        }
    }
}

tasks.withType<BootJar> {
    dependsOn(asciidoctor)
    from("$snippetsDir") {
        into("src/main/resources/static/docs")
    }
}

tasks.build {
    dependsOn(asciidoctor)
}