object DependencyVersions {
    const val SWAGGER_VERSION = "2.9.2"
}

dependencies {

    implementation(project(":leo-common"))

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

springBoot.buildInfo { properties { } }

configurations {
    val archivesBaseName = "leo-api-staging"
}