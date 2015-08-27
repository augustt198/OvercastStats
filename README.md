## OvercastStats [![Build Status](https://travis-ci.org/ryanw-se/OvercastStats.svg?branch=master)](https://travis-ci.org/ryanw-se/OvercastStats) [![license](http://img.shields.io/:license-MIT-blue.svg)](https://github.com/ryanw-se/OvercastAPI/blob/master/LICENSE)
A lightweight player statistics parsing API, written in Java.

You can check out the docs and javadoc here, http://ryanw-se.github.io/OvercastStats/.

### Contributors

Special thanks to the following contributors for their feedback, time, suggestions and more.

 - Aaron1011 (Feedback, Suggestions for improving the mappings url system)
 - skipperguy12 (Feedback, Helped me setup the maven server)
 - twiz_mwazin (Feedback, Suggestions, Original architectural decisions)
 - parapenguin (Feedback, Suggestions, work on KK)
 - molenzwiebl (Feedback, Suggestions)
 - augustt198 (Feedback, Suggestions)
 - kblanks (Feedback, Suggestions)
 - gcflames5 (Feedback, Suggestions)

### Dependency Usage

Adding OvercastStats to your maven or gradle project is super painless and takes less than a minute. This snippet of code below
demonstrates how simple it is to add this API to your project. Chances are you only need to add a new maven repository and dependency.

```gradle
buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "repo.ryan-w.me"
            url = "http://repo.ryan-w.me/nexus/content/repositories/snapshots/"
        }
    }
    dependencies {
        classpath 'me.ryanw.overcaststats:overcast-scraper:1.1-SNAPSHOT'
    }
}
```

Here is how you would do so a Maven based project, please keep in mind that you should always check the build server or nexus
and change the 1.1-SNAPSHOT version, to what ever is the latest. Its always recommended to be on the latest version.

```xml
<repositories>
    <repository>
        <id>repo.ryan-w.me</id>
        <url>http://repo.ryan-w.me/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>me.ryanw.overcaststats</groupId>
        <artifactId>overcast-scraper</artifactId>
        <version>1.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

You can check out the Sonatype Nexus server here, http://repo.ryan-w.me/nexus/.

### Issue Reporting - Guidelines

* Verify that the issue hasn't already been reported, if it has add onto that issue rather than creating a new one.
* Determine whether or not the issue is a mappings problem, or a OvercastStats related issue. Please report mappings issues, in that repository.
* Do not add your own tags like [BUG], [IMPORTANT], tags will be added to your issue is it is valid.
* If your bug is complex, tell us how to reproduce it, either step by step or with a short video.
* Be short and to the point, explain exactly what is happening, what you expected and anything else you feel is important.
* If your bug is invalid, just close it. Do not rename your ticket, or edit it. Just close it.

### Contributing - Guidelines

* When creating commit messages, always use present tense. If you have commits that aren't present tense, they won't be accepted.
* Always use a proper IDE, never edit code in browser, get a proper development environment (IDE, Java, Maven).
* Test your code, its always good to run several tests before creating a pull request.
* If your pull request is a work in progress, then simply mention that in your pull request description.
* Do not add tags to your pull requests, such as WIP, DONE, a collaborator will add tags where they are needed.
*

### Compiling - Overview

To compile this project, you will need to have Maven, Java, Git and a optionally an IDE installed. Verify that you are running at least Java 6
or higher, you will need the JDK installed in order to compile using Maven. Once you have installed a JDK that is at least running version
Java 6 or higher, you will need to download and install apache maven.

If you're on a mac, its as simple as installing brew and running the command ```brew install maven```. Linux users typically have a package
that they can install from, Windows users have plenty of tutorials showing you how to install maven on your specific version of Windows.

Git is very simple to install, if you're on Mac simply open up terminal and type git, an Xcode package installer will launch, simply install
the git package and you have git installed. On Linux, you should have a git package and windows users can download git from here, https://git-scm.com/.

After you have installed maven, you will want to clone the latest version if this repository. You can do so by navigating to the directory
that you wish to download the files into and typing ```git clone https://github.com/ryanw-se/OvercastStats.git```. Open up a terminal or
Command Prompt if your on Windows, and type ```ls```. This will list all of your directories, if you downloaded OvercastStats in your documents folder,
you would typically type ```cd Documents``` followed by ```cd OvercastStats```.

The first command will vary depending on where you downloaded the files, but the second command is always the same after you get into the directory.
Now that we have gotten into the directory, its time to build the project. Simply type the command ```mvn clean install``` to build the project
(create a jar) and install it to your local maven repository. (For reference in your projects).

The end result should be stored inside the ```target``` folder, as a jar file called ```overcast-scraper-<version>-SNAPSHOT.jar``` and ```api-<version>-SNAPSHOT.jar```.