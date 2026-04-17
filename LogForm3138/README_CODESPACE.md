# Run in GitHub Codespaces / Devcontainer

Steps to push and run this project inside a Codespace (or any devcontainer):

1) Initialize git and push to GitHub (example using `gh` CLI):

```bash
cd LogForm3138
git init
git add .
git commit -m "Initial commit: servlet login app with devcontainer"
# create remote repository (interactive) and push
gh repo create YOUR_GITHUB_USERNAME/logform3138 --public --source=. --remote=origin --push
```

If you don't have `gh`, create a repo on GitHub and then:

```bash
git remote add origin https://github.com/USERNAME/REPO.git
git push -u origin main
```

2) Open the repo in GitHub Codespaces or VS Code Remote - Containers. Codespaces will build the devcontainer image.

3) In the Codespace terminal run these commands to start Tomcat and the app:

```bash
# Build the WAR
mvn -f LogForm3138/pom.xml clean package -DskipTests

# Start Tomcat in the container
$CATALINA_HOME/bin/shutdown.sh || true
$CATALINA_HOME/bin/startup.sh

# Deploy the WAR (if not auto-copied by maven plugin)
cp LogForm3138/target/logform3138-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/logform3138.war

# Tail logs
tail -f $CATALINA_HOME/logs/catalina.out
```

4) In Codespaces the forwarded port `8081` will be available via the Codespaces UI as a forwarded port. Open `http://localhost:8081/logform3138/` in the Codespace browser preview or use the forwarded URL.

Notes
- The devcontainer builds OpenJDK 21, Maven, Tomcat 10.1.50 and `sqlite3`. The app uses SQLite database file `app.db` in the repository root.
- If you want Tomcat to use port 8080 inside Codespaces, change `server.xml` back to `8080`. Codespaces will map container ports.
