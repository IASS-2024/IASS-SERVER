plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "IASS-SERVER"
include("module-api")
include("module-common")
include("module-domain")
include("module-external")
