# ninefoo

Note we will use for this project:

- Java 1.7
- Gradle for build, test, run
- JUnit 4

## Getting started with Gradle

1. Download gradle binaries

    https://services.gradle.org/distributions/gradle-2.4-bin.zip

2. Add gradle to you environment path

    GRADLE_HOME/bin to your PATH environment variable

3. Make sure it works

    Navigate to the git repo in your terminal.

    ```bash
    cd ninefoo/
    gradle build
    ```

4. Set dev env

    * Eclipse

        - Generate your project file and settings files with gradle

            ```bash
            gradle eclipse
            ```

        - Get the gradle `plugin`:

            https://marketplace.eclipse.org/content/gradle-integration-eclipse-44

        - Open eclipse and import the project as a gradle project.

    * IntelliJ

        - The gradle plugin is usually allready installed.
        - Import project as a gradle project

5. Common gradle commands

- ``gradle build`` will build and test the project.
- ``gradle test``
- ``gradle run`` run the main application.

For a gradle quickstart: https://docs.gradle.org/current/userguide/tutorial_java_projects.html


## Markdown basics

For better and clearer communication in the issues,  
Please take 3 minutes to read [mastering-markdown!](https://guides.github.com/features/mastering-markdown/)  
Especially for code syntax highlting:

    ```java
    System.out.println('ninefoo')
    ```

And for mentions:

    @mention

## git Basics

**git clone** theUrlOfTheRepo  
**git pull**  Retreive changes from the remote repo to your local copy, always pull remote changes before you start working.  
**git status**  
**git add**  myFiles  
**git commit** -m "the commit message inside quotes"  
**git push** origin myBranch  
**git diff** - see your changes before commiting  
**git branch** - Create your own branch.  
**git push --set-upstream origin** - Push origin on GitHub.  
**git checkout** - Switch to a branch.  
**git log** - See previous commits  

