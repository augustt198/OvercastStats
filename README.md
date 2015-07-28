# OvercastAPI [![Build Status](http://ci.ryan-w.me/buildStatus/icon?job=OvercastAPI)](http://ci.ryan-w.me/job/OvercastAPI/) [![license](http://img.shields.io/:license-Mozilla Public License v2-blue.svg](https://github.com/ryanw-se/OvercastAPI/blob/master/LICENSE)
The goal of this project is to provide a solid API for the Overcast Network website, written in Java. 

You are welcome to contribute code or even just help out with issues, all contributions help further the project and are appreciated equally.

# Issue Reporting

**When creating an issue, keep the following in mind:**

* Verify that the issue hasn't already been reported, if it has add onto that issue rather than creating a new one.
* If your reporting a bug found in a project using OvercastAPI, verify that it is indeed an OvercastAPI issue.
* Only report valid issues, not feature requests or missing features. 

**Here are some things to consider when creating your actual issue report:**

* Provide a quick demonstration of the issue, whether it be a quick video or screenshots with steps to replicate.
* Always build the latest version of the API and verify that the issue still persists before creating a new issue.
* Write your issue in English, if you aren't good at English then you can use a tool such as Google Translate.

# Contributing

**When contributing to the project, please go through the following checklist to ensure a quality contribution:**

* Use a formal commit message. Commit messages should be in the present tense and prefixed with either ```api:``` or ```impl:``` depending on what you are changing. If you are changing the API, you would use the API prefix, same for impl. Because of this tag, make sure that your actual commit message is in all lower case. 
* When changing both the api and impl at the same time, create two different pull requests with separate commits. Do not group changes in both the api and impl in the same commit or pull request.
* Verify that your code meets the requirements for Google's Java coding conventions, http://google.github.io/styleguide/javaguide.html
* Make sure that your code actually works, before creating the pull request. If you attempt to have broken code merged, future pull requests will most likely be ignored.
* Use a proper IDE when contributing to the project, such as Eclipse, Netbeans and IntelliJ. Do not use the Github editor.

# Compiling

To compile and run the latest version of OvercastAPI you will need to have JDK6+ and apache-maven installed. If you are using an IDE like IntelliJ, you will have maven built in and can create a new run task set to maven with the command set to ```clean install```. If you are running from the command line, you can use the command ```mvn clean install``` while in the OvercastAPI directory. The result should be found in ```/target``` as a jar file called ```overcast-scraper-1.0-SNAPSHOT.jar```.