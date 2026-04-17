JDBC_Terminal

Simple terminal Java app to do CRUD on the Library SQLite `books` table.

This CLI connects to the repository `db/library.db` created earlier and provides create/list/update/delete operations for `books`.

Build and run (requires `sqlite-jdbc-3.47.0.0.jar` on the project root):

1. Compile:
```bash
javac -cp sqlite-jdbc-3.47.0.0.jar -d out src/app/Main.java
```

2. Run:
```bash
java -cp out:sqlite-jdbc-3.47.0.0.jar app.Main
```

Notes:
- The program uses `../db/library.db` by default (relative to `JDBC_Terminal`), which was created by `db/init_library.sql`.
- If the JDBC driver jar is missing, place the real `sqlite-jdbc-3.47.0.0.jar` into this folder.
- Example: to reuse the webapp DB instead, run the CLI from the workspace root and adjust the classpath accordingly.
