[![CodeFactor](https://www.codefactor.io/repository/github/sybsuper/sybmcstandardlib/badge)](https://www.codefactor.io/repository/github/sybsuper/sybmcstandardlib)
[![](https://jitpack.io/v/sybsuper/SybMCStandardLib.svg)](https://jitpack.io/#sybsuper/SybMCStandardLib)
![Banner](https://sybsuper.com/banner.png)

# SybMCStandardLib
A library that provides a set of useful functions and classes for Minecraft plugins. It is designed to provide compatibility with both Spigot API and Folia API.

## Use
### With Gradle
Add this to your repositories:
```Gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
And this to your dependencies:
```Gradle
	dependencies {
	        implementation 'com.github.sybsuper:SybMCStandardLib:master-SNAPSHOT'
	}
```
### With Maven
Add this to your repositories:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
And this to your dependencies:
```xml
	<dependency>
	    <groupId>com.github.sybsuper</groupId>
	    <artifactId>SybMCStandardLib</artifactId>
	    <version>master-SNAPSHOT</version>
	</dependency>
```