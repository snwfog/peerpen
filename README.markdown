### Concordia Capstone 2013

##### Setting up a Tomcat web application project in IntelliJ

Without generalizing, here is the folder structure that you are suppose to see when opening up (or importing in) the project into IntelliJ.

![project structure](http://cdn.charlescy.com/blog/img/project_tree_pp_01.png)

Then make sure that you have the project structure setup properly. It should be setup already technically, since that the `iml` file is sync'ed with `git`.

To verify the project structure, you click on `File -> Project Structure`:

![project structure menubar](http://cdn.charlescy.com/blog/img/project-structure-menubar.png)

Make sure that the `ppar` library is included in the compile path:

![library dependency](http://cdn.charlescy.com/blog/img/library-dependency.png)

Make sure that the artifact deployment section is setup, if it is empty, just add a new project and choose to have a exploded `war`.

![artifact setup](http://cdn.charlescy.com/blog/img/artifact-setup.png)

Add a new server deployment, it should be your local Tomcat repository. This step might be optional, depending on your system.

![deployment menu](http://cdn.charlescy.com/blog/img/deployment-tab.png)
![deployment tab](http://cdn.charlescy.com/blog/img/deployment-menu.png)

Finally, you need to add a running configuration from:
![run configuration](http://cdn.charlescy.com/blog/img/run-project.png)

![run project](http://cdn.charlescy.com/blog/img/run-project.png)
![add tomcat](http://cdn.charlescy.com/blog/img/add-tomcat.png)
![add deploy artifact](http://cdn.charlescy.com/blog/img/add-tomcat.png)



